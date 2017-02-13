<#import "szll_layout.ftl" as szll>
<@szll.layout>
	<div class="g-text-box">
     	<@channelContentDirective channelId="${channel.id}">
    		${(channelContent.content)!}
    	</@channelContentDirective>      
    </div>
    <script>
    	$("#szllMenu li").removeClass("z-crt");
    	$("#szllMenu #m_szgk").addClass("z-crt");
    </script>
</@szll.layout>

