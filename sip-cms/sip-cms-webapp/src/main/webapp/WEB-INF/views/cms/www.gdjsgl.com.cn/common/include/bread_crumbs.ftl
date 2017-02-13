<#macro crumbs channelId>
<span>您当前的位置：</span>
<a href="${ctx}/cms/index"><i class="u-ico-home"></i></a>
<@channelAllParentDirective channelId="${channelId}">
	<#if allParentChannels??>	
		<#list allParentChannels as channel>
			<span class="trg">&gt;</span>
            <a href="${ctx}/cms/${channel.alias}">${channel.name}</a>
		</#list>	
	 </#if>
	 <span class="trg">&gt;</span>
	 <em><a href="${ctx}/cms/${currentChannel.alias!}" id="currentChannel">${currentChannel.name!}</a></em>
</@channelAllParentDirective> 
</#macro>