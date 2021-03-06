<#macro selectMenu channelTreeId="channelTree" channelInputId="channel" channelInputName="channels" menuContentId="menuContent" onClick="onClick" selectedId="">
								<#nested/>
								<script>
                                	var ${channelTreeId}_setting = {
										async: {
											enable: true,
											url: '${ctx}/cms_channels',
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
										}
									};
									 
									
									function showMenu() {
										var channelObj = $("#${channelInputId}");
										var channelOffset = $("#${channelInputId}").offset();
										$("#${menuContentId}").css({left:0 + "px", top: channelObj.outerHeight() - 1 + "px"}).slideDown("fast");
										$("body").bind("mousedown", onBodyDown);
									}
									function hideMenu() {
										$("#${menuContentId}").fadeOut("fast");
										$("body").unbind("mousedown", onBodyDown);
									}
									
									function onBodyDown(event) {
										if (!(event.target.id == "${channelInputId}" || event.target.id == "${menuContentId}" || $(event.target).parents("#${menuContentId}").length>0)) {
											hideMenu();
										}
									}
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
											  treeNodes[i]={id:node.id,name:node.name,pid:parentId,alias:node.alias};
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
											//zTree.updateNode(selectedNode);
										}
									}
									
                                	$(document).ready(function(){
										$.fn.zTree.init($("#${channelTreeId}"), ${channelTreeId}_setting);
										
									});
                                </script>
</#macro>