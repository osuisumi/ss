<#import "xygk_layout.ftl" as xygk>
<@xygk.layout>
<div class="m-infor-mn">
	<@channelContentDirective channelId="${channel.id}">
    		${(channelContent.content)!}
    </@channelContentDirective>                              
</div><!--end m-infor-mn-->
</@xygk.layout>
<script type="text/javascript">
$(function(){
    $('#g-banner-lst').flexslider({
        animation: "slide",
        //autoplay: false,
        //slideshowSpeed: 3000,
        itemWidth: 850,
        slideshow: false,
        prevText: "prev",
        nextText: "next", 
        pauseOnHover: true,
        animationLoop: false
    });
    
     $("#xygkMenu li a").removeClass("z-crt");
     $("#xygkMenu #m_bgdh").addClass("z-crt");
}); 
</script>