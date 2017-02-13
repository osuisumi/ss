<#macro layout title="中山教师教育" openScroll=true cssArray=[] jsArray=[] pullUpEvent="" secondChannels=[]>
<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta name="keywords" content="${title}" />
<meta name="description" content="${title}" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<title>${title}</title>
<link rel="Shortcut Icon" href="${ctx}/css/cms/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/m/normalize.css">
<link rel="stylesheet" href="${ctx}/js/swiper/swiper-3.3.1.min.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/m/style.css">
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/swiper/swiper-3.3.1.jquery.min.js"></script>
<script src="${ctx}/js/iscroll/iscroll-probe.js"></script>
<script src="${ctx}/js/scroll-common.js"></script>
<script src="${ctx}/js/mobile_common.js"></script>
<script>
$(function(){
    //顶部导航左右滑动效果
    var mySwiper1 = new Swiper ('#topNav', {
        direction: 'horizontal',
        freeMode : true,
        width : 76
      });
    <#if (pullUpEvent??) && pullUpEvent!="">
    	<#assign pullUp="true"/>
    <#else>
    	<#assign pullUp="false"/>
    </#if>
      //开启模拟滚动条
    <#if openScroll>
	$("body").myScrollFn({
		//允许到底部时上拉加载更多
		pullUp: ${pullUp},
		//执行底部时上拉加载更多函数
		pullUpFn: function(){
			  <#if (pullUpEvent??) && pullUpEvent!="">
			  		${pullUpEvent};
			  </#if>         
		}
	});
	</#if>

})
</script>
</head>
<body>
<!-- begin wrapper -->
<div id="wrapper">
	<@channelsDirective>
		<#assign channelList=channelList/>
	</@channelsDirective>
	<div class="g-top-nav">
        <div class="swiper-container-horizontal swiper-container-free-mode m-top-nav" id="topNav">
            <div class="swiper-wrapper">
                 <#list channelList as channel>
                    <div class="swiper-slide"><a id="nav_${channel.alias}" href="<#if channel.url??>${channel.url}<#else>${ctx}/cms/${channel.alias}</#if>" class="nav-item<#if channel.alias == alias!""> z-crt</#if>">${channel.name! }</a></div>
				</#list>
            </div>
        </div>
        <a href="javascript:;" class="u-nav-detail" id="showSideNav"><span class="shadow"></span><span class="text"></span></a>
    </div>
    <!-- end top nav -->
    <!-- begin side nav -->
    <div class="g-sd-nav">
    	<div class="m-sd-nav" id="sideNav">
    	<#list channelList as channel>
	            <a href="<#if channel.url??>${channel.url}<#else>${ctx}/cms/${channel.alias}</#if>" <#if channel.alias == alias!"">class="z-crt"</#if>>${channel.name! }</a>
        </#list>
 		</div>
    </div>
    <!-- end side nav -->
    
    <#if (secondChannels??) && (secondChannels?size >0 )>
    	<div id="secondChannels" class="g-tit-tab tab4">
    		<#list secondChannels as channel>
				  <a href="${ctx}/cms/${channel.alias}" class="item" id="m_${channel.alias}">${channel.name}</a>
			</#list>
    	</div>
    </#if>
    <!-- begin content -->
    <div class="g-bd" id="content">
    	<#nested>
    </div>
    <!-- end content -->
    
	
    <div id="shadow"></div>
    <!-- end shadow -->
</div> 

<!-- end wrapper -->

</body>
</html>
</#macro>