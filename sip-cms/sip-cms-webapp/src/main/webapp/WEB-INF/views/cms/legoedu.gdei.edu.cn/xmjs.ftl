<#import "common/include/layout.ftl" as lo> 
<@lo.layout>  
<div class="g-project-introduce">
                <div class="bottom-bg"></div>
                <div class="m-pro-discrible-cont g-auto">
                    <div class="m-pro-discrible-contIn">
                        <div class="tl">
                            <h3>乐高项目介绍</h3>
                        </div>
                       <@channelContentDirective channelId="${channel.id}">
				    		${(channelContent.content)!}
				       </@channelContentDirective>  
                        <p id="file_lst"> 
									
						</p>
                    </div>


                </div>
                



            </div>
			
</@lo.layout> 
<script>
    $(function(){
		$.get('${ctx}/cms/file','relationId=${channel.id}',function(data) {
			var $file_lst = $("#file_lst");
			if (data != null) {
				$.each(data,function(i, tag) {
					$file_lst.append('附件'+(i+1)+':<a href="${FileUtils.getFileUrl("")}'+this.url+'" class="dld">'+this.fileName+'</a>');
				});
			}
		});
    })
</script>       

