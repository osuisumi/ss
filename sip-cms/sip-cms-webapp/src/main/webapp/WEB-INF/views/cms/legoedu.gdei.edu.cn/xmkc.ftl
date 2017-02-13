<#import "common/include/layout.ftl" as lo> 
<@lo.layout>  
 <div class="g-project-introduce">
                <div class="bottom-bg"></div>
                <div class="m-pro-discrible-cont g-auto">
                    <ul class="m-courseList">
						 <@articlesDirective alias="xmkc"  state="published">
            							<#if articleList??>	
										<#list articleList as article>	
                                        <li>
                                            <div class="img">
                                                <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank">
                                                    <img src="${FileUtils.getFileUrl(article.frontCoverImage!'')}" alt="">
                                                </a>
                                            	<span class="tip">${article.title}</span> 
                                            </div> 
                                            <h3><a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank">${article.subTitle!}</a></h3>
				                            <div class="more">
				                                <span class="time"><i></i>
				                                <@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
														${date}
											   </@formatTime></span>
				                                <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank">查看详情&gt;</a>
				                            </div>                           
                                        </li>
                                        </#list>
		                                </#if>
		                </@articlesDirective>
                    </ul>
                </div>
            </div>			
</@lo.layout>    

