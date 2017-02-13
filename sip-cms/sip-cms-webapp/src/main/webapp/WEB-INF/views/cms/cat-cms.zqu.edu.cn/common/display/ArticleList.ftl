<#import "../include/layout.ftl" as lo> 
<@lo.layout>        
<div class="innerPage">
                <!--begin content -->
                <div class="g-auto g-cont">
                    <!--beging inner content box module -->
                    <div class="g-inner-box">
                        <div class="g-inner-dt">
                        	<div class="g-crm">
                                <#import "../include/bread_crumbs.ftl" as cru/> 
                        		<@cru.crumbs channelId="${channel.id!}"/>                
                            </div><!--end m-crumbs 面包屑导航-->
                         	<div class="m-mod-dt1">
                                <ul class="m-date-lst itemRow">                               	
                                	<@articlesDirective channelId="${channel.id!}" page=param_page!"1"  size=param_size!"10" state="published">	
										<#if articleList??>								
										<#list articleList as article>
											<li>
		                                    	<span class="date">
		                                        <@formatTime longtime="${article.createTime!}" pattern="MM月">
													${date}
												</@formatTime>
												<@formatTime longtime="${article.createTime!}" pattern="dd">
												<span>${date}</span>
												</@formatTime>
												</span>
		                                        <h3 class="tt"><a href="${ctx}/cms/${channel.id!}/article/${article.id!}">${article.title!}</a></h3>
		                                        <p class="txt">
		                                        			<#assign content=article.content?replace("<.*?>","","r")>
															<#assign content=content?replace("&nbsp;","")>
															<#assign content=content?replace(" ","")>
							                                <#if content?length lt 150>
							                                     ${content?substring(0,content?length)}
							                                <#else>
							                                     ${content?substring(0,150)}
							                                </#if>
							                     </p>
		                                    </li>
										</#list>
										</#if>
									    <#assign paginator=paginator>
									</@articlesDirective>                                    
                                </ul>
                            </div><!--end m-train-con-->
                           <form action="${ctx}/cms/${alias!}" method="get" id="articleForm">
                            <div class="m-jump-page">
                            	<#if paginator??>
                                  <#import "../../../common/pagination.ftl" as page/>
							      <@page.pagination paginator=paginator pageForm="articleForm" type="" divId=""/>
								</#if>
                            </div>
                             </form>
                         </div>
                    </div>
                    <!--end inner content box module -->
                    
                </div>
                <!--end content -->
            </div>
</@lo.layout>  
