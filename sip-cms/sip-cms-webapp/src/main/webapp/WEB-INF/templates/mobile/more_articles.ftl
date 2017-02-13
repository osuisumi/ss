<@channelArticles alias="jjdt" page="${page!}"  size="10">
			 <#if catalog??>
					<#assign catalog=catalog>
			</#if>										
			<#list articleList as article>
				<li>
                   <a href="${ctx}/cms/${alias}/article/${article.id!}" class="m-figure-block">
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