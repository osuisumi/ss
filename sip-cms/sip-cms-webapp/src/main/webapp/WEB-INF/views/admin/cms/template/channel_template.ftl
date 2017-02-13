<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js"]/>
<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>
		<div class="mis-index-wrap">
			<div class="mis-column-innerBox mis-block-cont">
                        <div class="mis-column-innerL">
		                    <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>栏目</span>
                                </h2>
                                 <i class="mis-go-shrink"></i>    
                            </div> 
                            <div class="mis-ztree">
                                <ul id="misZtree" class="ztree"></ul>
                            </div>                    
             			</div>
             			<div class="mis-column-innerR">
                            <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span><#if type=='admin'>后台<#else>前端</#if>模板</span>
                                </h2>                                
                            </div>
                            <div class="mis-content" id="templateContent">
                                
                            </div>                                 
                        </div>
             </div>
		</div>
		<#import "../channel/tree_click.ftl" as tc> 
		<@tc.channelTreeClick channelTreeId="misZtree" authorized=false>
			<script>
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#templateContent").load("${ctx}/cms_templates/channels/${type}/"+treeNode.id)
				}	
			</script>
		</@tc.channelTreeClick>
</@lo.layout>