<#import "xygk_layout.ftl" as xygk>
<@xygk.layout>
	<div class="g-text-box">
     	<@channelContentDirective channelId="${channel.id}">
    		${(channelContent.content)!}
    	</@channelContentDirective>      
    </div>
    <script>
    	$("#xygkMenu li").removeClass("z-crt");
    	$("#xygkMenu #m_xyjj").addClass("z-crt");
    </script>
</@xygk.layout>