<#import "common/include/layout.ftl" as lo> 
<@lo.layout>
<div class="innerPage">
                <!--begin content -->
                <div class="g-auto g-cont">
                    <!--beging inner content box module -->
                    <div class="g-inner-box">
                        <div class="g-inner-dt">
                            <div class="g-crm">
	                           <#import "common/include/bread_crumbs.ftl" as cru/> 
		                       <@cru.crumbs channelId="${channel.id}"/>                                                    
	                        </div>
                            <div class="m-mod-dt1">
                                <ul class="m-people-lst">
                                	<@teachersDirective page="${page!}"  size="6">									
												<#list teacherList as teacher>
													<li>
														<a href="###" class="figure">
		                                                     <#import "../common/image.ftl" as image/>
		                                                     <@image.imageFtl url="${teacher.avatar!}" default="${ctx}/images/${mappingFolder!}/avatar.png" />
		                                                 </a>
		                                                 <h3 class="tt"><a href="###">${teacher.fullName!''}</a></h3>
		                                                 <p>${teacher.personalProfile!''}</p>
                                            		</li>
												</#list>
											    <#assign paginator=paginator>
									</@teachersDirective>                                   
                                </ul>
                            </div>
                            <form action="${ctx}/cms/sztd" method="get" id="teachersForm">
                                    <div class="m-jump-page">
                                        <#if paginator??>
		                                  <#import "../common/pagination.ftl" as page/>
									      <@page.pagination paginator=paginator pageForm="teachersForm" type="" divId=""/>
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
