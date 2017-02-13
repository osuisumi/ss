<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout>
		<#assign formId="articlesForm"/>
		<@articles page=param_page!"1" size=param_size!"10" alias=alias!"" state=param_state!""  title=param_title!"" isTop=param_isTop!"">
		<div class="mis-inner-wrap">
				<form action="${ctx}/cms_articles/list" method="get" id="${formId}">
                <div class="mis-mod">
                    <div class="mis-crm">
                        <div class="crm">
                            <a href="###"><i class="mis-home-ico"></i>首页</a>
                            <span class="trg">&gt;</span>
                            <em>栏目管理</em>
                        </div>
                    </div>   
                    <div class="mis-srh-layout">
                        <ul class="mis-ipt-lst">                            
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>栏目名称</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="title" value="${param_title!""}" placeholder="请输入内容标题" class="mis-ipt">
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
                                <a href="${ctx}/cms_articles/create" class="mis-btn mis-inverse-btn"><i class="mis-add-ico"></i>新建</a>
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
                                        <th width="2%"></th>
										<th width="25%">标题</th>
										<th width="5%">状态</th>
										<th width="5%">置顶</th>
										<th width="5%">所在栏目</th> 
										<th width="2%">浏览次数</th>
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
									<#if articleList??>
									<#list articleList as article>
                                    <tr>
                                        <td><input type="checkbox" name="checkboxId" value="${article.id}"></td>
										<td style="text-align:left">${article.title}</td>
										<td>
											<#if article.state?? && article.state! == 'published'>
												<button type="button"  class="easyui-linkbutton"
														onclick="unpublishArticle('${article.id}')">
														<i class="fa fa-ban-circle"></i> 取消发布
												</button>
											<#else>
												<button type="button"  class="easyui-linkbutton"
														onclick="publishArticle('${article.id}')">
														<i class="fa fa-edit"></i>发布
												</button>
											</#if>
										</td>
										<td>
											<#if article.isTop?? && article.isTop == 'Y'>
													<button type="button"  class="easyui-linkbutton"
														onclick="cancelTopArticle('${article.id}')">
														<i class="fa icon-ban-circle"></i> 取消置顶
													</button>
											<#else>
													<button type="button"  class="easyui-linkbutton"
														onclick="topArticletopArticle('${article.id}')">
														<i class="fa fa-flag"></i> 置顶
													</button>
											</#if>
										</td>
										<td>${article.channel.name!}</td>
										<td>${article.browseNumber!}</td>
										<td>
											<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
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
							window.location.href="${ctx}/cms_articles/"+value+"/edit";
						}
					}
			</script>
            </@articles>
</@lo.layout>