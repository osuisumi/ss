<#import "xygk_layout.ftl" as xygk>
<@xygk.layout>
<div class="m-infor-mn">
         <div class="organization-map" id="organization-map">
                 <@channelContentDirective channelId="${channel.id}">
			    		${(channelContent.content)!}
			    </@channelContentDirective>                          
         </div>
</div>
</@xygk.layout>
<script type="text/javascript">
$(function(){
    slideUpDown();              //组织架构伸缩效果
    function slideUpDown(){
        var block = $("#organization-map").find(".block"),
            tit = block.find(".u-tit");
        tit.on("click",function(){
            var _ts = $(this),
                txt_box = _ts.next(".txt-box"),
                ico = _ts.find(".u-ico");
            if(!txt_box.is(":animated")){
                txt_box.slideToggle();
                //_ts.parent().siblings().find(".txt-box").slideUp();
            }
            if(ico.hasClass("open")){
                ico.removeClass("open");
            }else{
                ico.addClass("open");
            }
        });
    }
 	$("#xygkMenu li a").removeClass("z-crt");
    $("#xygkMenu #m_zzjg").addClass("z-crt");
}); 
</script>