<#import "common/include/layout.ftl" as lo> 
<@lo.layout>  
            <div class="g-bd-index">
                <div class="g-auto">
				 <div class="g-frame-mod">
                    <div class="g-crumbs">
                        <div class="m-crumbs">   
                        	<#import "common/include/bread_crumbs.ftl" as cru/> 
                        	<@cru.crumbs channelId="${channel.id}"/>                                                  
                            <span class="u-line">&gt;</span>
                            <em>详情</em>
                        </div><!--end m-crumbs 面包屑导航-->
                    </div><!--end g-crumbs-->
                    <div class="g-layout">
                        <div class="m-train-wrap">
                        	<@channelContentDirective channelId="${channel.id}">
									<#if channelContent??>
										${channelContent.content!}
									</#if>
							</@channelContentDirective>
                        </div>
                    </div>
                </div>
             </div> 
           </div>
</@lo.layout> 