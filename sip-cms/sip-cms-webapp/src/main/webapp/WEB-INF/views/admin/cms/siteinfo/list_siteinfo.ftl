<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout>
		<#assign formId="articlesForm"/>
		<@siteInfosDirective page=param_page!"1" size=param_size!"10">
		<div class="mis-inner-wrap">
				<form action="${ctx}/cms_siteInfos/list" method="get" id="${formId}">
                <div class="mis-mod">
                    <div class="mis-crm">
                        <div class="crm">
                            <a href="###"><i class="mis-home-ico"></i>首页</a>
                            <span class="trg">&gt;</span>
                            <em>站点管理</em>
                        </div>
                    </div>   
                    <div class="mis-srh-layout">
                        <ul class="mis-ipt-lst">                            
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>域名：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="domain" value="${param_domain!""}" placeholder="请输入域名" class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>                            
                        </ul>
                        <div class="mis-btn-row indent1">
                            <button tyle="button" class="mis-btn mis-main-btn"><i class="mis-srh-ico"></i>查询</button>
                            <button tyle="button" class="mis-btn mis-default-btn"><i class="mis-refresh-ico"></i>重置</button>
                        </div>
                    </div>
                    <div class="mis-table-layout">
                        <div class="mis-opt-row">
                            <div class="mis-opt-mod fl">
                                <a href="${ctx}/cms_siteInfos/create" class="mis-btn mis-inverse-btn"><i class="mis-add-ico"></i>新建</a>
                                <button type="button" class="mis-btn mis-inverse-btn disabled-btn  disabled" disabled="disabled" onclick="editForm();" ><i class="mis-alter-ico"></i>修改</button>
                                <button type="button" onclick="deleteSiteInfo();" class="mis-btn mis-inverse-btn disabled-btn disabled" disabled="disabled"><i class="mis-detele-ico"></i>删除</button>                                
                            </div>
                        </div>
                        <div class="mis-table-mod">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mis-table">
                                <thead>
                                    <tr>
                                        <th width="2%"></th>
										<th width="15%">网站名称</th>
										<th width="10%">域名</th>
										<th width="5%">备案号</th>
										<th width="5%">前端主题样式</th> 
										<th width="5%">后台主题样式</th>
										<th width="10%">版权</th>
										<th width="5%">创建时间</th>	
                                    </tr>
                                </thead>
                                <tfoot>
                                	
                                    <tr>
                                        <td colspan="9">
                                            <#import "../../common/include/pagination.ftl" as page/>
											<@page.adminPage formId="${formId}" divId="" paginator=paginator/>
                                        </td>
                                    </tr>
                                </tfoot>
                                <tbody>
									<#if siteInfoList??>
									<#list siteInfoList as siteInfo>
                                    <tr>
                                        <td><input type="checkbox" name="checkboxId" value="${siteInfo.id}"></td>
										<td>${siteInfo.name}</td>
										<td>${siteInfo.domain!}</td>
										<td>${siteInfo.icp!}</td>
										<td>${siteInfo.frontEndTheme.name!}</td>
										<td>${siteInfo.adminTheme.name!}</td>
										<td>${siteInfo.copyRight!}</td>
										<td>
											<@formatTime longtime="${siteInfo.createTime!}" pattern="yyyy-MM-dd HH:mm">
													${date}
											</@formatTime>
										</td>
                                    </tr> 
                                    </#list>
                                    </#if>                                  
                                </tbody>
                            </table>
                        </div>
                    </div> 
                </div><!--end module layout -->
                </form>
            </div><!--end inner page -->
			<script type="text/javascript">
					$(document).ready(function(){
						$('input[type=checkbox]').click(function(){
							if($("input[name='checkboxId']:checked").length >0)
							{
								$(".mis-btn.disabled-btn").removeAttr("disabled");
								$(".mis-btn.disabled-btn").removeClass("disabled");
							}else{
								$(".mis-btn.disabled-btn").attr("disabled","disabled");
								$(".mis-btn.disabled-btn").addClass("disabled");
							}
						});
					});
					function editForm(){
						var value = getCheckedboxValues('${formId}','checkboxId',1);
						if(value!=''){
							window.location.href="${ctx}/cms_siteInfos/"+value+"/edit";
						}
					}
					
					function deleteSiteInfo(){
							var value = getCheckedboxValues('${formId}','checkboxId',1);
							if(value!=''){
								confirm('确认要删除选中记录吗？',function(r){
									$.ajaxDelete('${ctx}/cms_siteInfos/'+value, "", function(){
										mylayerFn.msg('删除成功！',{icon: 0, time: 2000},function(){
												 	$("#${formId}").submit();
											});
										    	
									});
								});
							} 
					}
			</script>
            </@siteInfosDirective>
</@lo.layout>