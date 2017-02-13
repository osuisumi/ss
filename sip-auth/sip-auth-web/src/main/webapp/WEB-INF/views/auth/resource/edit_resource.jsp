<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="saveResourceForm" action="${ctx}/auth_resources/save" method="post">

<c:if test="${not empty resource.id }">
	<input type="hidden" name="id" value="${resource.id }"/>
	<script type="text/javascript">
	$('#saveResourceForm').attr('action','${ctx}/auth_resources/update').attr('method','put');
	</script>
</c:if>
<input type="hidden" value="test" name="relationId">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="15%">名称:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="name" class="easyui-textbox" required value="${resource.name}" style="width: 95%" /></td>
			</tr>
			<tr id="code">
				<td width="15%">code:</td>
				<td width="35%" style="text-align: left;">
					<input id="codeInput" type="text" name="code" class="easyui-textbox"  value="${resource.code}" style="width: 400px" />
				</td>
			</tr>
			<tr>
				<td width="15%">父分组:</td>
				<td width="35%" style="text-align: left">
				<input id="parentId" type="text" name="parent.id" />
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<div style="text-align: center">
		<button type="button" onclick="savePermission()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script type="text/javascript">
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
			 $("#parentId").combotree({
			 	data:treeNodes,
			 	valueField:"id",
	        	readonly:false,
	        	onLoadSuccess:function(){
	        		$('#parentId').combo('setText',"${resource.parent.name}");
	        		$('#parentId').combo('setValue',"${resource.parent.id}");
	        	},
	        	onClick:function(node){
	        		$('#parentId').combo('setText',node.text);
	        		var nowId = "${resource.id}";
	        		if(nowId == node.id){
	        			$('#parentId').combo('setText','');
	        			$('#parentId').combo('setValue','');
	        			alert('父组不能是自己');
	        		}
	        	}
			}).combobox("initClear");
		});
		
	});
	
	

	function savePermission() {
		if(!$('#savePermissionForm').form('validate')){
			return false;
		}
		var data = $.ajaxSubmit('saveResourceForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_modal_close('editResourceDiv');
				getCurrentTab().panel("refresh");
			});
		}
	}
	
</script>