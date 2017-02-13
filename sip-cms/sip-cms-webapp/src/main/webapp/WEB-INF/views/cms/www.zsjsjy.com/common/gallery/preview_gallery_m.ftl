<#import "../include/simple_layout_m.ftl" as slo> 
<#assign cssArray=["${ctx}/js/swiper/swiper-3.3.1.min.css"]/>
<#assign jsArray=["${ctx}/js/swiper/swiper-3.3.1.jquery.min.js"]/>
<@slo.layout showScroll=false>
    <!-- begin content -->
    <div class="g-bd report" id="content">
        <!-- begin banner -->
        <div class="swiper-container g-bn special-report" id="reportBanner">
            <div class="swiper-wrapper">
            	<@photosDirective photoGalleryId="${id}">
            		<#if photoList??>
		        	<#list photoList as photo>
		        	<div class="swiper-slide">    
			        	<div class="img">
			            	 <img src="${FileUtils.getFileUrl(photo.fileInfo.url!'')}">
			        	</div>
			        	<div class="u-text">
                    	</div>
                    </div>
		        	</#list>
		        	</#if>
		        </@photosDirective>             
            </div>
        </div>
        <!-- end banner -->
    </div>
    <!-- end content -->
<script>
$(function(){

    //banner
    var mySwiper = new Swiper ('#reportBanner', {
        direction: 'horizontal',
        loop: true,
        //autoplay: 2000,
        
        //分页器
        pagination: '#topBannerCircle',
        
      });

    $("#reportBanner").find(".swiper-slide").on("click",function(){
        $(this).find(".u-text").toggle();
    });

    //开启模拟滚动条
    // $("body").myScrollFn({
    //     //允许到底部时上拉加载更多
    //     pullUp: true,
    //     //执行底部时上拉加载更多函数
    //     pullUpFn: function(){
           
    //     }
    // });
})
</script>
</@slo.layout>