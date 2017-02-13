					<@photoGalleriesDirective page="1"  size="6" relationId="${channel.id}">
                		<#if photoGalleryList??>
	                        <#list photoGalleryList as photoGallery> 
	                        	<li>
			                        <a href="${ctx}/cms/gallery/${photoGallery.id}/preview" class="block">
			                            <span class="img"><img src="${FileUtils.getFileUrl(photoGallery.frontCover!'')}" alt=""></span>
			                            <p>${photoGallery.name}</p>
			                            <p class="u-date"><i class="u-ico-clock"></i><@formatTime longtime="${photoGallery.createTime!}" pattern="yyyy/MM/dd">${date}</@formatTime></p>
			                        </a>
		                    	</li>
	                        </#list>
		                </#if>
                    </@photoGalleriesDirective> 