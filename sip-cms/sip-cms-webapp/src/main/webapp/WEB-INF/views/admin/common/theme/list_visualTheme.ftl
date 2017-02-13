<#import "../include/layout.ftl" as lo> 
<@lo.layout>
		<#assign formId="visualThemesForm"/>
		<@visualThemesDirective page=param_page!"1" size=param_size!"10">
		<div class="mis-index-wrap">
				<form action="${ctx}/theme/visual_themes/list" method="get" id="${formId}">
                <div class="mis-mod">
                    <div class="mis-crm">
                        <div class="crm">
                            <a href="###"><i class="mis-home-ico"></i>首页</a>
                            <span class="trg">&gt;</span>
                            <em>主题管理</em>
                        </div>
                    </div> 
                    <div class="mis-content">  
	                    <div class="mis-srh-layout">
	                        <ul class="mis-ipt-lst">                            
	                            <li class="item">
	                                <div class="mis-ipt-row">
	                                    <div class="tl">
	                                        <span>主题名称：</span>
	                                    </div>
	                                    <div class="tc">
	                                        <div class="mis-ipt-mod">
	                                            <input type="text" name="" value="" placeholder="请输入主题名称..." class="mis-ipt">
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
	                                <a href="${ctx}/theme/visual_themes/create" class="mis-btn mis-inverse-btn"><i class="mis-add-ico"></i>新建</a>
	                                <button type="button" class="mis-btn mis-inverse-btn disabled-btn  disabled" disabled="disabled" onclick="editForm();" ><i class="mis-alter-ico"></i>修改</button>
	                                <button type="button" class="mis-btn mis-inverse-btn" onclick="console.log(getCheckedboxValues('testForm','checkboxId'))" ><i class="mis-import-ico"></i>导入</button>
	                                <button type="button" class="mis-btn mis-inverse-btn"><i class="mis-export-ico"></i>导出</button>
	                                <button type="button" class="mis-btn mis-inverse-btn disabled-btn disabled" disabled="disabled"><i class="mis-detele-ico"></i>删除</button>                                
	                            </div>
	                        </div>
	                        <div class="mis-table-mod">
	                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mis-table">
	                                <thead>
	                                    <tr>
	                                        <th width="5%">选项</th>
	                                        <th width="10%">ID</th>
	                                        <th width="20%">主题名称</th>
	                                        <th width="20%">主题集名称</th>                                        
	                                        <th width="20%">主题描述</th>
	                                        <th width="10%">创建时间</th>
	                                    </tr>
	                                </thead>
	                                <tfoot>
	                                	
	                                    <tr>
	                                        <td colspan="9">
	                                            <#import "../include/pagination.ftl" as page/>
												<@page.adminPage formId="${formId}" divId="" paginator=paginator/>
	                                        </td>
	                                    </tr>
	                                </tfoot>
	                                <tbody>
										<#if visualThemeList??>
										<#list visualThemeList as visualTheme>
	                                    <tr>
	                                        <td><input type="checkbox" name="checkboxId" value="${visualTheme.id}"></td>
	                                        <td>${visualTheme.id}</td>
	                                        <td>${visualTheme.name}</td>
	                                        <td>${visualTheme.visualThemeSet.name}</td>
	                                        <td>${visualTheme.description}</td>
	                                        <td>
												<@formatTime longtime="${visualTheme.createTime!}" pattern="yyyy-MM-dd HH:mm">
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
                    </div> 
                </div><!--end module layout -->
            </div><!--end inner page -->
            </form>
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
						var value = getCheckedboxValues('testForm','checkboxId',1);
						if(value!=''){
							window.location.href="${ctx}/theme/visual_themes/"+value+"/edit";
						}
					}
			</script>
            </@visualThemesDirective>
</@lo.layout>