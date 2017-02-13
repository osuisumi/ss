<#import "common/include/layout.ftl" as lo> 
<#setting datetime_format="yyyy年MM月dd日"/>
<@lo.layout> 
<!-- begin index page -->
            <div class="indexPage">
                <!--begin banner layout-->
                <div id="bn-layout">
                    <div class="banner-flexslider"> 
                          <ul class="slides"> 
                            <li><img src="${ctx}/images/cms/${mappingFolder}/lego-banner1.jpg" /></li> 
                            <li><img src="${ctx}/images/cms/${mappingFolder}/lego-banner2.jpg" /></li> 
                          </ul> 
                    </div>
                </div>
                <!--end banner layout-->
                <!--begin content -->
                <div class="g-index-cont">
                    <div class="g-auto m-index-contIn">
                        <div class="m-index-contL">
                        	<div class="g-tl-box">
                            <h2 class="tt"><i class="u-ico-nwes"></i>最新资讯</h2>
                            <a href="${ctx}/cms/zxzx" class="more">更多&gt;</a>
                            </div>
                            <ul class="m-rankings-lst">
                            <@articlesDirective alias="zxzx" page="1"  size="3" state="published">
            					<#if articleList??>
            					<#list articleList as article>
                                <li>
                                    <p><a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank">${article.title}</a></p>
                                    <span class="time">${article.createTime?number_to_datetime}</span>
                                </li>
                                </#list>
                                </#if>
                            </@articlesDirective>
                            </ul>
                        </div>
                        <div class="m-index-contR">
                            <h2 class="tt"><i class="u-ico-patent"></i>项目专刊</h2>
                            <div class="g-photo-slide" id="photoSlide">
                                <!--begin photo slide 项目专刊 -->  
                                <div class="g-photoSlid-box cxScrollBox">
                                    <ul class="m-photoSlide-lst cxScrollList">
                                    <@articlesDirective alias="xmzk" page="1"  size="5" state="published">
            							<#if articleList??>	
										<#list articleList as article>	
                                        <li>
                                            <div class="m-project-block">
                                                <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank" class="figure">
                                                    <img src="${FileUtils.getFileUrl(article.frontCoverImage!'')}" alt="">
                                                </a>
                                                <h3 class="tt"><a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank">${article.title!}</a></h3>
                                            </div>
                                        </li>
                                        </#list>
		                                </#if>
		                            </@articlesDirective>
                                    </ul>    
                                </div>
                                <a href="javascript:void(0);" class="prev" title=""><i></i></a>
                                <a href="javascript:void(0);" class="next" title=""><i></i></a>
                            </div><!--end photo slide 项目专刊 -->     
                        </div>                   
                    </div>
                </div>
                <!--end content -->
            </div>
            <!--end index page -->
    
</@lo.layout>   