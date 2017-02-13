<#macro layout title="肇庆学院远程教育培训平台" topDivId="g-wrap-inner">
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
<meta name="author" content="">
<meta name="description" content="">
<meta name="keywords" content="">
<meta http-equiv="Window-target" content="_top">
<title>${siteInfo.name!}</title>
<link rel="Shortcut Icon" href="${ctx}/css/cms/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/p/style.css">
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
</head>
<body>
	<!--begin wrap -->
    <div id="${topDivId}">
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
                    	<#list channelList as channel>
							<li <#if (channel.alias == alias!"")||(channel.id==channelId!"")>class="z-crt"</#if>>
								<a href="<#if channel.url??>${channel.url}<#else>${ctx}/cms/${channel.alias}</#if>" >
									 <span>${channel.name! }</span>
                            		<span class="hover">${channel.name! }</span>
								</a>
							</li>
						</#list>
					</@channelsDirective>
                </ul><!--end menu module-->
                <div class="m-lr">
                    <a href="http://cat-ncts.zqu.edu.cn:10022" id="login" target="_blank" class="u-login"><i class="u-user-ico"></i>登录</a>
                    <span class="link">/</span>
                </div>
            </div><!--end auto layout -->
         </div>
        <!--end menu -->
		<!--begin body content -->
        <div id="g-bd">
             <#nested>            
        </div>
        <!--end body content -->
        <!--begin footer -->
        <div id="g-ft">
            <div class="g-auto g-ft-inner">
                <#include "footer.ftl"/>
            </div><!--end auto layout -->
        </div>
 		<!--end footer -->
    </div>
    <!--end wrap -->
</body>
</html>
</#macro>

