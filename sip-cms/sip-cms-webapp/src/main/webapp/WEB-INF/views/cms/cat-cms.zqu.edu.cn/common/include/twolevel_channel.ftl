 <#macro twolevelChannel alias currentAlias>
 		<ul class="m-side-nav">
               <li <#if currentAlias==alias>class="z-crt"</#if>><i></i><a href="${ctx}/cms/${alias}">全部</a></li>
     		<@channelsDirective parentAlias='${alias}'>	
			<#list channelList as channel>
				<li <#if currentAlias==channel.alias>class="z-crt"</#if>><i></i><a href="${ctx}/cms/${channel.alias}">${channel.name}</a></li>
			</#list>
			</@channelsDirective>
       </ul>
 </#macro>