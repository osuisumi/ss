<#import "../include/layout.ftl" as lo> 
<@lo.layout>               
            <div class="g-bd-index">
                <div class="g-auto">
					<div class="g-frame-mod">
                        <div class="g-crumbs spc">
                            <div class="m-crumbs">
                                <#import "../include/bread_crumbs.ftl" as cru/> 
                        		<@cru.crumbs channelId="${channel.id!}"/>     
                            </div><!--end m-crumbs 面包屑导航-->
                        </div><!--end g-crumbs-->
	                    <div class="lesson-res-box">
	                      	<div class="m-mod-dt">	                      		  
	                                <ul class="m-book-lst">
	                                	<@resourcesDirective relationId="${channel.id!}" page="${page!'1'}"  size="15">
	                                		<#if resourceList??>
		                                		<#list resourceList as resource>    
				                                    <li>
				                                        <div class="book-block">
				                                        	<a href="${FileUtils.getFileUrl(resource.url!'')}" target="_blank" class="figure">
				                                                <img src="${FileUtils.getFileUrl(resource.frontCover!'')}">
				                                            </a>
				                                            <a href="${FileUtils.getFileUrl(resource.url!'')}" target="_blank"  class="tit">${resource.name}</a>				                                            
				                                        </div>
				                                    </li>
				                             	</#list>	                                    
	                                    	</#if>
                                		</@resourcesDirective> 
	                                 </ul>
	                     </div>		
                          <!--end m-train-con-->
                           <form action="${ctx}/cms/${alias!}" method="get" id="resourceForm">
                            <div class="m-jump-page">
                            	<#if paginator??>
                                  <#import "../../../common/pagination.ftl" as page/>
							      <@page.pagination paginator=paginator pageForm="resourceForm" type="" divId=""/>
								</#if>
                            </div>
                            </form>
                      </div><!--end lesson-res-box-->
                   </div>
                </div>
            </div><!--end index body content -->
</@lo.layout>
