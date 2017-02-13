<#import "xygk_layout.ftl" as xygk>
<@xygk.layout>
	<div class="g-teacher-style">
     	<@channelContentDirective channelId="${channel.id}">
    		${(channelContent.content)!}
    	</@channelContentDirective>      
    </div>
    <script>
    	$("#xygkMenu li").removeClass("z-crt");
    	$("#xygkMenu #m_zzjg").addClass("z-crt");
    </script>
</@xygk.layout>