<#import "common/include/layout.ftl" as lo> 
<#assign jsArray=["${ctx}/js/laypage/laypage.js"]/>
<@lo.layout jsArray=jsArray> 
<div class="g-project-introduce">
                <div class="bottom-bg"></div>
                <div class="m-cont-main g-auto">
                    <ul class="g-teacher-list">
                    <@teachersDirective page=param_page!"1"  size=param_size!"6">									
						<#list teacherList as teacher>
                        <li class="m-teachLi">
                            <a href="javascript:;" class="img">
                            	<#if teacher.avatar??>
                            		<img src="${FileUtils.getFileUrl(teacher.avatar)}">
                            	<#else>
                                	<img src="${ctx}/images/cms/${mappingFolder!""}/avatar.png">
                                </#if>
                            </a>
                            <div class="discrible">
                                <h3>${teacher.fullName!''}</h3>
                                <p class="school"><span>${teacher.jobTitle!''}</span></p>
                                <p class="txt show-less">
                                	${teacher.personalProfile!''}
                                </p>
                            </div>
                            
                        </li>
						</#list>
						<#assign paginator=paginator>
					</@teachersDirective>
                    </ul>
                    <div class="g-jump-page">
                          <form action="${ctx}/cms/${channel.alias}" method="get" id="teacherListForm">
                                <div id="teacherListPage" class="m-laypage"></div>
									<#import "../common/pagination_laypage.ftl" as p/>
									<@p.pagination formId="teacherListForm" divId="teacherListPage" paginator=paginator />
                                </div>
                           </form>
                 	</div>
                </div>
                
                

            </div>  			
</@lo.layout>       

