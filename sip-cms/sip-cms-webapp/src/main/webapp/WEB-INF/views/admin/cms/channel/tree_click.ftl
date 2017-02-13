<#macro channelTreeClick channelTreeId="channelTree" onClick="onClick" selectedId="" authorized=true>
								<#nested/>
								<script>
                                	var ${channelTreeId}_Setting = {
										async: {
											enable: true,
											url: '${ctx}/cms_channels<#if authorized>/authorized</#if>',
											type: 'get',
											dataFilter: ${channelTreeId}_zTreeAjaxDataFilter,
										},
										data: {
											key: {
												name: "name"
											},
											simpleData: {
												enable: true,
												idKey: "id",
												pIdKey: "pid",
												rootPId: null
											}
										},		
										callback: {
											onClick: ${onClick},
											onAsyncSuccess: ${channelTreeId}_zTreeOnAsyncSuccess
										},
										view:{
											selectedMulti:false
										}
									};
									
									var selectedNode;
									function ${channelTreeId}_zTreeAjaxDataFilter(treeId, parentNode, responseData) {
										var treeNodes=new Array();
										if (responseData) {
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
											  if(parentId!=""){
											  	treeNodes[i]={id:node.id,name:node.name,pid:parentId,alias:node.alias};
											  }else{
											  	treeNodes[i]={id:node.id,name:node.name,pid:parentId,alias:node.alias,open:true};
											  }
											  if("${selectedId}"==node.id){
											  	selectedNode = treeNodes[i];
											  }
										  }
										}
										return treeNodes;
									};	
									
									function ${channelTreeId}_zTreeOnAsyncSuccess(){
										if(selectedNode!=null&&selectedNode!=undefined){
											var zTree = $.fn.zTree.getZTreeObj("${channelTreeId}"); 
											zTree.selectNode(selectedNode);
											zTree.updateNode(selectedNode);
										}
									}
									
                                	$(document).ready(function(){
										$.fn.zTree.init($("#${channelTreeId}"), ${channelTreeId}_Setting);
										var treeObj = $.fn.zTree.getZTreeObj("${channelTreeId}");
										treeObj.expandAll(true);
										$(".mis-shrink-tree").click();
									});
                                </script>
                                
</#macro>