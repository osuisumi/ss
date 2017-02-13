<#import "common/include/layout.ftl" as lo> 
<@lo.layout> 
<div class="innerPage">
                <!--begin content -->
                <div class="g-auto g-cont">
                    <!--beging inner content box module -->
                    <div class="g-inner-box">
                        <div class="g-inner-dt">
                            <div class="g-lyt-frame f-cb">
                                <div class="g-crm">
		                           <#import "common/include/bread_crumbs.ftl" as cru/> 
		                           <@cru.crumbs channelId="${channel.id}"/>                                                       
		                        </div>
                                <div class="m-mod-dt1">
                                    <div class="g-lyt-sd">
                                       <#import "common/include/twolevel_channel.ftl" as tc> 
                                       <@tc.twolevelChannel alias="cgzs" currentAlias="${alias}"/>
                                    </div>
                                    <div class="g-lyt-mn">
                                        <div class="g-lmain-cont">
                                            <!-- <h2 class="m-lmain-tt">全部成果展示</h2> -->
                                            <div class="cont">
                                                <ul class="m-ach-lst small">
													<@articlesDirective channelId="${channel.id!}" getAllChildren="true" page="${page!}"  size="10" state="published">									
															<#if articleList??>	
															<#list articleList as article>
							                                   <li class="item1">
                                                        			<span class="figure">成果</span>							                                    	
							                                        <h3 class="tt"><a href="${ctx}/cms/${channel.id!}/article/${article.id!}">${article.title!}</a></h3>
							                                        <p class="txt">
							                                        			<#assign content=article.content?replace("<.*?>","","r")>
																				<#assign content=content?replace("&nbsp;","")>
																				<#assign content=content?replace(" ","")>
												                                <#if content?length lt 100>
												                                     ${content?substring(0,content?length)}
												                                <#else>
												                                     ${content?substring(0,100)}
												                                </#if>
												                     </p>
												                     <span class="u-be-type type1">${article.channels[0].name!}</span>
							                                    </li>
							                                 </#list>
							                                 <#assign paginator=paginator>
							                                 </#if>														    
													</@articlesDirective>
                                            </div>
                                        </div>
                                    </div>
                                </div>                                
                            </div><!--end layout frame-->
                        </div>
                    </div>
                    <!--end inner content box module -->
                    
                </div>
                <!--end content -->
            </div>
</@lo.layout> 