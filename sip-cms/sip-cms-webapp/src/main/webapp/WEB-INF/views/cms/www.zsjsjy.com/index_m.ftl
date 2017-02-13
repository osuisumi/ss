<#import "common/include/layout_m.ftl" as lom> 
<@lom.layout>  
	<!--begin scroll wrap -->
        <div class="g-scroller-wrap" id="scroller">
            <!-- begin banner -->
            <div class="swiper-container g-bn" id="topBanner">
                <div class="swiper-wrapper">
                	<@bannersDirective  alias="gdtp">
                		<#if bannerList??>
						<#list bannerList as banner>
	                    <div class="swiper-slide">
	                        <a <#if banner.articleLink?? && banner.articleLink!=''>href="${banner.articleLink}" target="${banner.target!'_blank'}"<#else>href="javascript:void(0);"</#if> class="img">
	                            <img src="${FileUtils.getFileUrl(banner.imageUrl!"")}" alt="">
	                            <span class="shadow"><span class="text">${banner.title!}</span></span>
	                        </a>
	                    </div>	                   
                    	</#list>
                    	</#if>
					</@bannersDirective>
                </div>
                <div class="swiper-pagination" id="topBannerCircle"></div>
            </div>
            <!-- end banner -->
            <!-- begin quick-road -->
            <ul class="g-quick-road">
           <!--    <li>
                    <a href="${ctx}/cms/znkzx" class="block item1"><i></i><span>智能卡咨询</span></a>
                </li>-->
                <li>
                    <a href="${ctx}/cms/fwt" class="block item2"><i></i><span>服务台</span></a>
                </li>
                <li>
                    <a href="${ctx}/cms/ztbd" class="block item3"><i></i><span>专题报道</span></a>
                </li>
                <li>
                    <a href="${ctx}/cms/jyyx" class="block item4"><i></i><span>教师教育研修</span></a>
                </li>
            </ul>
            <!-- end quick-road -->
            <!-- begin menu-lst -->
            <div class="g-menu-lst">
                <ul class="m-menu-lst">
                    <li>
                        <div class="m-tt">
                            <h2 class="u-tt"><i></i>继教动态</h2>
                            <a href="${ctx}/cms/jjdt" class="u-more">更多&gt;</a>
                        </div>
                        <div class="m-con">
                        	<@articlesDirective alias="jjdt" page="1"  size="1" state="published">
                        	<#if articleList??>
								<#list articleList as article>
	                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-txt">${article.title!}</a>
	                            <div class="u-btm">
	                                <span class="u-date"><i class="u-ico-clock"></i>
	                                	<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
												${date}
										</@formatTime>
	                                </span>
	                            </div>
	                            </#list>
                            </#if>
                            </@articlesDirective>
                        </div>
                    </li>
                    <li>
                        <div class="m-tt">
                            <h2 class="u-tt"><i></i>通知公告</h2>
                            <a href="${ctx}/cms/tzgg" class="u-more">更多&gt;</a>
                        </div>
                        <div class="m-con">
                        	<@articlesDirective alias="tzgg" page="1"  size="1" state="published">
                        	<#if articleList??>
								<#list articleList as article>
	                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-txt">${article.title!}</a>
	                            <div class="u-btm">
	                                <span class="u-date"><i class="u-ico-clock"></i>
	                                	<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
												${date}
										</@formatTime>
	                                </span>
	                            </div>
	                            </#list>
                            </#if>
                            </@articlesDirective>
                        </div>
                    </li>
					<li>
                        <div class="m-tt">
                            <h2 class="u-tt"><i></i>课程体验</h2>
                            <a href="${ctx}/cms/kcty" class="u-more">更多&gt;</a>
                        </div>
                        <div class="m-con">
                        	<@articlesDirective alias="kcty" page="1"  size="1" state="published">
                        	<#if articleList??>
								<#list articleList as article>
	                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-txt">${article.title!}</a>
	                            <div class="u-btm">
	                                <span class="u-date"><i class="u-ico-clock"></i>
	                                	<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
												${date}
										</@formatTime>
	                                </span>
	                            </div>
	                            </#list>
                            </#if>
                            </@articlesDirective>
                        </div>
                    </li>
                    <li>
                        <div class="m-tt">
                            <h2 class="u-tt"><i></i>政策文件</h2>
                            <a href="${ctx}/cms/zcwj" class="u-more">更多&gt;</a>
                        </div>
                        <div class="m-con">
                        	<@articlesDirective alias="zcwj" page="1"  size="1" state="published">
                        	<#if articleList??>
								<#list articleList as article>
	                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-txt">${article.title!}</a>
	                            <div class="u-btm">
	                                <span class="u-date"><i class="u-ico-clock"></i>
	                                	<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
												${date}
										</@formatTime>
	                                </span>
	                            </div>
	                            </#list>
                            </#if>
                            </@articlesDirective>
                        </div>
                    </li>
                </ul>
            </div>
            <!-- end menu-lst -->
            <div id="pullUp">  
                <div class="pullUpIcon">
                                                 加载中<span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>  
                <div class="pullUpTxt">上拉显示更多...</div>  
            </div>
        </div>
</@lom.layout> 