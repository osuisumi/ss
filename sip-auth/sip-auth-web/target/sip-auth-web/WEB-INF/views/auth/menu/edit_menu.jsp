<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="saveMenuForm" action="${ctx}/auth_menus/save" method="post">
<input type="hidden" name="relationId" value="test">
<c:if test="${not empty menu.id }">
<input id="id" type="hidden" name="id" value="${menu.id }">
</c:if>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="15%">菜单名称:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="name" class="easyui-textbox" required value="${menu.name}" style="width: 95%" /></td>
			</tr>
		<%-- 	<c:choose>
				<c:when test="${empty menu.id }">
				<tr>
					<td width="15%">唯一标识:</td>
					<td width="35%" style="text-align: left;"><input type="text" name="id" class="easyui-textbox" required style="width: 95%;" /></td>
				</tr>
				</c:when>
			</c:choose> --%>
			<tr>
				<td width="15%">排序:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="orderNo" value="${menu.orderNo }" class="easyui-textbox"  style="width: 95%;" /></td>
			</tr>
			<tr>
				<td width="15%">父菜单:</td>
				<td width="35%" style="text-align: left;">
					<input id="parentId" name="parent.id">
				</td>
			</tr>
			<tr>
				<td width="15%">访问地址:</td>
				<td width="35%" style="text-align: left">
					<input class=""  id="permissionId" name="permission.id">
				</td>
			</tr>
			<!-- <tr>
				<td width="15%">图标:</td>
				<td>请选择</td>	
			</tr> -->
		</tbody>
	</table>
	<br>
	<div style="text-align: center">
		<button type="button" onclick="saveMenu()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script type="text/javascript">
	$(function(){
		$.get("${ctx}/auth_resources/api?limit=99999",null,function(responseData){
			var treeNodes = new Array();
			for(var i =0; i < responseData.length; i++) {
				  node = responseData[i];
				  var parentId = "";
				  var uri = "";
				  if(node.parent!=undefined&&node.parent!=null){
					  parentId=node.parent.id
				  }
				  resourceNode = {id:node.id,name:node.name,pid:parentId,uri:uri};
				  treeNodes.push(resourceNode);
				  //treeNodes[i]={id:node.id,name:node.name,pid:parentId};
				  if(node.permissions){
					  for(var j = 0;j<node.permissions.length;j++){
						  permission = node.permissions[j];
						  permissionNode = {id:permission.id,name:permission.name,pid:node.id,uri:permission.actionURI};
						  treeNodes.push(permissionNode);
					  }
				  }
			};
			
			$('#permissionId').combotree({
				data:treeNodes,
				valueField:'id',    
				textField:'name',
				width:300,
				parentField:"pid",
				formatter:function(row){
					return row.name;
				},
				onClick:function(node){
					if(node.uri){
						$('#permissionId').combo('setText',node.uri);
						$('#permissionId').combo('setValue',node.id);
					}else{
						$('#permissionId').combo('setValue',"");
					}
				},
				onLoadSuccess:function(){
					var permissionId = "${menu.permission.id}";
					var permissionActionURI = "${menu.permission.actionURI}";
					if(permissionId){
						$('#permissionId').combo('setText',permissionActionURI);
						$('#permissionId').combo('setValue',permissionId);
					}
				}
			}).combobox("initClear");
		});
	})

	$(function(){
		$.get("${ctx}/auth_menus?limit=9999",null,function(responseData){
			 var treeNodes=new Array()
			 for(var i =0; i < responseData.length; i++) {
				  node = responseData[i];
				  var parentId = "";
				  if(node.parent!=undefined&&node.parent!=null){
					  parentId=node.parent.id
				  }
				  treeNodes[i]={id:node.id,name:node.name,pid:parentId};
			  };
			  
			  $('#parentId').combotree({
				  	data: treeNodes, 
				    valueField:'id',
				    textField:'name',
				    parentField: 'pid',
		        	formatter:function(node){
		        		return node.name;
		        	},
		        	onLoadSuccess:function(){
		        		$('#parentId').combo('setText',"${menu.parent.name}");
		        		$('#parentId').combo('setValue',"${menu.parent.id}");
		        	},
		        	onClick:function(node){
		        		$('#parentId').combo('setText',node.name);
		        		var nowId = "${menu.id}";
		        		if(nowId == node.id){
		        			$('#parentId').combo('setText','');
		        			$('#parentId').combo('setValue','');
		        			alert('父菜单不能是自己');
		        		}
		        	}
				}).combobox("initClear");
		});
	})

	function saveMenu() {
		var id = $('#id').val();
		if(id){
			$('#saveMenuForm').attr('action','${ctx}/auth_menus/update');
			$('#saveMenuForm').attr('method','put');
		}
		if(!$('#saveMenuForm').form('validate')){
			return false;
		}
		var data = $.ajaxSubmit('saveMenuForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_tabs_update('listMenuForm', 'layout_center_tabs');
				easyui_modal_close('editMenuDiv');
			});
		}else{
			alert(json.responseMsg);
		}
	}
	
	function showIcons(){
		 $.easyui.icons.showSelector({
             onEnter: function (val) { alert(val); }
         });
	}
</script>