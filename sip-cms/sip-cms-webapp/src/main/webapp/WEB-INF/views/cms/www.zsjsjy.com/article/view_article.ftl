<#import "../common/include/layout.ftl" as lo> 
<@articleDetailDirective articleId="${articleId}" incrBrowseNum="true">
	<#assign article=article/>
</@articleDetailDirective>
<@lo.layout title="${article.title!}">  
            <div class="g-bd-index">
                <div class="g-auto">
				 <div class="g-frame-mod">
                    <div class="g-crumbs">
                        <div class="m-crumbs">   
                        	<#import "../common/include/bread_crumbs.ftl" as cru/> 
                        	<@cru.crumbs channelId="${channelId}"/>                                                  
                            <span class="u-line">&gt;</span>
                            <em>详情</em>
                        </div><!--end m-crumbs 面包屑导航-->
                    </div><!--end g-crumbs-->
                    <div class="g-layout">
                        <div class="m-train-wrap">
                           
                            <div class="train-tit">
                                <h2 class="u-tit">${article.title!}</h2>
                                <p class="u-info">
                                    <span class="u-time">
										发布时间：<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
														${date}
											   </@formatTime>
									</span>
                                    <span class="u-num">浏览次数：${article.browseNumber!1}</span>
                                </p>
                                <p class="u-info">
                                		<span><#if article.author??>撰稿人:${article.author}</#if></span>  
				                        <span><#if article.contributedby??>供稿人:${article.contributedby}</#if></span>
				                        <span><#if article.origin??>文章来源:${article.origin}</#if></span>
                                </p>
                            </div>
                            <div class="train-con">
                                ${article.content!}
                                <p id="file_lst"> 
									
								</p>
                            </div>
							
                        </div>
                    </div><!--end g-layout-->
					</div>
                </div>
            </div><!--end index body content -->
</@lo.layout> 
<script>
    $(function(){
		$.get('${ctx}/cms/file','relationId=${articleId}&relationType=cms-article',function(data) {
			var $file_lst = $("#file_lst");
			if (data != null) {
				$.each(data,function(i, tag) {
					$file_lst.append('附件'+(i+1)+':<a href="${FileUtils.getFileUrl("")}'+this.url+'" class="dld">'+this.fileName+'</a>');
				});
			}
		});
    })
</script>       

