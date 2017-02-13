<#import "xygk_layout.ftl" as xygk>
<@xygk.layout>
<div class="m-infor-mn">
                                    <div class="teacher-lst">
                                        <ul class="tea-ul">
                                        	<@teachersDirective page=param_page!"1"  size=param_size!"6">									
												<#list teacherList as teacher>
													<li class="tea-li">
		                                                <div class="head-img">		                                                	
		                                                    <img src="${ctx}/images/cms/${mappingFolder!""}/avatar.png">
		                                                </div>
		                                                <div class="teacher-infor">
		                                                    <h2 class="tea-name">${teacher.fullName!''}</h2>
		                                                    <p class="tea-post">
												                  ${teacher.eduBackground!''}&nbsp;${teacher.jobTitle!''}<br />
												                  ${teacher.position!''}
		                                                    </p>
		                                                    <p>
		                                                        <span class="tea-detail" limit="90">${teacher.personalProfile!''}</span>
		                                                        <a href="javascript:void(0);" class="open">[展开]</a>
		                                                        <a href="javascript:void(0);" class="close">[收起]</a>
		                                                    </p>
		                                                </div>
                                            		</li>
												</#list>
											    <#assign paginator=paginator>
											</@teachersDirective>
                                        </ul>
                                    </div><!--end teacher-lst-->
                                    <form action="${ctx}/cms/szfc" method="get" id="teachersForm">
                                    <div class="m-jump-page">
                                        <#if paginator??>
		                                  <#import "../common/pagination.ftl" as page/>
									      <@page.pagination paginator=paginator pageForm="teachersForm" type="" divId=""/>
										</#if>
                                    </div>
                                    </form>
                                </div><!--end m-infor-mn-->
</@xygk.layout>
<script type="text/javascript">
	$(function(){
        $(function(){
        checkLength(); 
        $(".tea-ul li:even").css("clear","both");  //清除tea-ul 下面索引为偶数的li，让它内容展开时，不出现卡住的情况     
    })

    function checkLength(){                 //判断P的长度，超过长度用...取代
        var self = $(".tea-detail"); 
        self.each(function(){ 
            var _this = $(this);
            var objString = _this.text(); 
            var objLength = _this.text().length; 
            var num = _this.attr("limit");
            if(objLength > num){ 
                _this.text(objString.substring(0,num) + "...").siblings(".open").show(); 
                _this.parent().children(".open").click(function(){
                    $(this).hide().siblings(".tea-detail").text(objString).siblings(".close").show();
                });
                _this.parent().children(".close").click(function(){
                    $(this).hide().siblings(".tea-detail").text(objString.substring(0,num) + "...").siblings(".open").show();
                });
                
            } else{
                _this.text(objString); 
            }                       
        }) 
    }
    
    $("#xygkMenu li a").removeClass("z-crt");
    $("#xygkMenu #m_szfc").addClass("z-crt");
}); 
</script>