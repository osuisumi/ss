<#import "xygk_layout_m.ftl" as xygk>
<@xygk.layout>
			<div class="g-teacher-lst">
				<@channelContentDirective channelId="${channel.id}">
			    		${(channelContent.mobileContent)!}
			    </@channelContentDirective> 
			</div>
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
</@xygk.layout>
<script>
	$(function(){
       $("#secondChannels a").removeClass("z-crt");
       $("#m_bgdh").addClass("z-crt");
	});
</script>
