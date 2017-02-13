			<div class="g-notice-lst">
                  <ul class="m-notice-lst">
                      <@articlesDirective channelId="${channel.id!}" page=param_page!"1"  size=param_size!"10" state="published">	
						<#if articleList??>								
						<#list articleList as article>
                         <li>
                           <a href="${ctx}/cms/${channel.id!}/article/${article.id!}" target="_blank"  class="block">
                              <span class="date">
                                 <b>
									<@formatTime longtime="${article.createTime!}" pattern="dd">
														${date}
									</@formatTime>
								 </b>
                                 <em><@formatTime longtime="${article.createTime!}" pattern="yyyy.MM">
														${date}
									</@formatTime></em>
                              </span>
                              <h3 class="tt">${article.title!}</h3>
                              <p>
									<#assign content=article.content?replace("<.*?>","","r")>
										<#assign content=content?replace("&nbsp;","")>
										<#assign content=content?replace(" ","")>
									<#if content?length lt 100>
											${content?substring(0,content?length)}
									<#else>
											${content?substring(0,100)}
									</#if>
							</p>
                           </a>
                         </li>
                        </#list>
						</#if>
						<#assign paginator=paginator>
					 </@articlesDirective>  
                   </ul>
            </div>
			<form action="${ctx}/cms/${channel.alias}" method="get" id="articlesForm">
                   <div id="articlesPage" class="m-laypage"></div>
						<#import "../../../common/pagination_laypage.ftl" as p/>
						<@p.pagination formId="articlesForm" divId="articlesPage" paginator=paginator />
                   </div>
            </form>