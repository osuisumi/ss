<@articlesDirective channelId="${channel.id!}" page=param_page!"1"  size=param_size!"6" state="published">								
			<#if articleList??>
                 <#list articleList as article>
                    <li>
                        <a href="${ctx}/cms/${channel.alias!}/article/${article.id!}" class="block">
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
</@articlesDirective>