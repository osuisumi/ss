<#import "../common/include/layout.ftl" as lo> 
<@lo.layout>  
			<div class="g-auto">
                <div class="g-crm">  
                        	<#import "../common/include/bread_crumbs.ftl" as cru/> 
                        	<@cru.crumbs channelId="${channelId}"/>                                                  
                           <span class="trg">&gt;</span>
               		<em>详情</em>
				</div>
			<!-- start g-content -->
                <div class="g-content">
                    <div class="g-text-detail">
                           <@articleDetailDirective articleId="${articleId}" incrBrowseNum="true">
                           <h2 class="m-text-tit">${article.title!}</h2>
                             <div class="info">
                                    <span>
										发布时间：<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
														${date}
											   </@formatTime>
									</span>
                                    <span>浏览次数：${article.browseNumber!1}</span>
                                </p>
                            </div>
                            <div class="train-con">
                                ${article.content!}
                                <p id="file_lst"> 
									
								</p>
                            </div>
							</@articleDetailDirective>
                           </div>
                	</div>
                <!-- end g-content -->
            </div>
</@lo.layout> 
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

