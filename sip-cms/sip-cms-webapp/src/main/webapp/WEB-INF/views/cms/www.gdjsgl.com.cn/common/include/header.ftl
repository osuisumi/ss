			<div class="g-auto">
                <h1 class="m-logo">
                    <a href="index.html">
                        <img src="${ctx}/images/${mappingFolder}/logo.png" alt="${siteInfo.name!}">
                        <span>${siteInfo.name!}</span>
                    </a>
                </h1>
                <div class="g-nav">
                    <ul class="main-nav">
                    	<@channelsDirective>	
                    	<#list channelList as channel>
							<li class="first <#if (channel.alias == alias!"")||(channel.id==channelId!"")>class="z-crt"</#if>><a href="<#if channel.url??>${channel.url}<#else>${ctx}/cms/${channel.alias}</#if>" >${channel.name! }</a></li>
						</#list>
						</@channelsDirective>
                    </ul>
                </div>
                <div class="m-hd-srh">
                    <input type="text" placeholder="搜索关键字" class="ipt">
                    <a href="javascript:void(0);" class="submit"><i class="u-ico-srh"></i></a>
                </div>
            </div>
		