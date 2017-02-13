<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="listPermissionForm">
	<div>
		<table cellpadding="0" cellspacing="0" width="100%" style="padding: 10px;">
			<tr>
				<td colspan="6"><br />
					<button type="button" class="easyui-linkbutton" onclick="addPermission()">
						<i class="fa fa-plus"></i> 新增
					</button>
					<button type="button" class="easyui-linkbutton" onclick="editPermission()">
						<i class="fa fa-pencil"></i> 修改
					</button>
					<button type="button" class="easyui-linkbutton delete-btn" onclick="deletePermission()">
						<i class="fa fa-minus"></i> 删除
					</button>
				</td>
			</tr>
		</table>
	</div>
	<table id="listPermissionTable" class="easyui-datagrid" pagination="true"
		rownumbers="true" fitColumns="true" singleSelect="false"
		checkOnSelect="true" selectOnCheck="true" data-options="">
		<thead>
			<tr>
				<th width="5" data-options="field:'id',checkbox:true"></th>
				<th width="10" data-options="field:'title'">action</th>
				<th width="5" data-options="field:'imageUrl'">actionURI</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty permissions}">
				<c:forEach items="${permissions}" var="permission">
					<tr>
						<td>${permission.id}</td>
						<td>${permission.name}</td>
						<td>${permission.action}</td>
						<td>${permission.actionURI }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<%-- <jsp:include page="/WEB-INF/views/include/pagination_table.jsp">
		<jsp:param name="pageForm" value="listPermissionForm" />
		<jsp:param name="tableId" value="listPermissionTable" />
		<jsp:param name="type" value="easyui" />
		<jsp:param name="tabId" value="layout_center_tabs" />
		<jsp:param name="paginatorName" value="permissionsPaginator" />
	</jsp:include> --%>
</form>
<script>

function addPermission(){
	var url = '${ctx}/auth_permissions/create';
	easyui_modal_open('editPermissionDiv', '新增权限', 800, 300, url, true);
}

function deletePermission(){
	var row = $('#listPermissionTable').treegrid('getSelections');
	if (row.length == 0) {
		$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
		return false;
	}else {
		$.messager.confirm('确认','确认要删除选中的组吗？',function(r){    
		    if (r){
		    	$.ajaxDelete('${ctx}/auth_permissions/batch/delete', $('#listPermissionForm').serialize(), function(){
					//easyui_tabs_update('listResourceForm', 'layout_center_tabs');
		    		easyui_panel_update('listPermissionForm','permissionList');
				});
		    }    
		}); 
	}
}

function editPermission(){
	var row = $('#listPermissionTable').treegrid('getSelections');
	if (row.length == 0) {
		$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
		return false;
	}else if (row.length > 1) {
		$.messager.alert('提示', '不能同时编辑多条数据', 'warning');
		return false;
	}else {
		var id = row[0].id;
		var type = row[0].type;
		easyui_modal_open('editPermissionDiv', '修改权限', 800, 300, '${ctx}/auth_permissions/'+id+'/edit?type='+type, true);
	}
}

</script>