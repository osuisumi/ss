<#import "common/include/layout.ftl" as lo> 
<#setting datetime_format="yyyy.MM.dd"/>
<@lo.layout> 
	<div class="g-bd" id="innerContent">
        <div class="g-auto">
            <div class="g-content inner">
                <div class="g-iframe f-cb">
                    <div class="g-iframe-mn">
                        <!-- begin latest information -->
                        <div class="g-latest-information">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t2">通知公告</h3>
                                    <a href="javascript:;" class="more">更多&gt;</a>
                                </div>
                                <div class="g-mod-dt">
                                    <dl class="m-news-lst ordinary">
                                    	<@articlesDirective alias="xwzx" page="1"  size="3" state="published">
            								<#if articleList??>
            								<#list articleList as article>
												<#if article_index == 0>  
			                                        <a href="javascript:;" class="img">
			                                            <img src="${FileUtils.getFileUrl(article.frontCoverImage!'')}" alt="">
			                                        </a>
			                                    </#if>
                                        		<dd>
	                                                <span class="date">【${article.createTime?number_to_datetime}】</span>
	                                                <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank" class="txt">${article.title}</a>
	                                            </dd>
	                                        </#list>
                                            </#if>
                                          </@articlesDirective>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <!-- end latest information -->
                    </div>
                    <div class="g-iframe-sd">
                        <!-- begin hot education-->
                        <div class="g-new-layout">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t3">学习支持服务</h3>
                                    <a href="javascript:;" class="more">更多&gt;</a>
                                </div>
                                <div class="g-mod-dt">
                                    <ul class="m-service-lst">
                                        <@channelContentDirective alias="xxzcfw">
								    		${(channelContent.content)!}
								    	</@channelContentDirective>      
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- end hot education-->
                    </div>
                </div>
                <div class="g-iframe f-cb">
                    <div class="g-lesson-study">
                        <div class="g-mod">
                            <div class="g-mod-tt">
                                <h3 class="tt t4">课程学习</h3>
                                <a href="javascript:;" class="more">更多&gt;</a>
                            </div>
                            <div class="g-mod-dt">
                                <div class="m-lesson-wrap" id="lessonWrap">
                                    <div class="lesson-box cxScrollBox">
                                        <ul class="m-lesson-lst cxScrollList">
                                            <li>
                                                <a href="javascript:;" class="block">
                                                    <span class="img"><img src="../images/nice-lsn1.jpg" alt=""></span>
                                                    <p>女性精神在现代社会中的挑战</p>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- 左右切换按钮 -->
                                    <span class="opa prev"><i></i></span>
                                    <span class="opa next"><i></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   
            </div>
        </div>
    </div>
</@lo.layout>
