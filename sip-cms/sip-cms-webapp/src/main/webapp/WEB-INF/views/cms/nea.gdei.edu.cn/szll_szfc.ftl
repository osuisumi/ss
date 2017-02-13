<#import "szll_layout.ftl" as szll>
<@szll.layout>
	<div class="g-teacher-style">
                                <div class="g-teacher-lst">
                                    <ul class="m-teacher-lst">
                                    <@teachersDirective page=param_page!"1"  size=param_size!"10">	
                                    	<#if teacherList??>								
										<#list teacherList as teacher>
                                        <li>
                                            <a href="javascript:;" class="block">
                                                <span class="img"><img src="${ctx}/images/cms/${mappingFolder!""}/avatar.png"></span>
                                                <p class="name"><b>${teacher.fullName!''}</b></p>
                                                <p class="info">${teacher.personalProfile!''}</p>
                                            </a>
                                        </li> 
                                        </#list>
                                        <#assign paginator=paginator>
                                        </#if>
                                      </@teachersDirective>                                         
                                    </ul>
                                </div>
                                <div class="g-jump-page">
		                            <form action="${ctx}/cms/${channel.alias}" method="get" id="teacherListForm">
		                                <div id="teacherListPage" class="m-laypage"></div>
											<#import "../common/pagination_laypage.ftl" as p/>
											<@p.pagination formId="teacherListForm" divId="teacherListPage" paginator=paginator />
		                                </div>
		                            </form>
		                        </div>
    </div>
      <script>
    	$("#szllMenu li").removeClass("z-crt");
    	$("#szllMenu #m_szfc").addClass("z-crt");
    </script>
</@szll.layout>

