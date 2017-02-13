<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="listDictTypeFormForChooseParentCode" action="${ctx}/dictType/listForChooseParentCode">
	<input type="hidden" name="orders" value="CREATE_TIME.DESC" />
	<div>
		<table cellpadding="5px;" cellspacing="0" width="100%"
			style="padding: 10px;">
			<tr>
				<td width="9%">索引名称：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[dictTypeName]" value="${searchParam.paramMap.dictTypeName}">
				</td>
				<td width="9%">索引编号：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[dictTypeCode]" value="${searchParam.paramMap.dictTypeCode}">
				</td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td colspan="6"><br />
					<button type="button" class="easyui-linkbutton main-btn"
						onclick="">
						<i class="fa fa-search"></i> 查询
					</button>
				</td>
			</tr>
		</table>
	</div>
	<table id="listDictTypeTableForChooseParentCode" class="" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" checkOnSelect="true"
		selectOnCheck="true">
		<thead>
			<tr>
				<th width="10" data-options="field:'dictTypeCode',checkbox:true"></th>
				<th width="15" data-options="field:'id'">索引编号</th>
				<th width="20" data-options="field:'dictTypeName'">索引名称</th>
				<th width="15" data-options="field:'parentCode'">父索引编号</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty dictTypes}">
				<c:forEach items="${dictTypes}" var="dictType">
					<tr>
						<td>${dictType.dictTypeCode}</td>
						<td>${dictType.dictTypeCode}</td>
						<td>${dictType.dictTypeName}</td>
						<td>${dictType.parentCode}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/views/include/pagination_window.jsp">
		<jsp:param name="pageForm" value="listDictTypeFormForChooseParentCode" />
		<jsp:param name="tableId" value="listDictTypeTableForChooseParentCode" />
		<jsp:param name="type" value="easyui" />
		<jsp:param name="tabId" value="chooseParentCodeDiv" />
		<jsp:param name="paginatorName" value="dictTypesPaginator" />
	</jsp:include>
	<br/>
	<div style="text-align: center">
		<button type="button" onclick="selectParentCode()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script>

	function selectParentCode() {
		var row = $('#listDictTypeFormForChooseParentCode').find('#listDictTypeTableForChooseParentCode').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		} else if (row.length > 1) {
			$.messager.alert('提示', '不能同时编辑多条数据', 'warning');
			return false;
		} else if(row[0].dictTypeCode == $("input[name=dictTypeCode]").val()){
			$.messager.alert('提示', '不能与子索引相同', 'warning');
			return false;
		} else {
			var dictTypeCode = row[0].dictTypeCode;
			$("#parentCode").textbox('setValue',dictTypeCode);
			$("input[name=parentCode]").val(dictTypeCode);
			easyui_modal_close('chooseParentCodeDiv');
		}
	}

</script>