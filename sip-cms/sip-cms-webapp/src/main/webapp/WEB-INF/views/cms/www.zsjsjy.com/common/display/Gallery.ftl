<#import "../include/layout.ftl" as lo> 
<@lo.layout>        
            <div class="g-bd-index">
                <div class="g-auto">
					<div class="g-frame-mod">
                        <div class="g-crumbs spc">
                            <div class="m-crumbs">
                                <#import "../include/bread_crumbs.ftl" as cru/> 
                        		<@cru.crumbs channelId="${channel.id!}"/>                
                            </div><!--end m-crumbs 面包屑导航-->
                        </div><!--end g-crumbs-->
                        <div class="lesson-res-box">
                      		<div class="m-mod-dt">	                      		  
	                                <ul class="lesson-lst report-lst">
	                                	<@photoGalleriesDirective page="1"  size="12" relationId="${channel.id}">
	                                		<#if photoGalleryList??>
	                                			<#list photoGalleryList as photoGallery>   
				                                    <li class="lesson-li report-li">
				                                        <div class="lesson-img">
				                                            <a href="${ctx}/cms/gallery/${photoGallery.id}/preview" target="photoGallery"><img src="${FileUtils.getFileUrl(photoGallery.frontCover!'')}" alt="专题报道"></a>
				                                        </div>
				                                        <div class="lesson-info">
				                                            <a href="${ctx}/cms/gallery/${photoGallery.id}/preview" target="photoGallery" class="lesson-tit">${photoGallery.name}</a>
				                                            <p class="u-info u-time">发布时间：<@formatTime longtime="${photoGallery.createTime!}" pattern="yyyy/MM/dd">${date}</@formatTime></p>
				                                            <p class="u-operation">
				                                                <a href="${ctx}/cms/gallery/${photoGallery.id}/preview" target="photoGallery" class="btn-com u-preview"><i class="u-ico-preview"></i>预览</a>
				                                            </p>
				                                        </div>
				                                    </li>
				                             	</#list>	                                    
	                                    	</#if>
											<#assign paginator=paginator>	
                                		</@photoGalleriesDirective> 
	                                 </ul>
                      	</div>
                      	<form action="${ctx}/cms/${alias!}" method="get" id="galleryForm">
                            <div class="m-jump-page">
                            	<#if paginator??>
                                  <#import "../../../common/pagination.ftl" as page/>
							      <@page.pagination paginator=paginator pageForm="galleryForm" type="" divId=""/>
								</#if>
                          	</div>
                        </form>
                    </div>
               </div>
          </div>
</@lo.layout> 
     