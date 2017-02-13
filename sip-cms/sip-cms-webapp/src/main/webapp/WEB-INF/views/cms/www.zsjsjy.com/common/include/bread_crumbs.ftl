<#macro crumbs channelId>
<span class="u-pos">您当前的位置：</span>
<a href="${ctx}/cms/index"><i class="u-index"></i></a>
<@channelAllParentDirective channelId="${channelId}">
	<#if allParentChannels??>	
		<#list allParentChannels as channel>
			<span class="u-line">&gt;</span>
            <a href="${ctx}/cms/${channel.alias}">${channel.name}</a>
		</#list>	
	 </#if>
	 <span class="u-line">&gt;</span>
     <a href="${ctx}/cms/${currentChannel.alias!}" id="currentChannel">${currentChannel.name!}</a>	
</@channelAllParentDirective> 
</#macro>