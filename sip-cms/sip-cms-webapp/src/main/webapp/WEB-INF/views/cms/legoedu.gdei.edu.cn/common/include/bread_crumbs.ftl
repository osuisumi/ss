<#macro crumbs channelId>
<span class="u-pos">您当前的位置：</span>
<@channelAllParentDirective channelId="${channelId}">
	<#if allParentChannels??>	
		<#list allParentChannels as channel>			
            <a href="${ctx}/cms/${channel.alias}">${channel.name}</a>
            <ins>&gt;</ins>
		</#list>	
	 </#if>
     <a href="${ctx}/cms/${currentChannel.alias!}" id="currentChannel">${currentChannel.name!}</a>	
</@channelAllParentDirective> 
</#macro>