<#macro selectMenuCheckbox channelTreeId="channelTree" channelInputId="channel" channelInputName="channels" menuContentId="menuContent" checkedArray="[]">
								<script>
                                	var ${channelTreeId}_setting = {
                                		check: {
											enable: true,
											chkboxType: {"Y":"", "N":""}
										},
										async: {
											enable: true,
											url: '${ctx}/cms_channels/authorized',
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
											beforeClick: ${channelTreeId}_beforeClick,
											onCheck: ${channelTreeId}_onCheck,
											onAsyncSuccess:${channelTreeId}_onCheck
										}
									};
									 
									function ${channelTreeId}_beforeClick(treeId, treeNode) {
										var zTree = $.fn.zTree.getZTreeObj("${channelTreeId}");
										zTree.checkNode(treeNode, !treeNode.checked, null, true);
										return false;
									}
									
									function ${channelTreeId}_onCheck(e, treeId, treeNode) {
										var zTree = $.fn.zTree.getZTreeObj("${channelTreeId}"),
										nodes = zTree.getCheckedNodes(true),
										v = "";
										$("input[name^='${channelInputName}']").remove();
										for (var i=0, l=nodes.length; i<l; i++) {
											v += nodes[i].name + ",";
											inputName='${channelInputName}'+i;
											$("#${channelInputId}").append("<input type='hidden' name='${channelInputName}["+i+"].id' value='"+nodes[i].id+"' />");
										}
										if (v.length > 0 ) v = v.substring(0, v.length-1);
										var channelObj = $("#${channelInputId}");
										channelObj.attr("value", v);
									}
									
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
									
									function ${channelTreeId}_zTreeAjaxDataFilter(treeId, parentNode, responseData) {
										var treeNodes=new Array();
										var checkedArray = ${checkedArray};
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
											  if($.inArray(node.id,checkedArray)>=0){
											  	treeNodes[i]={id:node.id,name:node.name,pid:parentId,actionUri:uri,checked:true};
											  }else{
											  	treeNodes[i]={id:node.id,name:node.name,pid:parentId,actionUri:uri};
											  }
										  }
										}
										return treeNodes;
									};	
									
                                	$(document).ready(function(){
										$.fn.zTree.init($("#${channelTreeId}"), ${channelTreeId}_setting);
										${channelTreeId}_onCheck();
									});
                                </script>
</#macro>