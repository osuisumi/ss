<#import "common/include/layout.ftl" as lo> 
<#import "../common/image.ftl" as image/>
<@lo.layout topDivId="g-wrap">
<!-- begin index page -->
            <div class="indexPage">
                <!--begin banner layout-->
                <div id="bn-layout">
                    <div id="g-bn" class="g-auto g-auto1">
                    <@articlesDirective alias="tpxw" page="1"  size="5" state="published">
                    	<#assign imageArticleList=articleList/>
                    	<ul class="m-bn m-bn-bg">
                    	<#list articleList as article>                        
                        	<li class="item">
                                <a href="${ctx}/cms/${article.channels[0].id}/${article.id!}" class="img">
                                    <img src="${FileUtils.getFileUrl(article.frontCoverImage!"")}" alt="">
                                </a>
                            </li>				
						</#list>
						</ul>
						<ul class="m-bn-descr">
						<#list articleList as article>						
                            <li class="ex <#if article_index==0>crt</#if>">
                                <h3 class="title"><a href="${ctx}/cms/${article.channel.alias!}/article/${article.id!}">${article.title}</a></h3>
                                <p class="txt">
                                	<a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}">
                                		<#assign content=article.content?replace("<.*?>","","r")>
															<#assign content=content?replace("&nbsp;","")>
															<#assign content=content?replace(" ","")>
															<#if content?length lt 70>
					                                              ${content?substring(0,content?length)}
					                                        <#else>
					                                              ${content?substring(0,70)}...
					                                        </#if>
                                	</a>
                                </p>
                            </li>						 
                        </#list>
                        </ul>
                     </@articlesDirective>
                        <div class="m-play-opt">
                            <a href="javascript:void(0);" class="prev"></a>
                            <a href="javascript:void(0);" class="play"></a>
                            <a href="javascript:void(0);" class="next"></a>
                        </div>
                    </div>
                    <div id="g-bn2" class="g-auto g-auto2">
                        <ul class="m-bn m-bn-bg2">
                        	<#if imageArticleList?size gt 1>
                        		<#assign size=imageArticleList?size/>
                        		<#assign article=imageArticleList[size-1]/>
                        		<li class="item">
		                                 <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="img">
		                                    <img src="${FileUtils.getFileUrl(article.frontCoverImage!"")}" alt="">
		                                 </a>
		                        </li>     
		                        <#list imageArticleList as article>
		                            <li class="item">
		                                 <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="img">
		                                    <img src="${FileUtils.getFileUrl(article.frontCoverImage!"")}" alt="">
		                                 </a>
		                            </li> 
                            	</#list>                 		
                            	<#assign article=imageArticleList[size-2]/>
                        		<li class="item">
		                                 <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}" class="img">
		                                    <img src="${FileUtils.getFileUrl(article.frontCoverImage!"")}" alt="">
		                                 </a>
		                        </li> 
		                       
                        	</#if>                                                               
                        </ul>                                                    
                    </div>
                </div>
			 	<!--end banner layout-->
			 	
			 	<!--begin content -->
                <div class="g-auto g-cont">
                    <!--beging content box module -->
                    <div class="g-box">
                        <div class="g-box-mn">
                            <div class="g-module">
                                <div class="m-mod-tt">
                                    <i class="u-ico-notice"></i>
                                    <h2 class="tt spc">通知公告</h2>
                                    <a href="${ctx}/cms/tzgg" class="more">更多<i class="u-more-ico"></i></a>
                                </div>
                                <div class="m-mod-dt">
                                    <ul class="m-date-lst itemFloat">
                                    	<@articlesDirective alias="tzgg" page="1"  size="4" state="published">
												<#list articleList as article>
												    <li>
			                                            <span class="date">
			                                            		<@formatTime longtime="${article.createTime!}" pattern="MM">
																	${date}月
																</@formatTime>
																<@formatTime longtime="${article.createTime!}" pattern="dd">
																		<span>${date}</span>
																</@formatTime>													
														</span>
			                                            <h3 class="tt"><a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}">${article.title!}</a></h3>
			                                            <p class="txt">
			                                            	<#assign content=article.content?replace("<.*?>","","r")>
															<#assign content=content?replace("&nbsp;","")>
															<#assign content=content?replace(" ","")>
															<#if content?length lt 70>
					                                              ${content?substring(0,content?length)}
					                                        <#else>
					                                              ${content?substring(0,70)}
					                                        </#if>
					                                     </p>
			                                        </li>												
												</#list>
										</@articlesDirective>
									 </ul><!--end text date list -->
                                </div>
                            </div><!--end module 通知公告-->
                        </div><!--end box main -->
                        
                        
                        <div class="g-box-sd">
                            <div class="g-module">
                                <div class="m-mod-tt">
                                    <i class="u-ico-active"></i>
                                    <h2 class="tt spc">项目动态</h2>
                                    <a href="${ctx}/cms/xmdt" class="more">更多<i class="u-more-ico"></i></a>
                                </div>
                                <div class="m-mod-dt">
                                    <ul class="m-rankings-lst">
                                    	<@articlesDirective alias="xmdt" page="1"  size="3" state="published">
												<#list articleList as article>
													<li class="no-1">
			                                            <span class="num">0${article_index+1}<i></i></span> 
			                                            <h3 class="tt"><a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}">${article.title!}</a></h3>
			                                        </li>
												</#list>
										</@articlesDirective>
                                        </ul><!--end rankings list -->
                                </div>
                            </div><!--end module 项目动态-->
                        </div><!--end box side -->
                    </div>
                    <!--end content box module -->
                    
                    <!--beging content box module -->
                    <div class="g-box">
                        <div class="g-box-tt">
                            <h2 class="tt"><span class="shadow"></span><span class="text">培训项目</span></h2>
                        </div>
                        <div class="g-box-dt">
                            <div class="g-photo-slide" id="photoSlide1">
                                <div class="g-photoSlid-box cxScrollBox">
                                    <ul class="m-photoSlide-lst cxScrollList">
                                    	<@projectsDirective>
                                    		<#list projects as project>
	                                        <li>
	                                            <div class="m-project-block">
	                                                <a href="###" target="_blank" class="figure">
	                                                    <@image.imageFtl url="${project.infoImage!}" default="${ctx}/images/${mappingFolder}/project.jpg" />
	                                                </a>
	                                                <h3 class="tt"><a href="###" target="_blank">${project.name}</a></h3>	                                                
	                                            </div>
	                                        </li>
	                                        </#list>
                                        </@projectsDirective>
                                     </ul>   
                                </div>
                                <a href="javascript:void(0);" class="prev" title=""></a>
                                <a href="javascript:void(0);" class="next" title=""></a>
                            </div><!--end photo slide 培训项目 -->
                        </div>
                    </div>
                    <!--end content box module -->
                    
                    <!--beging content box module -->
                    <div class="g-box">
                        <div class="g-box-mn">
                            <div class="g-module">
                                <div class="m-mod-tt">
                                    <i class="u-ico-lesson"></i>
                                    <h2 class="tt spc">推荐课程</h2>
                                </div>
                                <div class="m-mod-dt" id="m-lesson-lst">
                                    <ul class="m-course-lst nop">
                                    	<@recommendCoursesDirective channelAlias="tjkc" size="6">      
                                    	<#assign rcindex=-1/>                              	
                                    	<#list recommendCourseList as rc>
                                    		<#if rc.recommend?? && rc.recommend.id??>
                                    			<#assign rcindex=(rcindex+1)/>    
	                                    		<#if rcindex==0 || (rcindex+1)%2==1>
		                                        	<li>
		                                        </#if>
		                                            <div class="m-course-block">
		                                                <a href="###" class="figure">	                                                	
		                                                    <@image.imageFtl url="${rc.courseImage!}" default="${ctx}/images/${mappingFolder}/course.png" />
		                                                    <span class="ex"></span>
		                                                    <span class="period">${rc.studyHours!}</span>
		                                                </a>
		                                                <div class="txt">
		                                                   <h3 class="tt"><a href="###" title="${rc.courseName!}">${rc.courseName!}</a></h3>
		                                                    <div class="info">
		                                                        <div class="m-grade">
		                                                            <span class="star in"></span>
		                                                            <span class="star in"></span>
		                                                            <span class="star in"></span>
		                                                            <span class="star in"></span>
		                                                            <span class="star"></span>
		                                                        </div>
		                                                        <span class="name">${rc.author!}</span>
		                                                    </div> 
		                                                </div>
		                                            </div>                                           
		                                        <#if (rcindex+1)%2==0>
		                                        	</li>
		                                        </#if>
		                                     </#if>
                                        	</#list>
                                        </@recommendCoursesDirective>
                                      </ul>
                                </div>
                            </div><!--end module 推荐课程-->
                        </div><!--end box main -->
                        
                        
                        <div class="g-box-sd">
                            <div class="g-module">
                                <div class="m-mod-tt">
                                    <i class="u-ico-work"></i>
                                    <h2 class="tt spc">推荐工作坊</h2>
                                </div>
                                <div class="m-mod-dt" id="m-turn-box">
                                    <div class="g-recommend-work" id="recommend-work">
                                        <ul class="m-turn-lst slides">
                                        	<@recommendWorkshopsDirective channelAlias="tjgzf" size="4">                                	
	                                    	<#list recommendWorkshopList as rc>
	                                    		<#if rc.recommend?? && rc.recommend.id??>
	                                    			<#assign rcindex=(rcindex+1)/>    
		                                    		
		                                            <li>
		                                                <div class="m-turn-block no-1">
		                                                    <div class="turn-b">
		                                                        <h3 class="tt"><a href="###">${rc.workshopName}</a></h3>
		                                                        <p class="info1">
		                                                            <span></span>
		                                                            <span>创建于2015/03/21</span>
		                                                        </p>
		                                                        <!--
		                                                        <div class="info2">
		                                                            <span class="i">
		                                                                <span><i class="u-sUser-ico"></i></span>18
		                                                            </span>
		                                                            <span class="i">
		                                                                <span><i class="u-sLink-ico"></i></span>35
		                                                            </span>
		                                                            <span class="i">
		                                                                <span><i class="u-sBulb-ico"></i></span>24
		                                                            </span>
		                                                        </div>-->
		                                                    </div>
		                                                </div>
		                                            </li>
		                                        </#if>
	                                        </#list>
                                            </@recommendWorkshopsDirective>
                                           </ul><!--end rankings list -->
                                    </div>
                                </div>
                            </div><!--end module 项目动态-->
                        </div><!--end box side -->
                    </div>
                    <!--end content box module -->
                </div>
                <!--end content -->
            </div>
            <!--end index page -->                       		     
</@lo.layout>
<script type="text/javascript" src="${ctx}/js/sip-common.js"></script>  
<script type="text/javascript" src="${ctx}/js/jquery.dsTab.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="${ctx}/js/myFocus/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cxscroll.js"></script>
<script type="text/javascript">
 function closeN(){
        $('.g-layer.g-login-box').hide();
        $('.blackBg').hide();
    }
$(function(){
    //培训项目滚动
    $("#photoSlide1").cxScroll({direction:"right",step:3});
    //师资团队滚动
    $("#photoSlide2").cxScroll({direction:"right",step:1,speed:500,time:2000});
    
    $(".g-charts-box").myTab({
        pars    : '.g-charts-box',   //最外层父级
        tabNav  : '.m-charts-tabli',  //标签导航
        li      : 'li',       //标签
        tabCon  : '.g-charts-mn',  //区域父级
        tabList : '.g-charts-tabcont', //t区域模块
        cur     : 'z-crt'      //选中类
    });

    //推荐课程
    $('#m-lesson-lst').dsTab({
        itemEl        : '.m-course-lst li',
        prev          : '.prev',
        next          : '.next',
        play          : '.play',
        btnElName     : 'focus',
        btnItem       : 'li a',
        maxSize       : 5,
        playOnOff     : true,
        overStop      : true,
        changeType    : 'fade',
        change        : true,
        changeTime    : 3000,
    });

    //推荐工作坊
    $('#recommend-work').flexslider({
        animation: "slide",
        slideshowSpeed: 3000,
        itemWidth: 300,
        slideshow: true,
        prevText: "prev",
        nextText: "next", 
        pauseOnHover: true
    });
    
    indexbanner_run();//banner滚动
    function indexbanner_run(){       
        var num = 0;
        var num2= 1;
        var lastnum = $('#g-bn .m-bn .item:last').index();
        var pic_W = $('#g-bn .m-bn .item:last').width();
        var big_index = lastnum-1;
        var big2_index = lastnum+1;
        var timer;
        // alert(pic_W)
        var play=true;
        function autoPlay(){
            num++;
            num2++;
            if(num>lastnum){
                num=1   //下一张要切换到第二张，所以num要改成1
                $('#g-bn .m-bn').css('left',0)

            }
            if(num2>big2_index){
                num2=2
                $('#g-bn2 .m-bn-bg2').css('left',-pic_W)
            }
            $('#g-bn .m-bn').stop().animate({'left':-pic_W*num}, 350);    
            $('#g-bn2 .m-bn-bg2').stop().animate({'left':-pic_W*num2}, 350);    
            $('#g-bn .m-bn-descr li').eq(num).stop().fadeIn().siblings().fadeOut();  
            
        }
        clearInterval(timer)
        timer = setInterval(autoPlay,3000)
        //右按钮
        $('.m-play-opt .next').click(function(event) {
           autoPlay();
        });

        //左按钮
        $('.m-play-opt .prev').click(function(event) {
            num--;
            num2--;
            if(num<0){
                num=big_index
                $('#g-bn .m-bn').css('left',-pic_W*4)
            }
            if(num2<1){
                num2=lastnum
                $('#g-bn2 .m-bn-bg2').css('left',-pic_W*5)
            }
            $('#g-bn .m-bn').stop().animate({'left':-pic_W*num}, 350);
            $('#g-bn2 .m-bn-bg2').stop().animate({'left':-pic_W*num2}, 350);
            $('#g-bn .m-bn-descr li').eq(num).stop().fadeIn().siblings().fadeOut();
        });
        //鼠标移上清除定时器
        $('#g-bn').mouseenter(function(event) {
            if(play==true){
                play==true
                clearInterval(timer)
                // console.log(11)            
            }
        }).mouseleave(function(event) {      
            if(play==true){
                clearInterval(timer)
                timer = setInterval(autoPlay, 3000)            
            }

        });

        $(".m-play-opt .play").click(function(e) {
            if(play==true){
                clearInterval(timer)
                $(this).addClass('stop')
                play=false;
               
            }else{
                var target = $(e.target); 
                clearInterval(timer)
                timer = setInterval(autoPlay, 3000);
                $(this).removeClass('stop')
                if(target.parents("#g-bn").length == 1){
                    clearInterval(timer)
                }
                play=true;
            }


        });
    }
 
 });
</script>

