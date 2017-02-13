
            <ul class="mis-tree" id="misTree">
            	<@authMenuTree userId="${ThreadContext.getUser().getId()}">
					<#list authMenus as authMenu>
			              <li id="m_${authMenu.id}" class="mis-tree-item <#if authMenu_index &lt; 8>t${authMenu_index+1}<#else>t${authMenu_index-7}</#if>">
			                    <div class="mis-tree-info" data-open="false">
			                        <a href="<#if authMenu.permission.actionURI??>${ctx}${authMenu.permission.actionURI}?menuTreeId=m_${authMenu.id}<#else>javascript:;</#if>" class="dt" title="">
			                            <span class="txt">${authMenu.name}</span>
										 <#if authMenu.children?? && authMenu.children?size gt 0> 
			                            <span class="open-icon"></span>
										</#if>
			                        </a>
			                    </div>
			                    <#if authMenu.children?? && authMenu.children?size gt 0> 
			                    <ul class="mis-tree-inner">	
			                    <!-- 递归  宏定义 -->
							    <#macro menuTree children>
							       <#if children?? && children?size gt 0>
								       <#list children as child>
									        <#if child.children?? && child.children?size gt 0>
									       
									        	<li id="m_${child.id}" class="inner-item">
						                            <div class="inner-info" data-open="false">
						                                <a href="<#if child.permission.actionURI??>${ctx}${child.permission.actionURI}?menuTreeId=m_${child.id}<#else>javascript:;</#if>" class="dt"><span class="txt">${child.name}</span></a>
						                            </div>
										            <ul class="mis-tree-inner tree-bottom">
										            	<@menuTree children=child.children />
										            </ul>
										        </li>
									        <#else>
									        	<li id="m_${child.id}" class="inner-item">
				                                    <div class="inner-info">
				                                        <a href="<#if child.permission.actionURI??>${ctx}${child.permission.actionURI}?menuTreeId=m_${child.id}<#else>javascript:;</#if>" class="dt"><span class="txt">${child.name}</span></a>
				                                    </div>
			                                	</li>  
									        </#if>
								       </#list>
						    	 </#if>
						      </#macro>
						      <@menuTree children=authMenu.children />
								</ul>
							  </#if>	
			                </li>
		            </#list>
               </@authMenuTree>
            </ul>