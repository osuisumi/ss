<#import "../include/layout_m.ftl" as lom> 
<@lom.layout pullUpEvent="nextPage()">  
	<div class="g-scroller-wrap" id="scroller">
            <!-- begin news lst -->
            <div class="g-news-lst">
                <ul class="m-news-lst">
                	<@articlesDirective channelId="${channel.id!}" page=param_page!"1"  size=param_size!"6" state="published">
                    <#if articleList??>
                    <#list articleList as article>
                    <li>
                        <a href="${ctx}/cms/${alias!}/article/${article.id!}" class="block">
                            <span class="img">
                            	<#if (article.frontCoverImage??) && article.frontCoverImage!="">
									<img src="${FileUtils.getFileUrl(article.frontCoverImage!'')}" alt="">
								</#if>
                            </span>
                            <h3 class="u-tit">${article.title!}</h3>
                            <p class="u-btm">
                                <span><i class="u-ico-clock"></i>
                                <@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd">
									${date}
								</@formatTime>
								</span>
                                <span class="u-line">|</span>
                                <span><i class="u-ico-eyes"></i>${article.browseNumber}</span>
                            </p>
                        </a>
                    </li>
                    </#list>
                    </#if>
					<#assign paginator=paginator>
					</@articlesDirective>                    
                </ul>
            </div>
            <!-- end news lst -->
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
        </div>
        <script type="text/javascript">
        	var  totalPages= ${paginator.totalPages};
			var currentPage = ${paginator.page};
        	function nextPage(){
				if(totalPages-currentPage>0){	
		           		$.get("${ctx}/cms/${channel.id}/loadMore",{page:currentPage+1},function(data){
		           			$(".m-news-lst").append(data);
		           			currentPage=currentPage+1;
		           		});
		        }
        	}
		</script>
</@lom.layout> 