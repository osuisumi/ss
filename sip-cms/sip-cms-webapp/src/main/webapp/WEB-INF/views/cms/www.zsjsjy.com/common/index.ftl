<#import "include/layout.ftl" as lo> 
<@lo.layout>        
            <div class="g-bd-index">
                <div class="g-auto">
					<div class="g-frame-mod">
                        <div class="g-crumbs spc">
                            <div class="m-crumbs">
                                <#import "include/bread_crumbs.ftl" as cru/> 
                        		<@cru.crumbs alias="${alias}"/>                
                            </div><!--end m-crumbs 面包屑导航-->
                        </div><!--end g-crumbs-->
                      <div class="lesson-res-box">
                       	<div class="m-train-con">
                                <ul class="train-ul">                                	
                                	<@articles alias="${alias!}" page="${page!}"  size="10" state="published">
										<#if channel??>
												<#assign channel=channel>
										</#if>										
										<#list articleList as article>
											<li class="train-li">
                                             <i class="u-ico"></i>
                                             <a href="${ctx}/cms/${alias!}/article/${article.id!}" class="u-tit">${article.title!}</a>
                                             	<span class="u-time">
													<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd">
														${date}
													</@formatTime>
												</span>
                                            </li>
										</#list>
									    <#assign paginator=paginator>
									</@articles>                                    
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
<script>
    $(function(){
	    <#if channel??>
        	$("#channelName").text("${channel.name}");
		</#if>
    })
</script>
