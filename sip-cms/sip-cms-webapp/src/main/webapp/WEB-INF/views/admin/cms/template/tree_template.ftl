<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js"]/>
<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>
		<div class="mis-index-wrap">
			<div class="mis-mod">
                    <div class="mis-mod-tt">
                        <h2 class="tt t1"><span>模板信息</span></h2>
                    </div>  
                    <div class="mis-mod-dt" style="padding-left:20px;">
                        <div class="mis-ztree">
                            <ul id="misZtree" class="ztree"></ul>
                        </div>    
                    </div>                    
             </div><!--end module layout -->
		</div>
		<script>
                                	var setting = {
                                		view: {
											addHoverDom: addHoverDom,
											removeHoverDom: removeHoverDom,
											selectedMulti: false
										},
										async: {
											enable: true,
											url: '${ctx}/cms_templates',
											type: 'get'
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
											//beforeClick: beforeClick,
											onClick: onClick
										}
									};
									function onClick(event, treeId, treeNode, clickFlag) {
										window.location.href="${ctx}/cms_templates/"+treeNode.path+"/edit";
									}
									
                                	$(document).ready(function(){
										$.fn.zTree.init($("#misZtree"), setting);
									});
         </script>
</@lo.layout>