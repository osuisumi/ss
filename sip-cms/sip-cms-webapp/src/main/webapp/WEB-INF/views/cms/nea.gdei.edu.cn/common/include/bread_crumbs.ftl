<#macro crumbs channelId>
<span>您当前的位置：</span>
<a href="${ctx}/cms/index"><i class="u-ico-home"></i></a>
<@channelAllParentDirective channelId="${channelId}">
	<#if allParentChannels??>	
		<#list allParentChannels as channel>
			<span class="line">&gt;</span>
            <a href="${ctx}/cms/${channel.alias}">${channel.name}</a>
		</#list>	
	 </#if>
	 <span class="line">&gt;</span>
     <a href="${ctx}/cms/${currentChannel.alias!}" id="currentChannel">${currentChannel.name!}</a>	
</@channelAllParentDirective> 
</#macro>
