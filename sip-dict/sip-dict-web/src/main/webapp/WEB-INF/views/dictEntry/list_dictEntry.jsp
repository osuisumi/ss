<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="listDictEntryForm" action="${ctx}/dictEntry/list">
	<input type="hidden" name="orders" value="DICT_TYPE_CODE.ASC,SORT_NO.DESC" />
	<div>
		<table cellpadding="5px;" cellspacing="0" width="100%"
			style="padding: 10px;">
			<tr>				
				<td width="7%">字典索引名称：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[dictTypeName]" value="${searchParam.paramMap.dictTypeName}">
				</td>
				<td width="7%">字典索引编号：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[dictTypeCode]" value="${searchParam.paramMap.dictTypeCode}">
				</td>
				<td width="7%">父字典项值：</td>
				<td width="10%">
					<input type="text" class="easyui-textbox" 	name="paramMap[parentValue]" value="${searchParam.paramMap.parentValue}">
				</td>
				<td colspan="4"></td>
			</tr>
			<tr>
				<td colspan="6"><br />
					<button type="button" class="easyui-linkbutton main-btn"
						onclick="easyui_tabs_update('listDictEntryForm','layout_center_tabs');">
						<i class="fa fa-search"></i> 查询
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="addDictEntry()">
						<i class="fa fa-plus"></i> 新增
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="editDictEntry()">
						<i class="fa fa-pencil"></i> 修改
					</button>
					<button type="button" class="easyui-linkbutton delete-btn"
						onclick="deleteDictEntry()">
						<i class="fa fa-minus"></i> 删除
					</button>
				</td>
			</tr>
		</table>
	</div>
	<table id="listDictEntryTable" class="" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="false" checkOnSelect="true"
		selectOnCheck="true">
		<thead>
			<tr>
				<th width="10" data-options="field:'id',checkbox:true"></th>		
				<th width="10" data-options="field:'dictValue'">字典项值</th>
				<th width="20" data-options="field:'dictName'">字典项名称</th>
				<th width="25" data-options="field:'dictTypeCode'">索引编号</th>
				<th width="20" data-options="field:'dictTypeName'">索引名称</th>
				<th width="10" data-options="field:'parentValue'">父字典项值</th>
				<th width="20" data-options="field:'sortNo'">序号</th>
				<th width="20" data-options="field:'isHidden'">是否隐藏</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty dictEntrys}">
				<c:forEach items="${dictEntrys}" var="dictEntry">
					<tr>
						<td>${dictEntry.id}</td>
						<td>${dictEntry.dictValue}</td>
						<td>${dictEntry.dictName}</td>
						<td>${dictEntry.dictTypeCode}</td>
						<td>${dictEntry.dictTypeName}</td>
						<td>${dictEntry.parentValue}</td>
						<td>${dictEntry.sortNo}</td>
						<td>${dictEntry.isHidden}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<jsp:include page="/WEB-INF/views/include/pagination_table.jsp">
		<jsp:param name="pageForm" value="listDictEntryForm" />
		<jsp:param name="tableId" value="listDictEntryTable" />
		<jsp:param name="type" value="easyui" />
		<jsp:param name="tabId" value="layout_center_tabs" />
		<jsp:param name="paginatorName" value="dictEntrysPaginator" />
	</jsp:include>
</form>
<script>
	function addDictEntry() {
		var url = '${ctx}/dictEntry/create';
		easyui_modal_open('editDictEntryDiv', '添加字典项', 500, 300, url, true);
	}

	function editDictEntry() {
		var row = $('#listDictEntryForm').find('#listDictEntryTable').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		} else if (row.length > 1) {
			$.messager.alert('提示', '不能同时编辑多条数据', 'warning');
			return false;
		} else {
			var id = row[0].id;
			easyui_modal_open('editDictEntryDiv', '编辑字典索引', 500,300, '${ctx}/dictEntry/' + id + '/edit', true);
		}
	}
	
	function deleteDictEntry() {
		var row = $('#listDictEntryForm').find('#listDictEntryTable').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择数据进行操作！', 'warning');
			return false;			
		}else {					
			$.messager.confirm('确认', '确认要删除选中记录吗？', function(r) {
				if (r) {
					$.ajaxDelete('${ctx}/dictEntry', $('#listDictEntryForm').serialize(), function() {
						easyui_tabs_update('listDictEntryForm', 'layout_center_tabs');
					});
				}
			});
		}
	}
</script>