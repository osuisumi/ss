<#macro layout pullUpEvent=""  openScroll=true>
<#assign jsArray=["${ctx}/js/jquery.dsTab.js","${ctx}/js/jquery.placeholder.min.js","${ctx}/js/myFocus/jquery.flexslider-min.js"]/>
<@channelsDirective parentAlias='xygk'>	
	<#assign channelList=channelList>
</@channelsDirective>
<#import "common/include/layout_m.ftl" as lo> 
<@lo.layout jsArray=jsArray secondChannels=channelList pullUpEvent=pullUpEvent openScroll=openScroll>  
          <div class="g-scroller-wrap" id="scroller">
          	<#nested/>
          </div>
          <script>
          		$("#content").addClass("inner");
          		$(".swiper-wrapper a").removeClass("z-crt");
          		$("#nav_xygk").addClass("z-crt");
          </script>
</@lo.layout>
</#macro>







