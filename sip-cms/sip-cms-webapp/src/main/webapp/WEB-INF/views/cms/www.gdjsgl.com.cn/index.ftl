<#import "common/include/layout.ftl" as lo> 
<@lo.layout>
            <div class="g-auto">
                <!-- start focus and login -->
                <div class="g-fcous-login">
                    <div class="g-focus" id="g-focus">
                        <ul class="m-focus">
                        		<#assign bannerCount=0>                         
                                <@bannersDirective alias="index">
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
								</@bannersDirective>
                		</ul>
                        <div class="m-bn-focus">
							<#list 1..bannerCount as c>
		                        <a href="javascript:void(0);"></a>
	                        </#list>
	                    </div>
                    </div>
                    <div class="m-login">
                        <div class="tab-tit" id="tab-tit">
                            <a href="javascript:void(0);" class="num z-crt">账号登录</a>
                        </div>
                        <div class="tab-con" id="tab-con">
                            <div class="con-box z-crt">
                                <label>
                                    <span href="javascript:void(0);"><i class="name"></i></span>
                                    <input type="text" class="ipt" placeholder="账号">
                                </label>
                                <label>
                                    <span href="javascript:void(0);"><i class="pwd"></i></span>
                                    <input type="password" class="ipt" placeholder="密码">
                                </label>
                                <a href="javascript:void(0);" class="u-btn-login">登录</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end focus and login -->
                
                <!-- start g-frame -->
                <div class="g-frame">
                    <!-- start frame-mn -->
                    <div class="g-frame-mn">
                        <div class="m-mn-mod">
                        
                         <!-- start g-smFocus-news -->
                            <div class="g-smFocus-news">
                                <div class="m-mn-tit">
                                    <h2 class="tt"><i class="u-ico-tit jj"></i>继教动态</h2>
                                    <a href="${ctx}/cms/jjdt" class="more u-btn-more">更多+</a>
                                </div>
                                <div class="m-mn-con">
                                    <div class="g-smFocus" id="g-smFocus">
                                        <ul class="m-smFocus">
                                            <@articlesDirective alias="jjdt" page="1"  size="3">
												<#list articleList as article>
												    <li>
						                                <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}">
						                                    <img src="images/banner2.jpg" alt="">
						                                    <span class="txt"><span>${article.title!}</span></span>
						                                </a>
						                            </li>												
												</#list>
											</@articlesDirective>
                                        </ul>
                                        <div class="m-smBn-focus">
                                            <a href="javascript:void(0)" class="z-crt">1</a>
                                            <a href="javascript:void(0)">2</a>
                                            <a href="javascript:void(0)">3</a>
                                        </div>
                                    </div>
                                    <div class="g-news">
                                        <ul class="m-news">
                                            
                                            <@articlesDirective alias="jjdt" page="1"  size="5">
												<#list articleList as article>
												    <li <#if article_index==0>class="first"</#if>>
												    	<#if article_index==0>
												    		<a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-tit">${article.title!}</a>
												    		<p>
												    			<#assign content=article.content?replace("<.*?>","","r")>
																<#assign content=content?replace("&nbsp;","")>
																<#assign content=content?replace(" ","")>
												    			<#if content?length lt 100>
		                                                    		${content?substring(0,content?length)}
		                                                   		<#else>
		                                                    		${content?substring(0,100)}
		                                                    	</#if>
			                                                </p>
			                                                <a href="${ctx}/cms/${article.channelss[0].id!}/article/${article.id!}" class="u-btn">[ 查看详细+ ]</a>
												    	<#else>
												    		<i class="u-ico-news"></i>
		                                                	<a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-tit">${article.title!}</a>
												    	</#if>
			                                        </li>												
												</#list>
											</@articlesDirective>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- end g-smFocus-news -->
                            
                            <!-- start train-active -->
                            <div class="g-train-avtive" id="g-train-avtive">
                                <div class="m-mn-tit spc">
                                    <h2 class="tt"><i class="u-ico-tit train"></i>培训动态</h2>
                                    <a href="${ctx}/cms/gbpx" class="more u-btn-more">更多+</a>
                                    <div class="m-tit-tab" id="m-tit-tab">
                                        <a href="javascript:void(0);" class="z-crt">干部培训<span class="u-bor bor1"></span><span class="u-bor bor2"></span></a>
                                        <a href="javascript:void(0);">教师培训<span class="u-bor bor1"></span><span class="u-bor bor2"></span></a>
                                        <a href="javascript:void(0);">校本培训<span class="u-bor bor1"></span><span class="u-bor bor2"></span></a>
                                    </div>
                                </div>
                                <div class="m-mn-con" id="m-con-tab">
                                    <!-- 干部培训 -->
                                    <ul class="m-news lst z-crt">
                                    	<@articles alias="gbpx" page="1"  size="5">
                                    		<#list articleList as article>
	                                        <li <#if (article_index+1)%2==0>class="even"</#if>>
	                                            <i class="u-ico-news"></i>
	                                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-tit">${article.title!}</a>
	                                            <span class="u-time">
                                            		<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
														${date}
													</@formatTime>
												</span>
	                                        </li>
	                                        </#list>
                                        </@articles>
                                    </ul>
                                    <!-- 教师培训 -->
                                    <ul class="m-news lst">
                                        <@articles alias="jspx" page="1"  size="5">
                                    		<#list articleList as article>
	                                        <li <#if (article_index+1)%2==0>class="even"</#if>>
	                                            <i class="u-ico-news"></i>
	                                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-tit">${article.title!}</a>
	                                            <span class="u-time">
                                            		<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
														${date}
													</@formatTime>
												</span>
	                                        </li>
	                                        </#list>
                                        </@articles>
                                        
                                    </ul>
                                    <!-- 校本培训 -->
                                    <ul class="m-news lst">
                                        <@articles alias="xbpx" page="1"  size="5">
                                    		<#list articleList as article>
	                                        <li <#if (article_index+1)%2==0>class="even"</#if>>
	                                            <i class="u-ico-news"></i>
	                                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-tit">${article.title!}</a>
	                                            <span class="u-time">
                                            		<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
														${date}
													</@formatTime>
												</span>
	                                        </li>
	                                        </#list>
                                        </@articles>
                                        
                                    </ul>
                                </div>
                            </div>
                            <!-- end train-active -->
                            
                            <!-- start province-train-pro -->
                            <div class="g-provice-train">
                                <div class="m-mn-tit">
                                    <h2 class="tt"><i class="u-ico-tit pro"></i>省级培训项目</h2>
                                </div>
                                <div class="m-mn-con">
                                    <div class="province-pro-box" id="province-pro-box">
                                        <ul class="m-pro-lst  slides">                 
			                                <@bannersDirective alias="pxxm">
												<#list bannerList as banner>	
												  <li>
		                                                <a <#if banner.articleLink?? && banner.articleLink!=''>href="${banner.articleLink}" target="${banner.target!'_blank'}"<#else>href="javascript:void(0);"</#if> class="img">
		                                                    <img src="${FileUtils.getFileUrl(banner.imageUrl!"")}" alt="">
		                                                </a>
		                                                <p class="txt">
		                                                    <a href="javascript:void(0);">${banner.title!}</a>
		                                                </p>
	                                            	</li> 
												</#list>
											</@bannersDirective>                                   
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- end province-train-pro -->
                		</div>
                  </div>
                  
                  <!-- start frame-sd -->
                    <div class="g-frame-sd">
                        <div class="m-sd-mod">
                            <!-- start file-dld -->
                            <div class="g-file-dld">
                                <div class="m-sd-tit">
                                    <h3 class="tt">文件下载</h3>
                                    <a href="javascript:void(0);" class="more u-btn-more">更多+</a>
                                </div>
                                <div class="m-sd-con">                                	
                                    <ul class="m-news lst">
                                    	<@resourcesDirective alias="wjxz">
                                        <li>
                                            <i class="u-ico-news"></i>
                                            <a href="javascript:void(0);" class="u-tit">深圳龙岗幼儿教师培训</a>
                                        </li>
                                        </@resourcesDirective>
                                    </ul>
                                </div>
                            </div>
                            <!-- end file-dld -->
                            <!-- start help-center -->
                            <div class="g-help-center">
                                <div class="m-sd-tit">
                                    <h3 class="tt">帮助中心</h3>
                                    <a href="${ctx}/cms/cjwt" class="more u-btn-more">更多+</a>
                                </div>
                                <div class="m-sd-con">
                                    <ul class="m-news lst">
                                        
                                        <@articles alias="cjwt" page="1"  size="6">
                                    		<#list articleList as article>
                                    		<li>
	                                            <i class="u-ico-news"></i>
	                                            <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="u-tit">${article.title!}</a>
                                        	</li>
	                                        </#list>
                                        </@articles>
                                    </ul>
                                </div>
                            </div>
                            <!-- end help-center -->
                            <!-- start technology-maintain -->
                            <div class="g-technology-maintain">
                                <div class="m-sd-tit">
                                    <h3 class="tt">技术维护</h3>
                                   
                                </div>
                                <div class="m-sd-con">
                                    <ul class="m-info-lst">
                                        <li>
                                            <div class="icos"><i class="u-ico-tips tel"></i></div>
                                            <p class="u-tit">联系电话</p>
                                            <p class="u-txt">020-34115861</p>
                                        </li>
                                        <li>
                                            <div class="icos"><i class="u-ico-tips home"></i></div>
                                            <p class="u-tit">联系单位</p>
                                            <p class="u-txt">广东省第二师范学院远程教育中心</p>
                                        </li>
                                        <li>
                                            <div class="icos"><i class="u-ico-tips email"></i></div>
                                            <p class="u-tit">电子邮箱</p>
                                            <p class="u-txt">newt1211@163.com</p>
                                        </li>
                                        <li>
                                            <div class="icos"><i class="u-ico-tips address"></i></div>
                                            <p class="u-tit">通信地址</p>
                                            <p class="u-txt">广州市新港中路351号广东第二师范学院6栋</p>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- end technology-maintain -->
                        </div>
                    </div>
                    <!-- end frame-sd -->
                  </div>
                <!-- end g-frame -->
            </div>       
</@lo.layout>  
<script type="text/javascript" src="${ctx}/js/jquery.dsTab.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="${ctx}/js/myFocus/jquery.flexslider-min.js"></script>
<script type="text/javascript">
 $(function(){
        //big banner
        $('#g-focus').dsTab({
            itemEl        : '.m-focus li',
            btnElName     : 'm-bn-focus',
            btnItem       : 'a',
            currentClass  : 'z-crt',
            maxSize       : 5,
            changeType    : 'fade',
            change        : true,
            changeTime    : 3000,
            autoCreateTab : false
        });

        //继教 banner
        $('#g-smFocus').dsTab({
            itemEl        : '.m-smFocus li',
            btnElName     : 'm-smBn-focus',
            btnItem       : 'a',
            currentClass  : 'z-crt',
            maxSize       : 5,
            changeType    : 'fade',
            change        : true,
            changeTime    : 2500,
            autoCreateTab : false
        });

        //培训动态
        $("#g-train-avtive").myTab({
            pars    : '#g-train-avtive',
            tabNav  : '#m-tit-tab',
            li      : 'a',       //标签
            tabCon  : '#m-con-tab',
            tabList : '.m-news',
            cur     : 'z-crt'
        });

        //省级培训项目
        $('#province-pro-box').flexslider({
            animation: "slide",
            slideshowSpeed: 3000,
            itemWidth: 202,
            itemMargin: 40,
            slideshow: true,
            prevText: "prev",
            nextText: "next", 
            pauseOnHover: true
        });

        //账号登录与二维码登录切换
        loginTab();
        function loginTab(){
            var btn = $("#tab-tit a");
            var con = $("#tab-con .con-box");
            btn.on("click",function(){
                var _index = $(this).index();
                $(this).addClass("z-crt").siblings().removeClass("z-crt");
                con.eq(_index).addClass("z-crt").siblings().removeClass("z-crt");
            })
        }
    })
</script>

