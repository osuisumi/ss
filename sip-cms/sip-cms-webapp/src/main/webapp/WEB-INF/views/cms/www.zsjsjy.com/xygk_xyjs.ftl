<#import "xygk_layout.ftl" as xygk>
<@xygk.layout>
<div class="m-infor-mn">
    <div class="infor-bn" id="infor-bn">
        <div class="g-banner-lst" id="g-banner-lst">
            <ul class="slides" id="g-img-lst">
                <@bannersDirective relationId='${channel.id}'>
					<#list bannerList as banner>	
					 <li>
                        <a <#if banner.articleLink?? && banner.articleLink!=''>href="${banner.articleLink}" target="${banner.target!'_blank'}"<#else>href="javascript:void(0);"</#if>>
                            <img src="${FileUtils.getFileUrl(banner.imageUrl!'')}" alt="" width="850" height="390">
                            <span class="shadow"></span>
                    	</a>
                     </li>
					</#list>
				</@bannersDirective>
            </ul>
        </div>
    </div>
    <div class="infor-txt">
    	<@channelContentDirective channelId="${channel.id}">
    		${(channelContent.content)!}
    	</@channelContentDirective>  
    	<div id="allmap"></div>
    </div>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpgEWenP14UNqRfpGy5sLTrNRLiXD35V"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<style type="text/css">
    #allmap {height: 500px;width:100%;overflow: hidden;}
    #result {width:100%;font-size:12px;}
</style>

<script type="text/javascript">
$(function(){
    $('#g-banner-lst').flexslider({
        animation: "slide",
        //autoPlay: true,
        slideshowSpeed: 3000,
        itemWidth: 850,
        slideshow: true,
        prevText: "prev",
        nextText: "next", 
        pauseOnHover: true,
        animationLoop: true
    });
    
    positionXAndY();        //焦点图上一页和下一页的定位

    function positionXAndY(){
        var li_length = $("#g-img-lst li").length;              //获取焦点图的张数
        var prev = $(".flex-direction-nav .flex-prev");         //获取上一页按钮
        var next = $(".flex-direction-nav .flex-next");         //获取下一页按钮
        var num = (li_length-1) * 9 + 30;                       //每次改变的大小
        prev.css({"marginLeft": - num - 10 + 'px'});            //改变上一页按钮的位置
        next.css({"marginLeft": num - 10 + 'px'});              //改变下一页按钮的位置

        if(li_length <= 1){          //如果焦点图小于一张，隐藏按钮
            prev.hide();
            next.hide();
        }
    }
    $("#xygkMenu li a").removeClass("z-crt");
    $("#xygkMenu #m_xyjs").addClass("z-crt");
}); 
</script>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map('allmap');
    var poi = new BMap.Point(113.41702,22.508514);
    map.centerAndZoom(poi, 19);
    map.enableScrollWheelZoom();

    var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
                    '<img src="../images/img-chg1.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
                    '地址：中山市东区街道博爱六路20号<br/>简介：中山市教师进修学院前身为中山市教师进修学校。原中山市教师进修学校创建于1959年。' +
                  '</div>';

    //创建检索信息窗口对象
    var searchInfoWindow = null;
    searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
            title  : "中山市教师进修学院",      //标题
            width  : 350,             //宽度
            height : 105,              //高度
            panel  : "panel",         //检索结果面板
            enableAutoPan : true,     //自动平移
            searchTypes   :[
                BMAPLIB_TAB_SEARCH,   //周边检索
                BMAPLIB_TAB_TO_HERE,  //到这里去
                BMAPLIB_TAB_FROM_HERE //从这里出发
            ]
        });
    var marker = new BMap.Marker(poi); //创建marker对象
    marker.enableDragging(); //marker可拖拽
    marker.addEventListener("click", function(e){
        searchInfoWindow.open(marker);
    })
    map.addOverlay(marker); //在地图中添加marker
    //样式1
    var searchInfoWindow1 = new BMapLib.SearchInfoWindow(map, "信息框1内容", {
        title: "信息框1", //标题
        panel : "panel", //检索结果面板
        enableAutoPan : true, //自动平移
        searchTypes :[
            BMAPLIB_TAB_FROM_HERE, //从这里出发
            BMAPLIB_TAB_SEARCH   //周边检索
        ]
    });
    function openInfoWindow1() {
        searchInfoWindow1.open(new BMap.Point(113.404998,22.520034));
    }
</script>

</@xygk.layout>
