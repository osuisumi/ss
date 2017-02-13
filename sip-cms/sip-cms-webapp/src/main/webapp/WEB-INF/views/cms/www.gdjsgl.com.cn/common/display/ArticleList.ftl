<#import "../include/layout.ftl" as lo> 
<@lo.layout>        
            <div class="g-auto">
                <div class="g-crm">
                      <#import "../include/bread_crumbs.ftl" as cru/> 
                      <@cru.crumbs channelId="${channel.id!}"/>                
                </div><!--end m-crumbs 面包屑导航-->
              	<!-- start g-content -->
                <div class="g-content">
                    <!-- start g-tab-wrap -->
                    <div class="g-tab-wrap">
                        <div class="m-con-tab">
                            
                            <ul class="m-news-lst">                               	
                                	<@articlesDirective channelId="${channel.id!}" page=param_page!"1"  size=param_size!"10" state="published">									
										<#list articleList as article>
											<li>
			                                    <div class="u-date">										
													<@formatTime longtime="${article.createTime!}" pattern="dd">
														<span class="date">${date}</span>
													</@formatTime>
			                                        <@formatTime longtime="${article.createTime!}" pattern="yyyy年MM月">
														<span>${date}</span>
													</@formatTime>										
			                                    </div>
			                                    <a href="${ctx}/cms/${channel.id}/article/${article.id!}"  class="tit">${article.title!}</a>
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
									    <#assign paginator=paginator>
									</@articlesDirective>                                    
                                </ul>
                        </div>
                        <form action="${ctx}/cms/${alias!}" method="get" id="articleForm">
                         <div class="m-jump-page">
                           <#if paginator??>
                              <#import "../../../common/pagination.ftl" as page/>
							  <@page.pagination paginator=paginator pageForm="articleForm" type="" divId=""/>
							</#if>
                         </div>
                        </form>
                    </div>
                    <!-- end g-tab-wrap -->
                </div>
                <!-- end g-content -->   
 			</div>
</@lo.layout>  
