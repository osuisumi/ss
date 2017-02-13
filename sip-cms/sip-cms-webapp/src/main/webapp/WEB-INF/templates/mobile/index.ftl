<#import "common/common.ftl" as c> 
<@c.html>
			<!--begin new list box -->
			<div class="g-new-box" id="newListBox">
				<ul class="m-figure-lst">
					<@channelArticles alias="jjdt" page="${page!}"  size="10">
										<#if catalog??>
												<#assign catalog=catalog>
										</#if>										
										<#list articleList as article>
											<li>
                                             <a href="${ctx}/cms/jjdt/article/${article.id!}" class="m-figure-block">
                                             	<h2 class="title">
												${article.title!}
												</h2>
												<span class="u-time">
													<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm:ss">
														${date}
													</@formatTime>
												</span>
											 </a>                                             	
                                            </li>
										</#list>
									    
					</@channelArticles> 					
				</ul>
			</div>
			<!--end new list box -->
			<div id="pullUp">  
                <div class="pullUpIcon">
                    <span>加载中</span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>  
                <div class="pullUpTxt">上拉显示更多...</div>  
            </div>
</@c.html>
<script type="text/javascript">
$(function(){
	//显示侧边栏导航
	showSideNavFn();
	var  totalPages= ${paginator.totalPages};
	var currentPage = ${paginator.page};
    //开启模拟滚动条
    $("body").myScrollFn({
        //允许到底部时上拉加载更多
        pullUp: true,
        //执行底部时上拉加载更多函数
        pullUpFn: function(){
           if(totalPages-currentPage>0){	
           		$.get("${ctx}/cms/jjdt/loadMore",{page:currentPage+1},function(data){
           			$(".m-figure-lst").append(data);
           			currentPage=currentPage+1;
           		});
           }else{
           		
           }
        }
    });
});
</script>