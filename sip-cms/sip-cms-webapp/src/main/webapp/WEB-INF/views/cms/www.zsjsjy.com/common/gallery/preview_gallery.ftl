<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<link rel="Shortcut Icon" href="${ctx}/images/${ThreadContext.getDomain()}favicon.ico">
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/${ThreadContext.getDomain()}/zs-style.css">
<link href="${ctx}/js/booklet/css/jquery.booklet.1.4.0.css" rel="stylesheet" />
<title>中山教师教育</title>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/booklet/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/js/booklet/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="${ctx}/js/booklet/jquery.booklet.1.4.0.min.js"></script>
<script type="text/javascript">
$(function(){
	 $('#mybook').booklet({
        width: 1200,
        height: 800,
        pagePadding: 0,
        pageNumbers: false,
        next: "#custom-next",
        prev: "#custom-prev",
        menu: '#custom-menu',
        pageSelector: true
        //,hovers: false

    });
})
</script>
</head>
<body id="index-bd">
<!--begin my book wrap-->
<div class="myBookWrap">
    <div class="myBookOpa">
        <a href="jvascript:void(0);" id="custom-prev" class="custom-prev"></a>
        <div id="custom-menu"></div>
        <a href="jvascript:void(0);" id="custom-next" class="custom-next"></a>
    </div>
    <div id="mybook">
        <@photosDirective photoGalleryId="${id}">
        	<#list photoList as photo>    
	        	<div> 
	            	 <img src="${FileUtils.getFileUrl(photo.fileInfo.url!'')}">
	        	</div>
        	</#list>
        </@photosDirective>
    </div>   
</div>
<!--end my book wrap-->
<!--begin footer -->
         <!--begin footer -->
        <div id="g-ft">
            <div class="g-auto">
                <#include "../include/footer.ftl"/>
            </div><!--end auto layout -->
        </div>
        <!--end footer -->
<!--end footer -->
</body>
</html>
