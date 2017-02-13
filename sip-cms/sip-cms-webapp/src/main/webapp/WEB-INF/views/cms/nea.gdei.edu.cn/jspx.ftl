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
                                    <h3 class="tt t1">最新资讯</h3>
                                    <a href="${ctx}/cms/zxzx" class="more">更多&gt;</a>
                                </div>
                                <div class="g-mod-dt">
                                    <dl class="m-news-lst latestInfor">
                                        <@articlesDirective alias="zxzx" page="1"  size="4" state="published">
            								<#if articleList??>
            								<#list articleList as article>
												<#if article_index == 0>  
	                                            <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank"  class="img"><img src="${FileUtils.getFileUrl(article.frontCoverImage!'')}" alt=""></a>
	                                            <dt>
	                                                <div class="u-con">
	                                                    <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank" class="tit">${article.title}</a>
	                                                    <p>
	                                                    	<#assign content=article.content?replace("<.*?>","","r")>
															<#assign content=content?replace("&nbsp;","")>
															<#assign content=content?replace(" ","")>
		                                                    <a href="${ctx}/cms/${article.channels[0].id!}/article/${article.id!}">
		                                                    	<#if content?length lt 38>
		                                                    		${content?substring(0,content?length)}
		                                                    	<#else>
		                                                    		${content?substring(0,38)}
		                                                    	</#if>
		                                                    </a>
	                                                    </p>    
	                                                </div>
	                                            </dt>
	                                            <#else>
	                                            <dd>
	                                                <span class="date">【${article.createTime?number_to_datetime}】</span>
	                                                <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank" class="txt">${article.title}</a>
	                                            </dd>
	                                            </#if>
	                                        </#list>
                                            </#if>
                                          </@articlesDirective>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <!-- end latest information -->
                        <!-- begin train lesson -->
                        <div class="g-train-lesson">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t4">培训课程</h3>
                                    <!-- <a href="javascript:;" class="more">更多&gt;</a> -->
                                </div>
                                <div class="g-mod-dt">
                                    <ul class="m-train-lesson">
                                        <li class="item item1">
                                            <a href="javascript:;" class="block">
                                                <i class="ico"></i>
                                                <i class="shadow"></i>
                                                <span class="info">
                                                    <b>提升工程</b>
                                                    <p><span>12,223</span>门课程</p>
                                                    <i></i>
                                                </span>
                                            </a>
                                        </li>
                                        <li class="item item2">
                                            <a href="javascript:;" class="block">
                                                <i class="ico"></i>
                                                <i class="shadow"></i>
                                                <span class="info">
                                                    <b>常规培训</b>
                                                    <p><span>540,123</span>门课程</p>
                                                    <i></i>
                                                </span>
                                            </a>
                                        </li>
                                        <li class="item item3">
                                            <a href="javascript:;" class="block">
                                                <i class="ico"></i>
                                                <i class="shadow"></i>
                                                <span class="info">
                                                    <b>定制培训</b>
                                                    <p><span>32,520</span>门课程</p>
                                                    <i></i>
                                                </span>
                                            </a>
                                        </li>
                                        <li class="item item4">
                                            <a href="javascript:;" class="block">
                                                <i class="ico"></i>
                                                <i class="shadow"></i>
                                                <span class="info">
                                                    <b>个人选修</b>
                                                    <p><span>4,250</span>门课程</p>
                                                    <i></i>
                                                </span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- end train lesson -->
                        <!-- begin results show -->
                        <div class="g-results-show">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t7">成果展示</h3>
                                    <a href="javascript:;" class="more">更多&gt;</a>
                                </div>
                                <div class="g-mod-dt">
                                    <ul class="m-results-show">
                                        <li class="word">
                                            <a href="javascript:;" class="block">
                                                <span class="img"></span>
                                                <h4 class="tt">《经济危机的导火线》.doc</h4>
                                                <p class="info">张老师&nbsp;上传于&nbsp;&nbsp;2015/08/18</p>
                                                <span class="u-btn u-btn-inverse">查&nbsp;看&nbsp;&gt;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- end results show -->
                    </div>
                    <div class="g-iframe-sd">
                        <!-- begin hot education-->
                        <div class="g-hot-education">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t2">通知公告</h3>
                                    <a href="javascript:;" class="more">更多&gt;</a>
                                </div>
                                <div class="g-mod-dt">
                                    <dl class="m-news-lst noDt spe">
                                        <@articlesDirective alias="jtzgg" page="1"  size="5" state="published">
            								<#if articleList??>
            								<#list articleList as article>
                                            <dd>
                                                <span class="date">【${article.createTime?number_to_datetime}】</span>
                                                <a href="${ctx}/cms/${article.channels[0].id}/article/${article.id!}" target="_blank"  class="txt">${article.title}</a>
                                            </dd>
                                            </#list>
                                            </#if>
                                        </@articlesDirective>
                                    </dl>
                                </div>
                            </div>
                        </div>
                        <!-- end hot education-->
                        <!-- begin expert teacher-->
                        <div class="g-expert-teacher">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t6">专家名师</h3>
                                    <a href="###" class="more">更多&gt;</a>
                                </div>
                                <div class="g-mod-dt">
                                    <div class="m-expert-teacher">
                                    <@teachersDirective page=param_page!"1"  size=param_size!"3">
                                    	<#if teacherList??>									
										<#list teacherList as teacher>
											<#if teacher_index == 0>  
		                                        <div class="column">
		                                            <a href="javascript:;" class="img">
		                                                <img src="${FileUtils.getFileUrl(teacher.avatar!'')}" alt="">
		                                                <span class="info">
		                                                    <b>${teacher.fullName!''}</b>
		                                                    <p>${teacher.jobTitle!''}</p>
		                                                </span>
		                                            </a>
		                                        </div>
	                                        <#else>
	                                        <#if teacher_index==1>
	                                        <div class="row">
	                                        </#if>
	                                            <a href="javascript:;" class="img">
	                                                <img src="${FileUtils.getFileUrl(teacher.avatar!'')}" alt="">
	                                                <span class="info">
	                                                    <b>${teacher.fullName!''}</b>
	                                                    <p>${teacher.jobTitle!''}</p>
	                                                </span>
	                                            </a>
		                                        <#if !teacher_has_next>
		                                        </div>
		                                        </#if>
		                                     </#if>
                                        </#list>
                                        </#if>
                                    </@teachersDirective>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end expert teacher-->
                        <!-- begin platform show-->
                        <div class="g-platform-show">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t8">培训平台模块展示</h3>
                                    <!-- <a href="javascript:;" class="more">更多&gt;</a> -->
                                </div>
                                <div class="g-mod-dt">
                                    <ul class="m-platform-show">
                                        <@channelContentDirective alias="pxpt">
												<#if channelContent??>
													${channelContent.content!}
												</#if>
										</@channelContentDirective>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- end platform show-->
                    </div>
                </div>    
            </div>
        </div>
    </div>
</@lo.layout>