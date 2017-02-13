<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="saveRolePermissionForm" style="width: 900px; height: 600px" action="${ctx}/auth_roles/grant/permission" method="put">
	<input type="hidden" name="id" value="${authRole.id }">
	<table id="rolePermissionTree"></table>
	<br>
	<div style="text-align: center">
		<a type="button" onclick="saveRolePermission()" class="easyui-linkbutton"> <i class="fa fa-floppy-o"></i> 保 存
		</a>
	</div>
</form>
<script type="text/javascript">

	$(function(){
		$.get("${ctx}/auth_resources/api?limit=9999",null,function(responseData){
			 var treeNodes=new Array()
			 console.log(responseData.length);
			 for(var i =0; i < responseData.length; i++) {
				  node = responseData[i];
				  var parentId = "";
				  if(node.parent!=undefined&&node.parent!=null){
					  parentId=node.parent.id
				  }
				  var treeNode = {id:node.id,text:node.name,pid:parentId,type:'R'};
				  treeNodes.push(treeNode);
				  if(node.permissions){
					  for(var j=0;j<node.permissions.length;j++){
						  permission = node.permissions[j];
						  var treePermissionNode = {id:permission.id,text:permission.name,pid:node.id,type:'P'};
						  treeNodes.push(treePermissionNode);
					  }
				  }
			  };
			  $("#rolePermissionTree").tree({
				     data:treeNodes,
			         idField: 'id',
			         treeField: 'text',
			         remoteSort: false,
			         autoRowHeight: true,
			         singleSelect:true,
			         fitColumns:true,
			         checkbox:true,
			         formatter:function(node){
			        	 return node.text;
			         },
			         onLoadSuccess:function(node,data){
			        	 getSelect();
			         },
			         parentField:"pid"
			    });
		});
		
	})

	function getSelect(){
		var permissionIdsStr = '${permissionIds}';
		var permissionIds = $.parseJSON(permissionIdsStr);
		var rootNodes = $('#rolePermissionTree').tree('getRoots');
		$.each(rootNodes,function(i,n){
			var childrens = $('#rolePermissionTree').tree('getChildren',n);
			$.each(childrens,function(i,n){
				$.each(permissionIds,function(ii,nn){
					if(n.id == nn){
						$('#rolePermissionTree').tree('check',n.target);	
					}
				}
				)
			})
		})
	}

	function saveRolePermission() {
		var nodes = $('#rolePermissionTree').tree('getChecked',['indeterminate','checked']);
		$.each(nodes,function(i){
			$('#saveRolePermissionForm').append('<input type="hidden" name="permissions['+i+'].id" value='+this.id+'>');
		});
		var data = $.ajaxSubmit('saveRolePermissionForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_modal_close('editRolePermissionDiv');
			});
		}
	}
</script>