<#import "xygk_layout_m.ftl" as xygk>
<@xygk.layout pullUpEvent="nextPage()">
			<div class="g-teacher-lst">
                <ul class="m-teacher-lst" id="teacherLst">
                	<@teachersDirective page=param_page!"1"  size=param_size!"3">									
						<#list teacherList as teacher>
	                    <li>
	                        <div class="m-head">
	                            <span class="head-img"> <img src="${ctx}/images/cms/${mappingFolder!""}/avatar.png" alt=""> </span>
	                            <b>${teacher.fullName!''}</b>
	                            <p>${teacher.jobTitle!''}</p>
	                        </div>
	                        <div class="m-con">
	                            <div class="u-text">
	                                <p>${teacher.personalProfile!''}</p>
	                            </div>
	                            <div class="u-opa"></div>
	                        </div>
	                    </li>
	                    </#list>
	                    <#assign paginator=paginator>
	                 </@teachersDirective>
	           	</ul>
	         </div>
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
       $("#m_szfc").addClass("z-crt");
	});
	var  totalPages= ${paginator.totalPages};
	var currentPage = ${paginator.page};
	function nextPage(){
			if(totalPages-currentPage>0){	
		           $.get("${ctx}/cms/${channel.id}/loadMore",{page:currentPage+1,size:${paginator.limit}},function(data){
		           			$(".m-teacher-lst").append(data);
		           			currentPage=currentPage+1;
		           });
		     }
     }
</script>