<#import "common/include/layout.ftl" as lo> 
<@lo.layout>
<@courseDirective courseRelationId="${id}">
<div class="innerPage">
                <div class="g-auto">
                    <div class="g-inner-box g-couDl-inner">
                        <div class="g-crm">
                           <#import "common/include/bread_crumbs.ftl" as cru/> 
		                   <@cru.crumbs channelId="${channel.id}"/>                                                   
                            <span class="line">&gt;</span>
                            <strong>${course.courseTitle}</strong>
                        </div>
                        <div class="g-courseDt-cont">
                            <div class="g-cDetail-tp">
                                <span class="fig">
									<#import "../common/image.ftl" as image/>
									<@image.imageFtl url="${(course.courseImage)! }" default="${ctx}/images/${projectName}/course.png" />
								</span>
                                <h2 class="title">${course.courseTitle!}</h2>
                                <ul class="m-cDt-dl">
                                    <li class="i1"><span>10 节课时/周</span></li>
                                    <li class="i2"><span>
                                    <#if course.courseType == 'lead'>
									引领式
									<#elseif course.courseType == 'mic'>
									微课
									<#else>
									自主式
									</#if>
									</span></li>
                                    <li class="i3"><span>${DictionaryUtils.getEntryName('SUBJECT',course.subject!)} / ${DictionaryUtils.getEntryName('STAGE',course.stage!)}学段</span></li>
                                    <li class="i4"><span>${(course.organization)!}</span></li>
                                </ul>
                                <a href="javascript:;" class="btn u-main-btn selectBtn">立即选课</a>
                                <div class="m-cDt-info">
                                    <div class="tp">
                                        课程已进行至<strong><span>0</span>/4周</strong>
                                    </div>
                                    <p class="tiem">开课时间：<span>2015年12月24日</span></p>
                                    <p class="tiem">结束时间：<span>2016年1月24日</span></p>
                                    <div class="grade">
                                        <div class="m-grade">
                                            <span class="star in"></span>
                                            <span class="star in"></span>
                                            <span class="star in"></span>
                                            <span class="star in"></span>
                                            <span class="star"></span>
                                        </div>  
                                        <p>满意度评分</p>  
                                    </div>
                                </div>
                            </div><!--end course detail top -->
                            <div class="g-cDt-nav">
                                <div class="m-cDt-nav">
                                    <a href="javascript:;" class="z-crt">课程简介</a>
                                    <a href="javascript:;">课程内容</a>
                                    <a href="javascript:;" class="last">常见问题</a>
                                </div>
                                <div class="c-share">
                                    <div class="bdsharebuttonbox bdshare-button-style0-24" data-tag="share_1" data-bd-bind="1462246902657">
                                        <a class="bds_tsina" data-cmd="tsina" href="#" title="分享到新浪微博"></a>
                                        <a class="bds_qzone" data-cmd="qzone" href="#" title="分享到QQ空间"></a>
                                        <a class="bds_sqq" data-cmd="sqq" href="#" title="分享到QQ好友"></a>
                                        <a class="bds_weixin" data-cmd="weixin" href="#" title="分享到微信"></a>
                                    </div>
                                    <span>分享到：</span>
                                    <script type="text/javascript">
                                        window._bd_share_config = {
                                            share : [{
                                                "tag" : "share_1",
                                                "bdSize" : 24,
                                            }]
                                        }
                                        //以下为js加载部分
                                        with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
                                    </script>
                                </div>
                            </div><!--end course detail nav -->
                            <div class="g-cDt-frame f-cb">
                                <div class="g-cDt-mn">
                                    <div class="g-cDt-mnMod">
                                        <div class="m-intro-text">
                                            ${(course.summary)!}
                                        </div>
                                    </div>
                                    <div class="g-cDt-mnMod">
                                        <div class="title-block">
                                            <h3 class="title">课程内容</h3>
                                        </div>
                                        <div class="dt">
                                            <#if course.courseType == 'lead' || course.courseType == 'self'>
												<ol class="g-cDt-directory">
													<#if course.courseContent??>
														<#assign json=course.courseContent?eval />
														<#list json as section>
														<li class="t-item">
															<dl class="m-cDt-directory">
																<dt class="t-chapter">
																	<div class="num">
																		<span>模块${section_index+1}</span>
																	</div>
																	<span class="title">${section.title!}</span>
																	<!--<a href="javascript:;" class="in-icos">进行中</a>-->
																</dt>
																<#if section.childSections??>
																<#list section.childSections as childSection>
																	<dd class="t-section">
																		<a href="javascript:;"><span>${childSection_index+1}.${childSection.title!}；</span></a>
																		<!--<i class="finish" title="已完成"></i>-->
																	</dd>
																</#list>
																</#if>
															</dl>
														</li>
														</#list>
													</#if>
												</ol>
											<#else>
												${course.content!}
											</#if>
                                        </div>
                                    </div>
                                    <div class="g-cDt-mnMod">
                                        <div class="title-block">
											<h3 class="title"><a id="faqQuestions" name="faqQuestions">常见问题</a></h3>
										</div>
										<div class="dt">
												<ul id="faqQuestionList" class="g-cDt-qaLst">
													<@faqQuestionsDirective relationId="${course.courseId}">  
														<#list faqQuestions as faqQuestion>
														<li>
		                                                    <div class="box">
		                                                        <div class="q-block">
		                                                            <h3 class="tt">${faqQuestion.content!}</h3>
		                                                        </div>
		                                                        <ul class="m-studyA-lst">
		                                                            <li>
		                                                                <div class="a-block">
		                                                                    <div class="sign">
		                                                                        <span>答</span>
		                                                                    </div>
		                                                                    <#if faqQuestion.faqAnswers??>
			                                                                    <#list faqQuestion.faqAnswers as faqAnswer>
			                                                                    	<p class="a-txt">${faqAnswer.content!}</p>
			                                                                    </#list>
		                                                                    </#if>
		                                                                </div>
		                                                            </li>
		                                                        </ul>                                                            
		                                                    </div>
		                                                </li>
		                                                </#list>
													</@faqQuestionsDirective>
												</ul>
											<div>
										</div>
									</div>
                                  </div>

                                </div><!--end course detail frame main -->
                                <div class="g-cDt-sd">
                                    <div class="g-cDt-sdMod" id="teacherSlideBox">
                                        <div class="title-block">
                                            <h3 class="title">授课老师</h3>
                                            <a href="javascript:;" class="u-prev2"></a>
                                            <a href="javascript:;" class="u-next2"></a>
                                        </div>
                                        <div class="dt">
                                            <ul class="m-teacherSlide-lst">
                                                <li style="display: list-item;">
                                                    <a href="javascript:;" class="fig"><img src="../images/headImg11.jpg" alt=""></a>
                                                    <div class="info">
                                                        <strong class="name">张莉</strong>
                                                        <span class="level">教授</span>
                                                        <br>
                                                        <span class="school">清华大学计算机科学与技术系</span>
                                                    </div>
                                                    <div class="intro">
                                                        <p>郑莉 ，教授，清华大学计算机科学与技术系。国家精品资源共享课负责人；北京市计算机教育研究会副理事长。全国高等学校计算机研究学院... <a href="javascript:;" class="more">[ 展开 ]</a></p>
                                                    </div>
                                                </li>                                                
                                            </ul>
                                        </div>
                                    <div class="btns"><ul><li class="btn1"><a href="javascript:void(0)" class="current">1</a></li><li class="btn2"><a href="javascript:void(0)">2</a></li><li class="btn3"><a href="javascript:void(0)">3</a></li></ul></div></div>
                                    <div class="g-cDt-sdMod">
                                        <div class="title-block">
                                            <h3 class="title">相关课程</h3>
                                            <a href="jvascript:;" class="more">更多&gt;</a>
                                        </div>
                                        <div class="dt">
                                            <ul class="m-rcmCourse-lst">
                                                
                                            </ul>
                                        </div>
                                    </div>
                                </div><!--end course detail frame side -->
                            </div><!--end course detail frame -->
                        </div><!--end course detail content -->
                    </div><!--end inner content box -->
                </div><!--end auto content -->    
            </div>
</@courseDirective>
</@lo.layout>
