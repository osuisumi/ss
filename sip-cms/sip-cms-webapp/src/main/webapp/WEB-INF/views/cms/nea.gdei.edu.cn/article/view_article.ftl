<#import "../common/include/layout.ftl" as lo> 
<@lo.layout>  
    <div class="g-bd" id="innerContent">
        <div class="g-auto">
            <div class="g-content notice">
                <div class="g-iframe f-cb">
                    <div class="m-crm plt">
                        <#import "../common/include/bread_crumbs.ftl" as cru/> 
                        <@cru.crumbs channelId="${channelId}"/>                                                  
                         <span class="line">&gt;</span>
                        <em>详情</em>
                    </div>
                    <@articleDetailDirective articleId="${articleId}" incrBrowseNum="true">
                    <div class="g-detail-content">
                        <h1 class="tit">${article.title!}</h1>
                        <p class="info">
                            <span>发布日期：<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
															${date}
												   </@formatTime></span>
                        </p>
                        <div class="cont">
                            <p>${article.content!}</p>
	                        <p id="file_lst"> 
										
							</p>
                        </div>
                    </div>
					</@articleDetailDirective>
                </div>  
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

