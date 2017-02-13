<#assign jsArray=["${ctx}/js/jquery.dsTab.js","${ctx}/js/jquery.placeholder.min.js","${ctx}/js/myFocus/jquery.flexslider-min.js"]/>
<#import "common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>          
            <div class="g-bd-index">
                <div class="g-layout1">
                    <div class="g-auto">
                        <div id="g-bn">
                             <ul class="m-bn">   
                                <#assign bannerCount=0>                         
                                <@bannersDirective alias="gdtp">
                                	<#if bannerList??>
									<#list bannerList as banner>	
									 <li>
										<a <#if banner.articleLink?? && banner.articleLink!=''>href="${banner.articleLink}" target="${banner.target!'_blank'}"<#else>href="javascript:void(0);"</#if>>
	                                        <img src="${FileUtils.getFileUrl(banner.imageUrl!"")}" alt="" width="720" height="375">
	                                        <span class="shadow"></span>
	                                        <span class="txt">${banner.title!}</span>
                                    	</a>
									  </li>
									  <#assign bannerCount=bannerCount+1>         
									</#list>
									</#if>
								  </@bannersDirective>
	                            </ul>
	                            <div class="m-bn-focus">
									<#list 1..bannerCount as c>
		                                <a href="javascript:void(0);"></a>
	                                </#list>
	                            </div>
                        </div>
                        <!--end banner -->
                        <div class="g-notice-plate">
                            <div class="g-notice-box">
                                <div class="m-mod-tt1">
                                    <h2 class="tt">继教动态</h2>
                                    <a href="${ctx}/cms/jjdt" class="more">更多&gt;</a>
                                </div>
                                <div class="m-mod-dt2">
                                    <ul class="m-iNotice-lst">
	                                    <@articlesDirective alias="jjdt" page="1"  size="4" state="published">
											<#list articleList as article>
											    <li>
		                                            <span class="u-notice-dot"></span>
		                                            <span class="time">
		                                            <@formatTime longtime="${article.createTime!}" pattern="MMMM">
														${date}
													</@formatTime>
													<@formatTime longtime="${article.createTime!}" pattern="dd">
														<strong>${date}</strong>
													</@formatTime>
													</span>
		                                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" target="_blank">${article.title!}</a>
		                                        </li>													
											</#list>
										</@articlesDirective>
                                    </ul>
                                </div>
                            </div><!--end notice box -->
                            <ul class="m-tool-nav">
                                <li class="item4">
                                    <a href="${ctx}/cms/znkzx">
                                        <i class="icon"></i>智能卡咨询
                                    </a>
                                </li>
                                <li class="item3">
                                    <a href="javascript:void(0);">
                                        <i class="icon"></i>快速通道
                                    </a>
                                    <div class="m-toolNav-box row5">
                                        <i class="trg"></i>
                                        <div class="tg"></div>
                                        <div class="lst">
                                            <@channelContentDirective alias="kstd">
												<#if channelContent??>
													${channelContent.content!}
												</#if>
											</@channelContentDirective>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div><!--end notice plate -->
                    </div><!--end auto layout -->      
                </div><!--end layout block -->
                <div class="g-layout">
                    <div class="g-auto g-layout-cont">                    
                        <div class="g-mn-mod">
                            <div class="g-large-lt">
                                <div class="g-mn-tit">
                                    <h2 class="u-tt notice"><i class="u-ico"></i>通知公告</h2>
                                    <span class="u-line">|</span>
                                    <span class="sm-tt">NOTICE</span>
                                    <a href="${ctx}/cms/tzgg" class="u-more">更多&gt;</a>
                                </div>
                                <div class="g-mn-dt">
                                    <ul class="m-news-ul">
                                    	<li>
                                    	<@articlesDirective alias="tzgg" page="1"  size="2" state="published">
											<#list articleList as article>
												<#if article_index == 0>  
													<div class="u-date">
														<@formatTime longtime="${article.createTime!}" pattern="dd">
															<b>${date}</b>
														</@formatTime>
			                                            <@formatTime longtime="${article.createTime!}" pattern="MMMM">
															<span>${date}</span>
														</@formatTime>
													</div>
													<div class="u-top">
		                                                <h4 class="news-tit">
		                                                    <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" target="_blank">${article.title!}</a>
		                                                </h4>
		                                                <p class="new-con">
		                                                	<#assign content=article.content?replace("<.*?>","","r")>
															<#assign content=content?replace("&nbsp;","")>
															<#assign content=content?replace(" ","")>
		                                                    <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}">
		                                                    	<#if content?length lt 100>
		                                                    		${content?substring(0,content?length)}
		                                                    	<#else>
		                                                    		${content?substring(0,100)}
		                                                    	</#if>
		                                                    </a>
		                                                </p>
		                                            </div>
												<#else>
													<p class="sm-tit">
		                                                <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}">${article.title!}</a>
		                                                <span class="u-time">
		                                                	<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">${date}</@formatTime>
		                                                </span>
		                                                <span class="u-cover"></span>
		                                            </p>
												</#if>
											</#list>
										</@articlesDirective>
										</li>
                                    </ul>
                                </div>
                            </div>
                            
                            <div class="g-large-rt">
                                <div class="g-mn-tit">
                                    <h2 class="u-tt exp"><i class="u-ico"></i>课程体验</h2>
                                    <span class="u-line">|</span>
                                    <span class="sm-tt">EXPERIENCE</span>
                                    <a href="${ctx}/cms/kcty" class="u-more">更多&gt;</a>
                                </div>
                                <div class="g-mn-dt">
                                    <ul class="m-news-ul">
                                        <@articlesDirective alias="kcty" page="1"  size="2" state="published">
											<#list articleList as article>
												<#if article_index == 0>  
													<div class="u-date">
														<@formatTime longtime="${article.createTime!}" pattern="dd">
															<b>${date}</b>
														</@formatTime>
			                                            <@formatTime longtime="${article.createTime!}" pattern="MMMM">
															<span>${date}</span>
														</@formatTime>
													</div>
													<div class="u-top">
		                                                <h4 class="news-tit">
		                                                    <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}">${article.title!}</a>
		                                                </h4>
		                                                <p class="new-con">
		                                                	<#assign content=article.content?replace("<.*?>","","r")>
															<#assign content=content?replace("&nbsp;","")>
															<#assign content=content?replace(" ","")>
		                                                    <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" target="_blank">
		                                                    	<#if content?length lt 50>
		                                                    		${content?substring(0,content?length)}
		                                                    	<#else>
		                                                    		${content?substring(0,50)}
		                                                    	</#if>
		                                                    </a>
		                                                </p>
		                                            </div>
												<#else>
													<p class="sm-tit">
		                                                <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" target="_blank">${article.title!}</a>
		                                                <span class="u-time">
		                                                	<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">${date}</@formatTime>
		                                                </span>
		                                                <span class="u-cover"></span>
		                                            </p>
												</#if>
											</#list>
										</@articlesDirective>
										</li>
                                    </ul>
                                </div>
                            </div>
                            
                        </div>
                        
                        
                        <div class="g-mn-mod">
                            <div class="g-sm-lt">
                                <div class="g-mn-tit">
                                    <h2 class="u-tt spe"><i class="u-ico"></i>专题报道</h2>
                                    <span class="u-line">|</span>
                                    <span class="sm-tt">SPECIAL COVERAGE</span>
                                    <a href="${ctx}/cms/ztbd" class="u-more">更多&gt;</a>
                                </div>
                                <div class="g-mn-dt">
                                	<@photoGalleriesDirective page="1"  size="1" relationId="">
                                		<#if photoGalleryList??>
	                                		<#list photoGalleryList as photoGallery>                                			
		                                		<a href="${ctx}/cms/gallery/${photoGallery.id}/preview" target="photoGallery"  class="m-spc-rpt">
		                                        	<img src="${FileUtils.getFileUrl(photoGallery.frontCover!'')}" alt="专题报道">
		                                    	</a>
	                                    	</#list>	                                    
                                    	</#if>
                                	</@photoGalleriesDirective>                        
                                </div>
                            </div>
                            <div class="g-sm-ctr">
                                <div class="g-mn-tit">
                                    <h2 class="u-tt plc"><i class="u-ico"></i>政策文件</h2>
                                    <span class="u-line">|</span>
                                    <span class="sm-tt">POLICE PAPER</span>
                                    <a href="${ctx}/cms/zcwj" class="u-more">更多&gt;</a>
                                </div>
                                <div class="g-mn-dt">
                                    <ul class="m-plc-lst">
                                    	<@articlesDirective alias="zcwj" page="1"  size="5" state="published">
											<#list articleList as article>
												<li>
												 <#if article_index==0>
												 	<span class="u-num fir">
												 <#elseif article_index==1>
												 	<span class="u-num sec">
												 <#elseif article_index==2>
												 	<span class="u-num thir">
												 <#else>
												   <span class="u-num">
												 </#if>	                                             
	                                             0${article_index+1}</span>
	                                             <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" target="_blank" class="txt">${article.title!}</a>
	                                            </li>
											</#list>
										</@articlesDirective>
                                    </ul>
                                </div>
                            </div>
                            <div class="g-sm-rt">
                                <div class="g-mn-tit">
                                    <h2 class="u-tt cov"><i class="u-ico"></i>教师教育研修</h2>
                                    <span class="u-line">|</span>
                                    <span class="sm-tt">COVERAGE</span>
                                    <a href="${ctx}/cms/jyyx" class="u-more">更多&gt;</a>
                                </div>
                                <div class="g-mn-dt">
                                	<@resourcesDirective page="1"  size="2" type="pdf">
                                		<#if resourceList??>
                                		<div class="m-book-ul">
	                                		<#list resourceList as resource>                                			
		                                    	<a href="${FileUtils.getFileUrl(resource.url!'')}" target="_blank" class="book-li">
			                                        <img src="${FileUtils.getFileUrl(resource.frontCover!'')}" alt="教师教育研修">
			                                        <span class="txt">${resource.name}</span>
			                                    </a>
	                                    	</#list>
	                                     </div>	                                    
                                    	</#if>
                                	</@resourcesDirective>  
                                </div>
                            </div>
                        </div>
                    </div><!--end auto layout -->
			 </div>
            </div><!--end index body content -->
</@lo.layout>  
<script type="text/javascript">
$(function(){
    $('input, textarea').placeholder();

    $("#u-login").bind('click',function(){
        if($("#m-login-wrap").is(":visible")){
            $("#m-login-wrap").hide();
        }else{
            $("#m-login-wrap").show();
        }
        
    }).bind('mouseover',function(){
        $("#m-login-wrap").show();
    }).bind('mouseout',function(){
        $("#m-login-wrap").hide();
    });

    $('#g-bn').dsTab({
        itemEl        : '.m-bn li',
        btnElName     : 'm-bn-focus',
        btnItem       : 'a',
        maxSize       : 5,
        currentClass  : 'z-crt', //按钮当前样式
        autoCreateTab : false,
        changeType    : 'fade',
        change        : true,
        changeTime    : 3100
    });
    $('#myFocus').flexslider({
        animation: "slide",
        slideshowSpeed: 3000,
        itemWidth: 260,
        slideshow: true,
        prevText: "prev",
        nextText: "next", 
        pauseOnHover: true
    });

    $("#m-menu-tit .u-tit").on('click',function(){
        var _this = $(this);
        var index = _this.index();
        _this.addClass("z-crt").siblings().removeClass("z-crt");
        $("#m-menu-con .con-lst").eq(index).addClass("z-crt").siblings().removeClass("z-crt");
    });
}); 
</script>

