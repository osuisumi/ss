					<@teachersDirective page=param_page!"1"  size=param_size!"3">									
						<#list teacherList as teacher>
	                    <li>
	                        <div class="m-head">
	                            <span class="head-img"> <img src="${ctx}/images/cms/${mappingFolder!""}/avatar.png" alt=""> </span>
	                            <b>${teacher.fullName!''}</b>
	                            <p>${teacher.jobTitle!''}</p>
	                        </div>
	                        <div class="m-con">
	                            <div class="u-text">
	                                <p>${teacher.personalProfile!''}</p>
	                            </div>
	                            <div class="u-opa"></div>
	                        </div>
	                    </li>
	                    </#list>
	                 </@teachersDirective>