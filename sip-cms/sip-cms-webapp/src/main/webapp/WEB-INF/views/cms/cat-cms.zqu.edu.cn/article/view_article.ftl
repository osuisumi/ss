<#import "../common/include/layout.ftl" as lo> 
<@lo.layout>  
<div class="innerPage">
                <!--begin content -->
                <div class="g-auto g-cont">
                    <!--beging inner content box module -->
                    <div class="g-inner-box">
                    	<div class="g-crm">  
                        	<#import "../common/include/bread_crumbs.ftl" as cru/> 
                        	<@cru.crumbs channelId="${channelId}"/>                                                  
                            <span class="line">&gt;</span>
                            <em>详情</em>
                        </div><!--end m-crumbs 面包屑导航-->
                        <@articleDetailDirective articleId="${articleId}" incrBrowseNum="true">
                            <div class="g-detail-cont">
	                            <h1 class="title">${article.title!}</h1>
	                            <p class="info">
	                                <span>发布时间：<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
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

