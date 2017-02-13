<#import "../common/include/layout.ftl" as lo> 
<@lo.layout>  
<div class="g-project-introduce">
                <div class="bottom-bg"></div>
                <div class="m-pro-discrible-cont g-auto">
                    <h3 class="m-all-cont-tl">
                        <#import "../common/include/bread_crumbs.ftl" as cru/> 
                        <@cru.crumbs channelId="${channelId}"/>   
                        <ins>&gt;</ins>
                        <span>详情</span>
                    </h3> 
                    <div class="m-pro-discrible-contIn">
                    	<@articleDetailDirective articleId="${articleId}" incrBrowseNum="true">
                        <div class="tl">
                            <h3>${article.title!}</h3>
                            <span class="time">发布时间：<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
														${date}
											   </@formatTime></span>
                        </div>
                        ${article.content!}
                        <p id="file_lst"> 
									
						</p>
						</@articleDetailDirective>
                    </div>


                </div>
                



            </div>
			
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

