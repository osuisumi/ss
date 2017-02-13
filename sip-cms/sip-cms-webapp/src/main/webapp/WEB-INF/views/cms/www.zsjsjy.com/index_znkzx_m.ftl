<#import "common/include/simple_layout_m.ftl" as slo> 
<@slo.layout>  
    <div class="g-bd article" id="content">
        <!--begin scroll wrap -->
        <div class="g-scroller-wrap" id="scroller" style="transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
            <!-- begin teacher lst -->
            <div class="g-teacher-lst">
                <ul class="m-teacher-lst organization" id="organization">
                    <@channelContentDirective channelId="${channel.id}">
						<#if channelContent??>
							${channelContent.mobileContent!}
						</#if>
					</@channelContentDirective>
                </ul>
            </div>
            <!-- end teacher lst -->
            <div id="pullUp" class="" style="display: none;">  
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
    <div class="iScrollVerticalScrollbar iScrollLoneScrollbar" style="position: absolute; z-index: 9999; width: 7px; bottom: 2px; top: 2px; right: 1px; overflow: hidden; transform: translateZ(0px); transition-duration: 500ms; opacity: 0;"><div class="iScrollIndicator" style="box-sizing: border-box; position: absolute; border: 1px solid rgba(255, 255, 255, 0.901961); border-radius: 3px; width: 100%; transition-duration: 0ms; display: block; height: 424px; transform: translate(0px, 0px) translateZ(0px); transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); background: rgba(0, 0, 0, 0.498039);"></div></div></div>
    <!-- end content -->
</div>
<!-- end wrapper -->
<script>
$(function(){
    telescopic();
    //组织架构伸缩
    function telescopic(){
        var par = $("#organization"),
            head_btn = par.find(".m-head");
            btm_btn = par.find(".u-btn");

        //点击标题，伸缩相应栏目
        head_btn.on("click",function(){
            var _ts = $(this),
                _par = _ts.parent(),
                _con = _ts.next();
            if(!(_par.hasClass("z-crt"))){
                _con.slideDown(400,function(){
                    _par.addClass("z-crt").siblings().removeClass("z-crt");
                });
                _par.siblings().find(".m-con").slideUp(400);
            }
            else{
                _con.slideUp(400,function(){
                    _par.removeClass("z-crt");
                });
            }
        });

        //点击收起按钮
        btm_btn.on("click",function(){
            $(this).parent().slideUp(400);
        });
    }

})
</script>
</@slo.layout> 