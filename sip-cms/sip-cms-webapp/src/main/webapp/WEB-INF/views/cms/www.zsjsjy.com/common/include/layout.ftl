<#macro layout title="" cssArray=[] jsArray=[]>
<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><#if title!="">${title}_</#if>${siteInfo.name}</title>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="author" content="">
<meta name="description" content="${siteInfo.description!}">
<meta name="keywords" content="">
<meta http-equiv="Window-target" content="_top">
<link rel="Shortcut Icon" href="${ctx}/css/cms/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/p/zs-style.css">
<#if cssArray??>
	<#list cssArray as css>
<link rel="stylesheet" href="${css}">	
	</#list>
</#if>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<#if jsArray??>
	<#list jsArray as js>
<script type="text/javascript" src="${js}"></script>	
	</#list>
</#if>
<script type="text/javascript" charset="utf-8" src="http://lead.soperson.com/20001894/10064293.js"></script>
</head>
<body id="index-bd">
    <!--begin wrap -->
    <div id="g-wrap">
    	<#escape x as x?html>
        <!--begin header -->
        <div id="g-hd">
            <#include "header.ftl"/>
        </div>
        <!--end header -->
        <!--begin menu layout-->
        <div id="g-menu">
            <div class="g-auto">
                <ul class="m-menu">
                    <@channelsDirective>	
                    	<#list channelList as c>
							<li><a href="<#if c.url??>${c.url}<#else>${ctx}/cms/${c.alias}</#if>" <#if (c.alias == alias!"")||(c.id==channelId!"")||(c.id==(channel.parent.id)!"")>class="z-crt"</#if>>${c.name! }</a></li>
						</#list>
					</@channelsDirective>
                </ul><!--end menu module-->
                <div class="m-hd-srh">
                	<!--
                	<form id="headerSearchForm" method="get" action="${ctx}/cms/search/list">
                    <input type="text" id="bdcsMain" name="keyword"  value="${keyword!''}" class="ipt" />
                    <a href="javascript:$('#headerSearchForm').submit();" class="submit"><i class="u-search-ico"></i></a>
                    </form>-->
                </div><!--end header search -->
                <div class="m-user-plate">
                    


                </div><!--end user plate module-->
            </div><!--end auto layout -->
        </div>
        <!--end menu layout-->
        <!--begin body content -->
        <div id="g-bd">
             <#nested>            
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
</#macro>
