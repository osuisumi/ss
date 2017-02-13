		<#import "../../include/form_layer.ftl" as fl>
		<@fl.form id="createResourceForm" action="${ctx}/auth_resources" method="post">
		<ul class="mis-ipt-lst">
        	<li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>资源名称：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="name" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>    
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>资源编码：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="code" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>所属站点：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-select">
                            <select name="relationId">
                            	<@siteInfosDirective page="1" size="100">
                            		<#if siteInfoList??>
									<#list siteInfoList as siteInfo>
										<option value="${siteInfo.id}">${siteInfo.name}(${siteInfo.domain})</option>
									</#list>
									</#if>
                            	</@siteInfosDirective>
                            </select>
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                     <div class="mis-ipt-row">
                           <div class="tl">
                                 <span>上级资源：</span>
                            </div>
                            <div class="tc">
                                    	<div class="mis-treeSelect">
	                                        <div class="mis-ipt-mod">
												<input type="text" id="resourceParent" readonly  class="mis-ipt"   onclick="showResource();"/>
												<a id="selectResourceBtn" href="javascript:;" class="selectBtn" onclick="showResource(); return false;">请选择</a>
	                                        </div>                                        
	                                        <div id="resourceZtreeContent" class="menuZtreeContent">
	                                        	<ul id="parentResourceTree" class="ztree selectTree"></ul>
	                                    	</div>
	                                    </div>	                                    
                                    </div>
                              </div>  
             </li>

 		</ul>
		</@fl.form>
		<#import "select_resource.ftl" as sm> 
		<@sm.selectResource resourceTreeId="parentResourceTree" onClick="selectParentResource" resourceInputId="resourceParent" resourceInputName="parent" resourceContentId="resourceZtreeContent" selectedId=pid!''>
			<script>
				function selectParentResource(e, treeId, treeNode){
						$("input[name^='parent']").remove();
						$("#resourceParent").append("<input type='hidden' name='parent.id' value='"+treeNode.id+"' />");
						$("#resourceParent").val(treeNode.name);
				}
			</script>
		</@sm.selectResource>