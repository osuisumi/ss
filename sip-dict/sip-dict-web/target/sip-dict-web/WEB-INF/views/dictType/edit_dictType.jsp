<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="saveDictTypeForm" action="${ctx}/dictType/save" method="post">
	<input type="hidden" name="state" value="${state}" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">索引名称:</td>
				<td width="80%" style="text-align: left;"><input type="text"
					name="dictTypeCode" class="easyui-textbox" required 
					value="${dictType.dictTypeCode}" style="width: 60%" /></td>
			</tr>
			<tr>
				<td>索引编号:</td>
				<td  style="text-align: left;"><input type="text"
					name="dictTypeName" class="easyui-textbox" required
					value="${dictType.dictTypeName}" style="width: 60%" /></td>
			</tr>			
			<tr>
				<td>父索引编号:</td>
				<td style="text-align: left;">
					<input type="text" id="parentCode"  class="easyui-textbox"
					value="${dictType.parentCode}" style="width: 60%" disabled />
					<button type="button" class="easyui-linkbutton main-btn"
						onclick="chooseParentCode();">
						<i class="fa fa-search"></i> 选择
					</button>
				</td>
			</tr>								
		</tbody>
	</table>
	<br>
	<input hidden type="text" name="parentCode" value="${dictType.parentCode}"/>
	
	<div style="text-align: center">
		<button type="button" onclick="saveDictType()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script type="text/javascript">
    $(function(){    	
    	if($("#saveDictTypeForm input[name=state]").val() === "update"){
    		$("input[name=dictTypeCode]").attr("readonly",true);
    	}
    });

	function saveDictType() {
		if (!$('#saveDictTypeForm').form('validate')) {
			return false;
		}	
		var data = $.ajaxSubmit('saveDictTypeForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_tabs_update('listDictTypeForm', 'layout_center_tabs');
				easyui_modal_close('editDictTypeDiv');
			});
		}else{
			$.messager.alert("提示信息", "操作失败！", 'info');
		}
		
	}
	function chooseParentCode(){
		easyui_modal_open('chooseParentCodeDiv', '选择父索引', 900,700, '${ctx}/dictType/listForChooseParentCode', true);
	}
	
</script>