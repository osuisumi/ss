<#import "common/include/layout.ftl" as lo> 
<@lo.layout>
<div class="innerPage">
                <!--begin content -->
                <div class="g-auto g-cont">
                    <!--beging inner content box module -->
                    <div class="g-inner-box">
                        <div class="g-inner-tp">
                            <div class="m-innerMn-tt">
                                <h3 class="tt">课程中心<!-- <span class="trg"><i></i></span> --></h3>
                                <span class="ex">共有<em id="courseCount"></em>个课程</span>
                            </div>
                            <div class="g-select-box">
                                <dl id="subjectSelectRow" class="m-select-row">
									<dt>
										学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;科:
									</dt>
									<dd>
										<a type="subject" value="" onclick="listCourse(this)">全部</a>
									</dd>
									<#list DictionaryUtils.getEntryList('SUBJECT') as dictEntry>
										<dd>
											<a type="subject" value="${dictEntry.dictValue }" onclick="listCourse(this)">${dictEntry.dictName }</a>
										</dd>
									</#list>
								  </dl>
								  <dl id="stageSelectRow" class="m-select-row">
									<dt>
										学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段:
									</dt>
									<dd>
										<a type="stage" value="" onclick="listCourse(this)">全部</a>
									</dd>
									<#list DictionaryUtils.getEntryList('STAGE') as dictEntry>
										<dd>
											<a type="stage" value="${dictEntry.dictValue}" onclick="listCourse(this)">${dictEntry.dictName }</a>
										</dd>
									</#list>
								  </dl>
                            </div>
                        </div>
                        <div class="g-inner-dt">
                            <@coursesDirective stage='${param_stage!}' subject='${param_subject!}' page="${page!''}" size="12">
								<ul class="m-course-lst small spc">
									<#list courses as cr>
										<li>
											<div class="m-course-block">
												<a href="${ctx}/cms/kczx/content/${(cr.id)!}" class="figure">
													<#import "../common/image.ftl" as image/>
													<@image.imageFtl url="${(cr.courseImage)! }" default="${ctx}/images/${mappingFolder!}/course.png" />
												</a>
												<#if cr.studyHours??>
													<span class="period">${(cr.studyHours)!}学时</span>
												</#if>
												<p class="ex">
													
												</p>
												<h3 class="tt"><a href="detail.html">${(cr.courseTitle)!}</a></h3>
												<div class="info">
													<div class="m-grade">
														<span class="star in"></span>
														<span class="star in"></span>
														<span class="star in"></span>
														<span class="star in"></span>
														<span class="star"></span>
													</div>
													<!--<span class="name"></span>-->
												</div>
											</div>
										</li>
									</#list>
								</ul>
								<form id="listCourseForm" action="${ctx}/cms/kczx" method="get">
									<input id="subject" type="hidden" name="subject" value="${param_subject!}">
									<input id="stage" type="hidden" name="stage" value="${param_stage!}">
									<div class="m-jump-page">
		                            <#if paginator??>
		                              <#import "../common/pagination.ftl" as page/>
									  <@page.pagination paginator=paginator pageForm="listCourseForm" type="" divId=""/>
									</#if>
		                           </div>
								</form>
							</@coursesDirective>
                        </div>
                    </div>
                    <!--end inner content box module -->
                    
                </div>
                <!--end content -->
 </div> 
</@lo.layout> 
<script>
	$(function(){
		initSubject();
		initStage();
		initZ_CRTState();
		$('#courseCount').text($('.m-course-lst li').size());
	});
	function initSubject() {
		var subjects = $('#subjectsDiv option');
		$.each(subjects, function(i, n) {
			$('#subjectSelectRow').append('<dd><a value="' + $(n).attr("value") + '" type=subject onclick="listCourse(this)">' + $(n).text().trim() + '</a></dd>');
		});
	}

	function initStage() {
		var stages = $('#stagesDiv option');
		$.each(stages, function(i, n) {
			$('#stageSelectRow').append('<dd><a value="' + $(n).attr("value") + '" type="stage" onclick="listCourse(this)">' + $(n).text().trim() + '</a></dd>');
		});
	}

	function initZ_CRTState() {
		var subjectParam = "${param_subject!''}";
		var stageParam = "${param_stage!''}";
		if (subjectParam) {
			var selectedSubject = $('#subjectSelectRow a[value=' + subjectParam + ']');
			if (selectedSubject.size() > 0) {
				selectedSubject.addClass('z-crt');
			}
		} else {
			$('#subjectSelectRow a').eq(0).addClass('z-crt');
		}
		if (stageParam) {
			var selectedStage = $('#stageSelectRow a[value=' + stageParam + ']');
			if (selectedStage.size() > 0) {
				selectedStage.addClass('z-crt');
			}
		} else {
			$('#stageSelectRow a').eq(0).addClass('z-crt');
		}

	}

	function listCourse(a) {
		var type = $(a).attr('type');
		var value = $(a).attr('value');
		var form = $('#listCourseForm');
		$('#'+type).val(value);
		form.find('input[name="page"]').val('');
		form.submit();
	}
</script>