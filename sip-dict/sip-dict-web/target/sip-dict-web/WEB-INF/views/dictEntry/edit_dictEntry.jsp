<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="saveDictEntryForm" action="${ctx}/dictEntry/save" method="post">
	<input type="hidden" name="id" value="${dictEntry.id}" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">字典项名称:</td>
				<td width="80%" style="text-align: left;"><input type="text"
					name="dictName" class="easyui-textbox" required 
					value="${dictEntry.dictName}" style="width: 60%" /></td>
			</tr>
			<tr>
				<td>字典项值:</td>
				<td  style="text-align: left;"><input type="text"
					name="dictValue" class="easyui-textbox" required
					value="${dictEntry.dictValue}" style="width: 60%" /></td>
			</tr>
			<tr>
				<td>序号:</td>
				<td  style="text-align: left;"><input type="text"
					name="sortNo" class="easyui-textbox" required
					value="${dictEntry.sortNo}" style="width: 60%" /></td>
			</tr>									
			<tr>
				<td>索引编号:</td>
				<td style="text-align: left;">
					<input type="text" id="dictTypeCode" class="easyui-textbox"
					value="${dictEntry.dictTypeCode}" style="width: 60%" disabled required/>
					<button type="button" class="easyui-linkbutton main-btn"
						onclick="chooseDictTypeCode();">
						<i class="fa fa-search"></i> 选择
					</button>
				</td>
			</tr>
			<tr>
				<td>父字典项值:</td>
				<td  style="text-align: left;"><input type="text"
					name="parentValue" class="easyui-textbox" 
					value="${dictEntry.parentValue}" style="width: 60%" />
				</td>
			</tr>								
		</tbody>
	</table>
	<br>
	<input hidden type="text" name="dictTypeCode" value="${dictEntry.dictTypeCode}"/>
	<div style="text-align: center">
		<button type="button" onclick="saveDictEntry()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script type="text/javascript">

	function saveDictEntry() {
		if (!$('#saveDictEntryForm').form('validate')) {
			return false;
		}	
		var data = $.ajaxSubmit('saveDictEntryForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_tabs_update('listDictEntryorm', 'layout_center_tabs');
				easyui_modal_close('editDictEntryDiv');
			});
		}else{
			$.messager.alert("提示信息", "操作失败！", 'info');
		}
	}
	function chooseDictTypeCode(){
		easyui_modal_open('chooseDictTypeCodeDiv', '选择父索引', 900,700, '${ctx}/dictType/listForChoose', true);
	}
	
</script>