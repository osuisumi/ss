<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="saveRoleMenuForm" action="${ctx}/auth_roles/grant/menu" method="put">
	<input type="hidden" name="id" value="${authRole.id }">
	<table id="roleMenuTree"></table>
	<br>
	<div style="text-align: center">
		<a type="button" onclick="saveRoleMenu()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</a>
	</div>
</form>
<script type="text/javascript">
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
				  treeNodes[i]={id:node.id,text:node.name,pid:parentId,uri:uri,orderNo:node.orderNo};
			  };
			  
			  $("#roleMenuTree").tree({
			        width: 900,
			        height: 600,
			        data:treeNodes,
			        checkbox:true,
			        singleSelect:false,
			        onLoadSuccess:function(node,data){
			        	getSelect()
			        },
			        parentField: "pid"  //该属性表示支持平滑数据格式时指向父级节点idField的属性名，默认为 "pid"
			    });
			  
		})
		
	});

	function getSelect(){
		var menusStr = '${menuIds}';
		var menu = $.parseJSON(menusStr);
		var rootNodes = $('#roleMenuTree').tree('getRoots');
		$.each(rootNodes,function(i,n){
			var childrens = $('#roleMenuTree').tree('getChildren',n);
			$.each(childrens,function(i,n){
				$.each(menu,function(ii,nn){
					if(n.id == nn){
						$('#roleMenuTree').tree('check',n.target);
					}
				})
			})
		})
		
	}

	function saveRoleMenu() {
		var nodes = $('#roleMenuTree').tree('getChecked',['indeterminate','checked']);
		$('#saveRoleMenuForm .menuParam').empty();
		$(nodes).each(function(i){
			$('#saveRoleMenuForm').append('<input type="hidden" name="menus['+i+'].id" value="'+this.id+'">');
		});
		var data = $.ajaxSubmit('saveRoleMenuForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_modal_close('editRoleMenuDiv');
			});
		}
	}
</script>