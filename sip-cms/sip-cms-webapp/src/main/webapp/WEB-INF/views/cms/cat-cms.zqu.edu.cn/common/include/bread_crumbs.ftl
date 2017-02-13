<#macro crumbs channelId>
<span>当前位置：</span>
<a href="${ctx}/cms/index" title="首页" class="u-home-ico"></a>
<@channelAllParentDirective channelId="${channelId}">
	<#if allParentChannels??>	
		<#list allParentChannels as channel>
			<span class="line">&gt;</span>
            <a href="${ctx}/cms/${channel.alias}">${channel.name}</a>
		</#list>	
	 </#if>
	 <span class="u-line">&gt;</span>
     <a href="${ctx}/cms/${currentChannel.alias!}" id="currentChannel">${currentChannel.name!}</a>	
</@channelAllParentDirective> 
</#macro>