<#import "xygk_layout_m.ftl" as xygk>
<@xygk.layout>
			<div class="swiper-container g-bn school-info" id="topBanner">				
                <div class="swiper-wrapper">
                	<@bannersDirective relationId='${channel.id}'>
						<#list bannerList as banner>	
						 <div class="swiper-slide">
	                        <a class="img" <#if banner.articleLink?? && banner.articleLink!=''>href="${banner.articleLink}" target="${banner.target!'_blank'}"<#else>href="javascript:void(0);"</#if>>
	                            <img src="${FileUtils.getFileUrl(banner.imageUrl!'')}" alt="">
	                            <span class="shadow">${banner.title!}</span>
	                    	</a>
	                     </div>
						</#list>
					</@bannersDirective>   
                </div>
                <div class="swiper-pagination" id="topBannerCircle"></div>
            </div>
            <article class="g-school-introduce">
            	<@channelContentDirective channelId="${channel.id}">
		    		${(channelContent.content)!}
		    	</@channelContentDirective>  
            </article>
            <div id="pullUp">  
                <div class="pullUpIcon">
                    加载中<span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>  
                <div class="pullUpTxt">上拉显示更多...</div>  
            </div>
</@xygk.layout>
<script>
	$(function(){
		var mySwiper = new Swiper ('#topBanner', {
        direction: 'horizontal',
        loop: true,
        autoplay: 2000,
        
        //分页器
        pagination: '#topBannerCircle',
        
      });
       $("#secondChannels a").removeClass("z-crt");
       $("#m_xyjs").addClass("z-crt");
	})
</script>