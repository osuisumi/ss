<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="listResourceForm" style="height:100%">
	<div id="listPage" class="easyui-layout" data-options="fit:true"  style="width:100%;height:80%;overflow:hidden;margin:0;padding:0">
		    <div id="listTreeDivToolbar">
				<a href="javascript:void(0)" class="fa fa-plus" onclick="addResource()" title="新增组"></a>
				<a href="javascript:void(0)" class="fa fa-pencil" onclick="editResource()" title="修改组"></a>
				<a href="javascript:void(0)" class="fa fa-minus" onclick="deleteResource()" title="删除组"></a>
			</div>
            <div class="easyui-panel" data-options="region:'west',title:'权限组',collapsible:false,tools:'#listTreeDivToolbar'" style="width:25%;height:auto;"  id="listTreeDiv">
                <ul id="listTree" style="height:auto">
            	</ul>	
            </div>
            <div id="permissionList"  class="asyui-panel" data-options="region:'center',title:'权限列表'" style="width:20%;height:330px;"  >
            	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
            </div>
	</div>
</form>
<script>
$(function () {
	$.get("${ctx}/auth_resources/api?limit=9999",null,function(responseData){
		 var treeNodes=new Array()
		 console.log(responseData.length);
		 for(var i =0; i < responseData.length; i++) {
			  node = responseData[i];
			  var parentId = "";
			  if(node.parent!=undefined&&node.parent!=null){
				  parentId=node.parent.id
			  }
			  var treeNode = {id:node.id,text:node.name,pid:parentId};
			  treeNodes.push(treeNode);
		  };
		 $("#listTree").tree({
		        data:treeNodes,
		        onSelect:function(node){
		        	getCurrentTab().find("#permissionList").panel('refresh','${ctx}/auth_permissions/list?tabId=permissionList&resource.id='+node.id);
			    },
		        parentField:'pid'
		    });
	});
	
});


function addResource(){
	var url = '${ctx}/auth_resources/create';
	easyui_modal_open('editResourceDiv', '新增组', 800, 300, url, true);
}

function deleteResource(){
	var node = getCurrentTab().find('#listTree').tree('getSelected');
	$.ajaxDelete("${ctx}/auth_resources/" + node.id,null,function(response){
		if(response.responseCode == '00'){
			alert('操作成功');
			getCurrentTab().panel("refresh");
		}
	});
}

function editResource(){
	var node = getCurrentTab().find('#listTree').tree('getSelected');
	var url = '${ctx}/auth_resources/' + node.id + "/edit";
	easyui_modal_open('editResourceDiv', '修改组', 800, 300, url, true);
}

</script>