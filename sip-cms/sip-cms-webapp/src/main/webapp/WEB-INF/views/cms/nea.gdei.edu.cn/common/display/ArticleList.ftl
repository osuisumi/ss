<#import "../include/layout.ftl" as lo> 
<#assign jsArray=["${ctx}/js/laypage/laypage.js"]/>
<@lo.layout jsArray=jsArray>  
	<div class="g-bd" id="innerContent">
        <div class="g-auto">
            <div class="g-content notice">
                <div class="g-iframe f-cb">
                    <div class="m-crm plt">
                         <#import "../include/bread_crumbs.ftl" as cru/> 
                        <@cru.crumbs channelId="${channel.id!}"/>  
                    </div>
                    <div class="g-mn-mod">
                        <div class="g-notice-lst">
                            <ul class="m-notice-lst">
								<@articlesDirective channelId="${channel.id!}" page=param_page!"1"  size=param_size!"10" state="published">	
										<#if articleList??>						
										<#list articleList as article>
		                                <li>
		                                    <a href="${ctx}/cms/${channel.id!}/article/${article.id!}" target="_blank"  class="block">
		                                        <span class="date">
		                                            <b><@formatTime longtime="${article.createTime!}" pattern="dd">
														${date}
													</@formatTime></b>
		                                            <em><@formatTime longtime="${article.createTime!}" pattern="yyyy.MM">
														${date}
													</@formatTime></em>
		                                        </span>
		                                        <h3 class="tt">${article.title!}</h3>
		                                        <p><#assign content=article.content?replace("<.*?>","","r")>
															<#assign content=content?replace("&nbsp;","")>
															<#assign content=content?replace(" ","")>
															<#if content?length lt 70>
					                                              ${content?substring(0,content?length)}
					                                        <#else>
					                                              ${content?substring(0,70)}...
					                                        </#if></p>
		                                    </a>
		                                </li>  
		                                </#list>
										</#if>
									    <#assign paginator=paginator>
								</@articlesDirective>                              
                            </ul>
                        </div>
                        <!-- begin jump page -->
                        
                        <div class="g-jump-page">
                            <form action="${ctx}/cms/${channel.alias}" method="get" id="articleListForm">
                                <div id="articleListPage" class="m-laypage"></div>
									<#import "../../../common/pagination_laypage.ftl" as p/>
									<@p.pagination formId="articleListForm" divId="articleListPage" paginator=paginator />
                                </div>
                            </form>
                        </div>
                        <!-- begin jump page -->
                    </div>
                </div>  
            </div>
        </div>
    </div>      
</@lo.layout>  
