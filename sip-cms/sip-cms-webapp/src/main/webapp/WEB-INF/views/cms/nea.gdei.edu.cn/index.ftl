<#import "common/include/layout.ftl" as lo> 
<#setting datetime_format="yyyy.MM.dd"/>
<@lo.layout> 
	<!--begin banner 横幅 -->
    <div class="g-bn">
        <div class="g-bn-bg"></div>
        <div class="g-auto">
            <div class="g-index-bn" id="indexBn">
            	<ul class="m-bn-img">
            		<#assign bannerCount=0>  
            		<@bannersDirective alias="gdtp">
                        <#if bannerList??>
						<#list bannerList as banner>	
							<li>
								<a <#if banner.articleLink?? && banner.articleLink!=''>href="${banner.articleLink}" target="${banner.target!'_blank'}"<#else>href="javascript:void(0);"</#if>>
	                                    <img src="${FileUtils.getFileUrl(banner.imageUrl!"")}" alt="" width="720" height="375">
                                </a>
							</li> 
							<#assign bannerCount=bannerCount+1>      
						</#list>
						</#if>
					</@bannersDirective>
                </ul>
	             <a href="javascript:;" class="opa prev"></a>
	             <a href="javascript:;" class="opa next"></a>
	             <div class="page"><span class="now">1</span><span class="line">/</span><span class="all">${bannerCount}</span></div>
	             <div class="shadow"></div>
            </div>
        </div>
    </div>
    <!--end banner 横幅-->
    
    <!--begin content body 内容 -->
    <div class="g-bd" id="indexContent">
        <div class="g-auto">
            <div class="g-content index">
                <div class="g-iframe f-cb">
                    <div class="g-iframe-mn">
                        <div class="g-new-wrap f-cb">
                            <div class="g-new-layout fl">
                                <div class="g-mod">
                                    <div class="g-mod-tt">
                                        <h3 class="tt t1">新闻资讯</h3>
                                        <a href="${ctx}/cms/xwzx" class="more">更多&gt;</a>
                                    </div>
                                    <div class="g-mod-dt">
                                        <dl class="m-news-lst">
                                        <@articlesDirective alias="xwzx" page="1"  size="3" state="published">
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
                            <div class="g-new-layout fr">
                                <div class="g-mod">
                                    <div class="g-mod-tt">
                                        <h3 class="tt t2">通知公告</h3>
                                        <a href="${ctx}/cms/itzgg"  class="more">更多&gt;</a>
                                    </div>
                                    <div class="g-mod-dt">
                                        <dl class="m-news-lst noDt">
                                         <@articlesDirective alias="itzgg" page="1"  size="5" state="published">
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
                        </div>
                    </div>
	                 <div class="g-iframe-sd">    
		                        <div class="g-special-report">
		                            <div class="g-mod">
		                                <div class="g-mod-tt">
		                                    <h3 class="tt t4">专题报道</h3>
		                                    <!-- <a href="###"  class="more">更多&gt;</a>-->
		                                </div>
		                                <div class="g-mod-dt">
		                                    <div class="m-special-report">
		                                    	<a href="###" class="block">
		                                            <img src="${ctx}/images/${mappingFolder}/report.jpg" alt="">
		                                        </a>
		                                    	 
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
	                    </div>
                    </div>
               
               
				<div class="g-frame f-cb">
                    <ul class="m-entrances-lst">
                        <li class="item1">
                            <a href="http://glpt.gdjxjy.com.cn" target="_blank" class="block">
                                <p>广东省中小学教师</p>
                                <p>继续教育信息管理平台</p>
                            </a>
                        </li>
                        <li class="item2">
                            <a href="http://www.gdjsw.cn" target="_blank" class="block">
                                <p>广东省中小学教师信息技术</p>
                                <p>应用能力提升工程公共服务平台</p>
                            </a>
                        </li>
                        <li class="item3">
                            <a href="http://legoedu.gdei.edu.cn" target="_blank" class="block">
                                <p>教育部-乐高</p>
                                <p>“创新人才培养计划”教师培训网</p>
                            </a>
                        </li>
                        <li class="item4">
                            <a href="http://neancts.gdei.edu.cn"  target="_blank" class="block">
								<p>广东第二师范学院</p>
                                <p> 远程教育网(二师在线）</p>
                            </a>
                        </li>
                    </ul>
                </div>

               
               <div class="g-iframe f-cb">
                    <div class="g-iframe-mn">
                        <div class="g-nice-lesson">
                            <div class="g-mod">
                                <div class="g-mod-tt">
                                    <h3 class="tt t3">精品课程</h3>
                                   <!-- <a href="###"  class="more">更多&gt;</a>-->
                                </div>
                                <div class="g-mod-dt">
                                    <div class="g-lesson-lst">
                                        <ul class="m-lesson-lst">
                                            <li>
                                                <a href="javascript:;" class="block">
                                                    <span class="img"><img src="${ctx}/images/${mappingFolder}/nice-lsn1.jpg" alt=""></span>
                                                    <p>五大领域的信息技术应用</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;" class="block">
                                                    <span class="img"><img src="${ctx}/images/${mappingFolder}/nice-lsn2.jpg" alt=""></span>
                                                    <p>交互式电子白板的教学应用</p>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;" class="block">
                                                    <span class="img"><img src="${ctx}/images/${mappingFolder}/nice-lsn3.jpg" alt=""></span>
                                                    <p>移动互联技术在教学中的应用</p>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                      </div>
                        
                        <div class="g-iframe-sd">
	                        <div class="g-hot-education">
	                            <div class="g-mod">
	                                <div class="g-mod-tt">
	                                    <h3 class="tt t5">教育热点</h3>
	                                    <a href="${ctx}/cms/jyrd" class="more">更多&gt;</a>
	                                </div>
	                                <div class="g-mod-dt">
	                                    <dl class="m-news-lst noDt">
	                                   	 	<@articlesDirective alias="jyrd" page="1"  size="5" state="published">
	            								<#if articleList??>
	            								<#list articleList as article>
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
                        </div>	
                      </div>  			
                    </div>
                </div>   
                 
            </div>
        </div>
    </div>
    <!--end content body 内容-->
    
</@lo.layout>       