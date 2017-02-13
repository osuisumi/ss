<#import "xygk_layout_m.ftl" as xygk>
<@xygk.layout openScroll=false>
			<div class="g-teacher-lst">
				<@channelContentDirective channelId="${channel.id}">
			    		${(channelContent.mobileContent)!}
			    </@channelContentDirective> 
			</div>
            
</@xygk.layout>
<script>
$(function(){
	 //组织架构滚动
    scroll();
    function scroll(){
        var window_h = $(window).height(),
            topNav_h = $(".g-top-nav").outerHeight(),
            titTab_h = $(".g-tit-tab").outerHeight(),
            con = $("#content");
            
        con.css({
            "height" : window_h - topNav_h - titTab_h + "px",
            "overflow" : "scroll"
        });
    }

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
            $(this).parent().slideUp(400,function(){
                $(this).parents("li").removeClass("z-crt");
            })
        });
    }
    $("#secondChannels a").removeClass("z-crt");
    $("#m_zzjg").addClass("z-crt");

});
</script>
