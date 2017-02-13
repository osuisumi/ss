				<@resourcesDirective relationId="${channel.id!}" page=param_page!'1'  size=param_size!"6">
	                 <#if resourceList??>
		             <#list resourceList as resource>   
	                    <li>
	                        <a href="${FileUtils.getFileUrl(resource.url!'')}" class="block">
	                            <span class="img"><img src="${FileUtils.getFileUrl(resource.frontCover!'')}" alt=""></span>
	                            <p>${resource.name}</p>
	                        </a>
	                    </li>
	                  </#list>
	                  </#if> 
	            </@resourcesDirective> 