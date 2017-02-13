<#import "common/include/simple_layout_m.ftl" as slo> 
<@slo.layout>  
    <!-- begin content -->
    <div class="g-bd article" id="content">
        <!--begin scroll wrap -->
        <div class="g-scroller-wrap" id="scroller">
            <!-- begin teacher lst -->
            <div class="g-teacher-lst">
                <ul class="m-teacher-lst office-tel" id="teacherLst">
                    <@channelContentDirective alias="fwt">
							<#if channelContent??>
								${channelContent.mobileContent!}
							</#if>
					</@channelContentDirective>
                </ul>
            </div>
            <!-- end teacher lst -->
            <div id="pullUp">  
                <div class="pullUpIcon">
                                          加载中<span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>  
                <div class="pullUpTxt">上拉显示更多...</div>  
            </div>
        </div>
        <!--end scroll wrap -->
    </div>
    <!-- end content -->
</@slo.layout> 