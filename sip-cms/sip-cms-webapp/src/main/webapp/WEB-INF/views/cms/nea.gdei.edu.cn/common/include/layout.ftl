<#macro layout title="" cssArray=[] jsArray=[]>
<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title><#if title!="">${title}_</#if>${siteInfo.name}</title>
<link rel="Shortcut Icon" href="${ctx}/css/cms/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/p/neat.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder}/p/portals.css">
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
</head>
<body id="portalsBody">
<!--begin wrap 网站容器-->
<div id="wrap">
    <!--begin top layout 顶部 -->

    <!--end top layout 顶部-->
    <!--begin header 头部 -->
    <div class="g-hd">
    	<#include "header.ftl"/>        
    </div>
    <!--end header 头部-->
    
    <#nested/>
    
    <div id="side-opa">
        <div class="item item1">
            <a href="javascript:;" class="block block1"></a>
            <a href="javascript:;" class="block block2"></a>
        </div>
        <div class="line"></div>
        <div class="item item2">
            <a href="javascript:;" class="block block1"></a>
            <a href="javascript:;" class="block block2"></a>
        </div>
    </div>
    
    <!--begin footer 底部 -->
    <div class="g-ft">
    	<#include "footer.ftl"/>
       
    </div>
    <!--end footer 底部-->
</div>
<!--end wrap 网站容器-->
<script>
$(function(){
    //index banner
    (function(){
        var indexBn = $("#indexBn"),
            bnImg = indexBn.find(".m-bn-img"),
            bnImg_li = bnImg.find("li"),
            bnDescr1 = indexBn.find(".m-bn-descr1").find("li"),
            bnDescr2 = indexBn.find(".m-bn-descr2").find("li"),
            btnPrev = indexBn.find(".prev"),
            btnNext = indexBn.find(".next"),
            page_now = indexBn.find(".now"),
            page_all = indexBn.find(".all");

        //滚动的距离
        var off_w = bnImg_li.width();

        //图片的个数
        var length = bnImg_li.length;

        //初始显示索引
        var index = 0;
        if(index < 0 || index >= length){
            index = 0;
        }
        else{
            //当前页
            page_now.text(index + 1);
        }

        //总页数
        page_all.text(length);

        var timer = null;

        //自动切换
        timer = setInterval(auto,3000);

        function scroll(){
            page_now.text(index + 1);
            bnImg.stop().animate({
                "left" : -off_w * index + "px"
            },350);
            bnDescr1.eq(index).stop().fadeIn().siblings().fadeOut();
            bnDescr2.eq(index).stop().fadeIn().siblings().fadeOut();
        }

        function auto(){
            index++;
            if(index >= length){
                index = 0;
            }
            scroll();
        }

        //鼠标移上去清除定时器
        indexBn.mouseenter(function(){
            clearInterval(timer);
        }).mouseleave(function(){
            timer = setInterval(auto,2000);
        });

        //上一页
        btnPrev.on("click",function(){
            index--;
            if(index < 0){
                index = length - 1;
            }
            scroll();
        });

        //下一页
        btnNext.on("click",function(){
            auto();
        });

    })()
})
</script>
</body>
</html>
</#macro>