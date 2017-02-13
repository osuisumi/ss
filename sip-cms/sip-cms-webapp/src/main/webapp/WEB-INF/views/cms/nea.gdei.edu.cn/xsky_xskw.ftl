<#import "xsky_layout.ftl" as xsky>
<@xsky.layout>	
	<div class="g-teacher-style">
                                <div class="g-photo-lst">
                                    <ul class="m-photo-lst periodical">
                                    	<@resourcesDirective relationId="${channel.id!}" page="${page!'1'}"  size="15">
	                                		<#if resourceList??>
		                                		<#list resourceList as resource>    
				                                    <li>
				                                        <span class="img">
				                                        	<a href="${FileUtils.getFileUrl(resource.url!'')}" target="_blank" class="figure">
				                                                <img src="${FileUtils.getFileUrl(resource.frontCover!'')}">
				                                            </a>			                                            
				                                         </span>
				                                         <p>${resource.name}</p>
				                                    </li>
				                             	</#list>	                                    
	                                    	</#if>
                                		</@resourcesDirective>                                      
                                    </ul>
                                </div>
                                <!-- begin jump page -->
                                <form action="${ctx}/cms/${channel.alias}" method="get" id="xskwForm">
                                <div id="xskwPage" class="m-laypage"></div>
									<#import "../common/pagination_laypage.ftl" as p/>
									<@p.pagination formId="xskwForm" divId="xskwPage" paginator=paginator />
                                </div>
                                </form>
                                <!-- begin jump page -->
                            </div>
    <script>
    	$("#xskyMenu li").removeClass("z-crt");
    	$("#xskyMenu #m_xskw").addClass("z-crt");
    </script>
</@xsky.layout>