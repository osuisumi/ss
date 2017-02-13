package com.haoyu.sip.excel;


import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.excel.annotations.ExcelEntity;
import com.haoyu.sip.excel.annotations.ExportField;
import com.haoyu.sip.excel.styles.Style;
import com.haoyu.sip.utils.Reflections;


/**
 * Excel导出
 * @author lianghuahuang
 *
 */
public class ExcelExport<T> {
	Class<T> clazz;
	
	private List<String> errorMsg = Lists.newArrayList();
	
	public List<String> getErrorMsg() {
		return errorMsg;
	}

	public ExcelExport(Class<T> clazz){
		this.clazz = clazz;
	}
	
	/**
	 * 导出excel
	 * @param dataList 标注为ExcelEntity的对象集合
	 * @param outStream 输出流
	 */
	public void exportExcel(List<T> dataList,OutputStream outStream){
		ExcelEntity excelEntity = clazz.getAnnotation(ExcelEntity.class);
		Class<?> styleClass = excelEntity.style();
		try {
	        
	        Map<String, Map<String, Object>> fieldMap = Maps.newHashMap();
	        Map<Integer,String> fieldIndexMap = Maps.newHashMap();
	        parseExportField(excelEntity, fieldMap, fieldIndexMap);
	        //验证字段排序信息
	        if(excelEntity.sortHead()){
	        	if(!fieldMap.isEmpty()&&fieldMap.size()!=fieldIndexMap.size()){
	        		this.errorMsg.add("ExportField 排序信息配置错误！");
	        		return;
	        	}else if(!fieldIndexMap.isEmpty()){
	        		Set<Integer> indexSets = fieldIndexMap.keySet();
	        		for(Integer idx:indexSets){
	        			if(idx<0||idx>=fieldMap.size()){
	        				this.errorMsg.add("ExportField 排序信息配置错误！");
	        				return;
	        			}
	        		}
	        	}
	        	
	        }
	        
	        //写入EXCEL字段标题信息
	      //创建EXCEL标题信息
			HSSFWorkbook wb = new HSSFWorkbook();  
	        Sheet sheet = wb.createSheet(excelEntity.sheetName());  
	        Style style = (Style)(styleClass.newInstance());
	        CellStyle headStyle = style.getHeadStyle(wb);
	        
	        Set<Entry<Integer,String>> fieldIndexEs = fieldIndexMap.entrySet();
	    	//创建标题栏
	        if(fieldIndexEs!=null&&!fieldIndexEs.isEmpty()){
	        	Row row = sheet.createRow(0);// 创建第一行  
	        	row.setHeight(excelEntity.rowHeight());
	 	        Cell cell = row.createCell(0);// 创建第一行的第一个单元格  
	 	        cell.setCellValue("序号"); 
	 	        cell.setCellStyle(headStyle);
	 	        Iterator<Entry<Integer,String>> iterator = fieldIndexEs.iterator();
	 	        while(iterator.hasNext()){
	 	        	Entry<Integer,String> entry = iterator.next();
	 	        	cell  = row.createCell(entry.getKey()+1);
	 	        	cell.setCellValue(entry.getValue());
	 	        	cell.setCellStyle(headStyle);
	 	        	Map<String,Object> ifm = fieldMap.get(entry.getValue());
	 	        	short colWidth = (Short)(ifm.get("colWidth"));
	 	        	sheet.setColumnWidth(entry.getKey()+1, colWidth);
	 	        }
	        }
	        //创建数据记录
	        if(dataList!=null&&!dataList.isEmpty()){
	        	int rowIndex = 1;
	        	CellStyle bodyStyle = style.getBodyStyle(wb);
	        	if(excelEntity.wrapText()){
	        		bodyStyle.setWrapText(excelEntity.wrapText());
	        	}
	        	for(T data:dataList){
	        		Row row = sheet.createRow(rowIndex);// 创建第一行  
	        		row.setHeight(excelEntity.rowHeight());
		 	        Cell cell = row.createCell(0);// 
		 	        cell.setCellValue(rowIndex++); 
		 	        cell.setCellStyle(bodyStyle);
		 	        Iterator<Entry<Integer,String>> iterator = fieldIndexEs.iterator();
		 	        while(iterator.hasNext()){
		 	        	Entry<Integer,String> entry = iterator.next();
		 	        	cell  = row.createCell(entry.getKey()+1);		 	        	
		 	        	Map<String,Object> ifm = fieldMap.get(entry.getValue());
		 	        	String fieldName = ObjectUtils.toString(ifm.get("fieldName"));
		 	        	cell.setCellValue(ObjectUtils.toString(Reflections.getFieldValue(data, fieldName)));		 	        	
		 	        	cell.setCellStyle(bodyStyle);
		 	        }
	        	}
	        }
			wb.write(outStream);
	        outStream.flush();
	                
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	/**
	 * @param excelEntity
	 * @param fieldMap
	 * @param fieldIndexMap
	 */
	private void parseExportField(ExcelEntity excelEntity,
			Map<String, Map<String, Object>> fieldMap,
			Map<Integer, String> fieldIndexMap) {
		int index = 0;
		//获取需要导出的字段
		Field[] fields = clazz.getDeclaredFields();  
		for (Field field:fields) {  
			ExportField exportField = field.getAnnotation(ExportField.class);
			if(exportField!=null){
				Map<String,Object> exportFieldMap = Maps.newHashMap();
				exportFieldMap.put("colWidth", exportField.colWidth());
				exportFieldMap.put("fieldName", field.getName());
				fieldMap.put(exportField.colName(), exportFieldMap);
				if(excelEntity.sortHead()){
					fieldIndexMap.put(exportField.index(), exportField.colName());
				}else{
					fieldIndexMap.put(index, exportField.colName());
					index++;
				}
			}
		}
	}
	
}
