<#macro html title="中山教师教育">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>最新动态列表</title>
<meta name="keywords" content="中山教师教育" />
<meta name="description" content="中山教师教育"/>
<meta content="initial-scale=1,maximum-scale=1,user-scalable=no,width=device-width" name="viewport"/>
<meta name="format-detection" content="telephone=no" />
<link rel="stylesheet" href="${ctx}/css/mobile/normalize.css">
<link rel="stylesheet" href="${ctx}/css/mobile/style.css">
</head>
<body>
<!--begin wrapper -->	
<div id="wrapper">
		<!--begin header 头部-->
		<header class="g-hd" id="header">
			<h1 class="m-logo"><a href="javascript:void(0);">中山教师教育</a>
			</h1>
			<a href="javascript:void(0);" class="u-more-channel" id="showSideNavBtn">最新动态</a>
		</header>
		<!--end header 头部-->
		<div class="g-sd-nav" id="sideNav">
			<nav class="m-sd-nav">
				<a href="${ctx}/cms/index" class="item1">最新动态</a>
				<a href="${ctx}/cms/tzgg" class="item2 z-crt">通知公告</a>
				<a href="javascript:void(0);" class="item2">学院概况</a>
				<a href="javascript:void(0);" class="item3">办事指南</a>
				<a href="javascript:void(0);" class="item4">教师资格认证</a>
				<a href="javascript:void(0);" class="item5">联系我们</a>
				<a href="javascript:void(0);" class="item6">更多</a>
			</nav>
		</div>
		<!--begin .g-bd 内容-->
		<div id="content" class="g-bd">
		<!--begin scroll wrap -->
		<div id="scroller" class="g-scroller-wrap">
			<#nested> 
		</div>
		<!--end scroll wrap -->
	</div>
	<!--end content -->
</div>
<!--end wrapper -->
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/iscroll/iscroll-probe.js"></script>
<script type="text/javascript" src="${ctx}/js/scroll-common.js"></script>
<script>
	//显示侧边栏导航
function showSideNavFn(){
	// 是否在打开状态
    var ifShow = false;
    var $showSideNavBtn = $("#showSideNavBtn");
    var $sideNav = $("#sideNav");
    var $content = $("#content");
    var $newListBox = $("#newListBox");

    $showSideNavBtn.on('click',function(e){
    	var $this = $(this);
    	//制止事件冒泡
    	e.stopPropagation();
    	//判断是否在打开状态
    	if($this.hasClass('z-crt')){
            //console.log(1);
            //修改打开状态
            closeFn();
        }else {
            //修改打开状态
            openFn();
        }

        //判断点击其他地方关闭
	    $(".showSideNavHtml").off().on("click",function(event){ 
	        //兼容firefox和IE对象属性
	        e = event || window.event;
	        var target = $(e.target) || $(e.srcElement); 
	        //制止事件冒泡
	        event.stopPropagation();
	        //console.log(1);
	        //判断是否打开
	        if(ifShow){
	        	//判断是否点击的是侧边栏导航之外的元素
	            if(target.closest($sideNav).length == 0){ 
		            //执行关闭方法
		            $(".showSideNavHtml").off('click');
		            closeFn();
		        }  
	        }	        
	    }); 
    });

    //打开
    function openFn(){
    	ifShow = true;
    	$('body').addClass('showSideNavHtml');
        $showSideNavBtn.addClass('z-crt');
		$sideNav.css('right','0');
        $content.css('left','-46%');
        //添加遮罩层
        $content.prepend('<div class="m-sideNav-mask" id="sideNavMask"></div>');
        //打开时销毁iscroll滚动
        //myScroll.destroy();
    }
    //关闭
    function closeFn(){
    	ifShow = false;
        $('body').removeClass('showSideNavHtml');
        $showSideNavBtn.removeClass('z-crt');
		$sideNav.css('right','-46%');
		$content.css('left','0');
        //清除遮罩层
        $("#sideNavMask").remove();
    }
};
</script>
</#macro>
