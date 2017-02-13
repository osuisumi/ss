<#import "common/include/layout.ftl" as lo> 
<@lo.layout>        
            <div class="g-bd-index">
                <div class="g-auto">
					<div class="g-frame-mod">
                        <div class="g-crumbs spc">
                            <div class="m-crumbs">
                                <#import "common/include/bread_crumbs.ftl" as cru/> 
                        		<@cru.crumbs channelId="${channel.id!}"/>                
                            </div><!--end m-crumbs 面包屑导航-->
                        </div><!--end g-crumbs-->
                      <div class="lesson-res-box">
                        <div class="m-mod-dt">
                                <div class="m-sign-entrance">
                                    <a href="http://m.zsjsjy.com/education/undergraduateIndex.do" class="u-undergraduate" target="_blank">函授本科专科预报名 ></a>
                                    <a href="http://m.zsjsjy.com/education/graduateIndex.do" class="u-master" target="_blank">教育硕士考前辅导报名 ></a>
                                </div>
                        </div>
                       	<div class="m-train-con">
                                <ul class="train-ul">                                	
                                	<@articlesDirective channelId="${channel.id!}" page=param_page!"1"  size=param_size!"10" state="published">									
										<#list articleList as article>
											<li class="train-li">
                                             <i class="u-ico"></i>
                                             <a href="${ctx}/cms/${channel.id!}/article/${article.id!}" target="_blank" class="u-tit">${article.title!}</a>
                                             	<span class="u-time">
													<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd">
														${date}
													</@formatTime>
												</span>
                                            </li>
										</#list>
									    <#assign paginator=paginator>
									</@articlesDirective>                                    
                                </ul>
                            </div><!--end m-train-con-->
                           <form action="${ctx}/cms/${alias!}" method="get" id="articleForm">
                            <div class="m-jump-page">
                            	<#if paginator??>
                                  <#import "../common/pagination.ftl" as page/>
							      <@page.pagination paginator=paginator pageForm="articleForm" type="" divId=""/>
								</#if>
                            </div>
                             </form>
                        </div><!--end m-mod-dt-->
                      </div><!--end lesson-res-box-->
                   </div>
                </div>
            </div><!--end index body content -->
</@lo.layout>  