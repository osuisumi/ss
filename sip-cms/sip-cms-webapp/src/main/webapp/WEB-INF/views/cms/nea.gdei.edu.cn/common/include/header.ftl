		<div class="g-auto">
            <h1 class="m-logo">
                <a href="${ctx}/cms/index">
                    <img src="${ctx}/images/cms/${mappingFolder}/logo.png" alt="广东第二师范学院网络教育学院">
                </a>
            </h1>
            <div class="m-menu">
            	<@channelsDirective>	
                    	<#list channelList as c>
							<a href="<#if c.url??>${c.url}<#else>${ctx}/cms/${c.alias}</#if>" <#if (c.alias == alias!"")||(c.id==channelId!"")||(c.id==(channel.parent.id)!"")>class="z-crt"</#if>>${c.name! }</a>
						</#list>
				</@channelsDirective>
            </div>    
        </div>