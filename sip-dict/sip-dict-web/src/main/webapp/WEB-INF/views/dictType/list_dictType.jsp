<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="listDictTypeForm" action="${ctx}/dictType/list">
	<input type="hidden" name="orders" value="CREATE_TIME.DESC" />
	<div>
		<table cellpadding="5px;" cellspacing="0" width="100%"
			style="padding: 10px;">
			<tr>
				<td width="6%">索引名称：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[dictTypeName]" value="${searchParam.paramMap.dictTypeName}">
				</td>
				<td width="6%">索引编号：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[dictTypeCode]" value="${searchParam.paramMap.dictTypeCode}">
				</td>
				<td width="7%">父索引编号：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[parentCode]" value="${searchParam.paramMap.parentCode}">
				</td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="6"><br />
					<button type="button" class="easyui-linkbutton main-btn"
						onclick="easyui_tabs_update('listDictTypeForm','layout_center_tabs');">
						<i class="fa fa-search"></i> 查询
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="addDictType()">
						<i class="fa fa-plus"></i> 新增
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="editDictType()">
						<i class="fa fa-pencil"></i> 修改
					</button>
					<button type="button" class="easyui-linkbutton delete-btn"
						onclick="deleteDictType()">
						<i class="fa fa-minus"></i> 删除
					</button>
					</td>
			</tr>
		</table>
	</div>
	<table id="listDictTypeTable" class="" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" checkOnSelect="true"
		selectOnCheck="true">
		<thead>
			<tr>
				<th width="10" data-options="field:'dictTypeCode',checkbox:true"></th>
				<th width="20" data-options="field:'id'">索引编号</th>
				<th width="30" data-options="field:'dictTypeName'">索引名称</th>
				<th width="20" data-options="field:'parentCode'">父索引编号</th>
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
	<jsp:include page="/WEB-INF/views/include/pagination_table.jsp">
		<jsp:param name="pageForm" value="listDictTypeForm" />
		<jsp:param name="tableId" value="listDictTypeTable" />
		<jsp:param name="type" value="easyui" />
		<jsp:param name="tabId" value="layout_center_tabs" />
		<jsp:param name="paginatorName" value="dictTypesPaginator" />
	</jsp:include>
</form>
<script>
	function addDictType() {
		var url = '${ctx}/dictType/create';
		easyui_modal_open('editDictTypeDiv', '添加字典索引', 500, 230, url, true);
	}

	function editDictType() {
		var row = $('#listDictTypeForm').find('#listDictTypeTable').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		} else if (row.length > 1) {
			$.messager.alert('提示', '不能同时编辑多条数据', 'warning');
			return false;
		} else {
			var dictTypeCode = row[0].dictTypeCode;
			easyui_modal_open('editDictTypeDiv', '编辑字典索引', 500,230, '${ctx}/dictType/' + dictTypeCode + '/edit', true);
		}
	}
	function updateAdvert(state) {
		var row = $('#listAdvertForm').find('#listAdvertTable').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择数据进行操作！', 'warning');
			return false;			
		}else {		

			$.messager.confirm('确认', '确认要更新选中记录吗？', function(r) {
				if (r) {
					$.ajax({
						  url: '${ctx}/advert/update?state='+state,
						  data: $('#listAdvertForm').serialize(),
						  success: function() {
								easyui_tabs_update('listAdvertForm', 'layout_center_tabs');
							},
						});
				}
			});
		}
	}
	
	function deleteDictType() {
		var row = $('#listDictTypeForm').find('#listDictTypeTable').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择数据进行操作！', 'warning');
			return false;			
		}else {					
			$.messager.confirm('确认', '确认要删除选中记录吗？', function(r) {
				if (r) {
					$.ajaxDelete('${ctx}/dictType', $('#listDictTypeForm').serialize(), function() {
						easyui_tabs_update('listDictTypeForm', 'layout_center_tabs');
					});
				}
			});
		}
	}
</script>