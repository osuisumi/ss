<#import "../common/include/layout.ftl" as lo> 
<@lo.layout>        
            <div class="g-bd-index">
                <div class="g-auto">
					<div class="g-frame-mod"> 
                      <div class="m-search-box">
                        <div class="search-tit">                        	
                            <div class="input-box">
                            	<form id="searchForm" method="get" action="${ctx}/cms/search/list">
                                <input type="text" class="u-txt" name="keyword" value="${keyword!''}" placeholder="请输入关键字">
                                <input type="submit" class="u-sch" value="搜索">
                                </form>
                            </div>
                        </div>
                        <div class="search-con"></div>
                      </div>
                      <div class="lesson-res-box">
                       	<div class="m-train-con">
                                <ul class="train-ul">                                	
                                	<@articlesSearchDirective keyword=keyword!"" page=param_page!"1" size=param_size!"10" state="published">									
										<#list articleList as article>
											<li class="train-li">
                                             <i class="u-ico"></i>
                                             <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-tit">${article.title!}</a>
                                             	<span class="u-time">
													<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd">
														${date}
													</@formatTime>
												</span>
                                            </li>
										</#list>
									    <#assign paginator=paginator>
									</@articlesSearchDirective>                                    
                                </ul>
                            </div><!--end m-train-con-->
                           <form action="${ctx}/cms/${alias!}" method="get" id="articleForm">
                            <div class="m-jump-page">
                            	<#if paginator??>
                                  <#import "../../common/pagination.ftl" as page/>
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
