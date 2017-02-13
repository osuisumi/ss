<#macro layout title="" cssArray=[] jsArray=[]>
<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="description" content="乐高教育,乐高教育教师培训">
<meta name="keywords" content="乐高教育,乐高教育教师培训">
<meta http-equiv="Window-target" content="_top">
<title><#if title!="">${title}_</#if>${siteInfo.name}</title>
<link rel="Shortcut Icon" href="${ctx}/css/cms/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/p/style.css">
<#if cssArray??>
	<#list cssArray as css>
<link rel="stylesheet" href="${css}">	
	</#list>
</#if>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cxscroll.js"></script>
<#if jsArray??>
	<#list jsArray as js>
<script type="text/javascript" src="${js}"></script>	
	</#list>
</#if>
</head>
<body>
	<!--begin wrap -->
    <div id="g-wrap">
        <!--begin header -->
        <div id="g-hd-index">
            <#include "header.ftl"/>
        </div>
        <!--end header -->
        <!--begin menu -->
        <div id="g-menu">
            <div class="g-auto">
                <ul class="m-menu">
                    <@channelsDirective>	
                    	<#list channelList as c>
							<li <#if (c.alias == alias!"")||(c.id==channelId!"")||(c.id==(channel.parent.id)!"")>class="z-crt"</#if>>
								<a href="<#if c.url??>${c.url}<#else>${ctx}/cms/${c.alias}</#if>" >
									<span>${c.name! }</span>
									<span class="hover">${c.name! }</span>
								</a>
							</li>
						</#list>
					</@channelsDirective>
                </ul>
            </div>
        </div>
        <!--end menu -->
        <!--begin content body -->
        <div id="g-bd">
            <#nested/>
        </div>
        <!--end content body -->
        <!--begin footer -->
        <div id="g-ft">
           	<#include "footer.ftl"/>
        </div>
        <!--end footer -->


    </div>
    <!--end wrap -->


<script type="text/javascript">
$(function() { 

    //大banner轮播
    $('.banner-flexslider').flexslider({
        animation: "slide",
        slideshowSpeed: 4000,
        slideshow: true,
        pauseOnHover: true
    });
    //培训专利滚动
    $("#photoSlide").cxScroll({direction:"right",step:3});


});   

 
</script>
</body>
</html>



</#macro>