<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="listMenuForm">
	<div>
		<table cellpadding="0" cellspacing="0" width="100%" style="padding: 10px;">
			<tr>
				<td colspan="6"><br />
					<button type="button" class="easyui-linkbutton" onclick="addMenu()">
						<i class="fa fa-plus"></i> 新增
					</button>
					<button type="button" class="easyui-linkbutton" onclick="editMenu()">
						<i class="fa fa-pencil"></i> 修改
					</button>
					<button type="button" class="easyui-linkbutton delete-btn" onclick="deleteMenu()">
						<i class="fa fa-minus"></i> 删除
					</button>
				</td>
			</tr>
		</table>
	</div>
	<table id="listMenuTable">
	</table>
</form>
<script>
$(function () {
	$.get("${ctx}/auth_menus?limit=9999",null,function(responseData){
		 var treeNodes=new Array()
		 for(var i =0; i < responseData.length; i++) {
			  node = responseData[i];
			  var parentId = "";
			  if(node.parent!=undefined&&node.parent!=null){
				  parentId=node.parent.id
			  }
			  var uri = "";
			  if(node.permission!=undefined&&node.permission!=null){
				  uri=node.permission.actionURI;
			  }
			  treeNodes[i]={id:node.id,name:node.name,pid:parentId,uri:uri,orderNo:node.orderNo};
		  };
		  
		  $("#listMenuTable").treegrid({
		        width: 900,
		        height: 600,
		        data:treeNodes,
		        idField: 'id',
		        treeField: 'name',
		        singleSelect:false,
		        frozenColumns: [[
		            { field: 'id', checkbox: true },
		            { field: 'name', title: '菜单名', width: 300 ,styler:function(){
		            	return 'text-align:left';
		            }}
		        ]],
		        columns: [[
					{field: 'uri', title :'链接',width: 180,},
					{field: 'orderNo', title: '序号', width: 80},
		        ]],
		        parentField: "pid"  //该属性表示支持平滑数据格式时指向父级节点idField的属性名，默认为 "pid"
		    });
		  
	})
	
});

	function addMenu() {
		var url = '${ctx}/auth_menus/create';
		easyui_modal_open('editMenuDiv', '新增菜单', 800, 300, url, true);
	}

	function editMenu() {
		var row = $('#listMenuTable').treegrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		}else if (row.length > 1) {
			$.messager.alert('提示', '不能同时编辑多条数据', 'warning');
			return false;
		}else {
			var id = row[0].id;
			easyui_modal_open('editMenuDiv', '修改菜单', 800, 300, '${ctx}/auth_menus/'+id+'/edit', true);
		}
	}
	
	function deleteMenu(){
		var row = $('#listMenuTable').treegrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		}else {
			$.messager.confirm('确认','确认要删除选中的菜单吗？',function(r){    
			    if (r){    
			    	var hasParent = false;
			    	$(row).each(function(){
			    		var parentname = this.parentname;
			    		if(parentname == ''){
			    			hasParent = true;
			    			return false;
			    		}
					});
			    	if(hasParent){
			    		$.messager.confirm('确认','选中的菜单中包含一级菜单，删除该菜单将一并删除其所有的子菜单',function(r){
			    			if(r){
			    				$.ajaxDelete('${ctx}/auth_menus/batch/delete', $('#listMenuForm').serialize(), function(){
									easyui_tabs_update('listMenuForm', 'layout_center_tabs');
								});
			    			}
			    		});
			    	}else{
			    		$.ajaxDelete('${ctx}/auth_menus/batch/delete', $('#listMenuForm').serialize(), function(){
							easyui_tabs_update('listMenuForm', 'layout_center_tabs');
						});
			    	}
			    }    
			}); 
		}
	}

</script>