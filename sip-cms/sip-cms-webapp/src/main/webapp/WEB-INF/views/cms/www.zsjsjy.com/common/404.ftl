<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="author" content="">
<meta name="description" content="">
<meta name="keywords" content="">
<meta http-equiv="Window-target" content="_top">
<link rel="Shortcut Icon" href="${ctx}/css/${ThreadContext.getDomain()}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/cms/${ThreadContext.getDomain()}/p/zs-style.css">
<title>${siteInfo.name!}</title>
</head>
<body id="maintain">
    <!--begin wrap -->
    <div id="g-wrap">
    	<#escape x as x?html>
        <!--begin header -->
	    <div id="g-hd">
	        <div class="g-hd-b">
	            <div class="g-auto">
	                <h1 id="m-logo">
	                    <a href="${ctx}/cms/index">
	                        <img src="${ctx}/images/cms/${ThreadContext.getDomain()}/web-logo.png" alt="${siteInfo.name!}"><span class="hide-txt">${siteInfo.name!}</span>
	                    </a>
	                </h1>
	            </div><!--end auto layout -->    
	        </div>
	    </div>
	    <!--end header -->
        <!--begin body content -->
	    <div id="g-bd">
	        <div id="g-maintain-img">404！您所访问的页面不存在！</div>
	    </div>
	    <!--end body content -->
        </#escape>
        <!--begin footer -->
        <div id="g-ft">
            <div class="g-auto">                
					<ul class="m-ft-nav">
				 		${siteInfo.footerHtml!}
					</ul>
					<div class="m-ft-info">
	                    <div class="tdc">
	                    	<#if siteInfo.weixinQrcode??>
	                        <img src="${FileUtils.getFileUrl(siteInfo.weixinQrcode!)}" alt="">
	                        </#if>
	                    </div>
	                    <div class="info">
	                        <p>扫一扫右侧二维码，关注我们</p>
	                        <p>${siteInfo.copyRight!}<br/>${siteInfo.icp!}</p>
	                    </div>
	                </div>
            </div><!--end auto layout -->
        </div>
        <!--end footer -->
    </div>
    <!--end wrap -->
</body>
</html>
