<#import "include/layout.ftl" as lo> 
<@lo.layout>  
            <div class="g-bd-index">
                <div class="g-auto">
				 <div class="g-frame-mod">
                    <div class="g-crumbs">
                        <div class="m-crumbs">   
                        	<#import "include/bread_crumbs.ftl" as cru/> 
                        	<@cru.crumbs alias="${alias}"/>                                                  
                            <span class="u-line">&gt;</span>
                            <em>详情</em>
                        </div><!--end m-crumbs 面包屑导航-->
                    </div><!--end g-crumbs-->
                    <div class="g-layout">
                        <div class="m-train-wrap">
                           <@articleDetail articleId="${articleId}" incrBrowseNum="true">
							<#if article??>
								<#assign channel=article.channel>
							</#if>
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
                            </div>
                            <div class="train-con">
                                ${article.content!}
                                <p id="file_lst"> 
									
								</p>
                            </div>
							</@articleDetail>
                        </div>
                    </div><!--end g-layout-->
					</div>
                </div>
            </div><!--end index body content -->
</@c.layout> 
<script>
    $(function(){
		$.get('${ctx}/cms/file','relationId=${articleId}',function(data) {
			var $file_lst = $("#file_lst");
			if (data != null) {
				$.each(data,function(i, tag) {
					$file_lst.append('附件'+(i+1)+':<a href="${FileUtils.getFileUrl("")}'+this.url+'" class="dld">'+this.fileName+'</a>');
				});
			}
		});
    })
</script>       

