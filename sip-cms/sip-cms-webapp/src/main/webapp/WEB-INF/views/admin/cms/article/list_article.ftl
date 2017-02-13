<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js","${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"]/>
<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>
		<div class="mis-index-wrap">
			<div class="mis-column-innerBox mis-block-cont">
                        <div class="mis-column-innerL">
		                    <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>栏目</span>
                                </h2>
                                 <i class="mis-go-shrink"></i>    
                            </div> 
                            <div class="mis-ztree">
                                <ul id="misZtree" class="ztree"></ul>
                            </div>                    
             			</div>
					<#assign formId="listArticlesForm"/>
					<@articles page=param_page!"1" size=param_size!"10" alias=param_alias!"" state=param_state!""  title=param_title!"" isTop=param_isTop!"">
					<div class="mis-column-innerR">
                            <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>内容管理</span>
                                </h2>                                
                            </div>
                            <div class="mis-content" style="padding-left:10px;" id="articleContent">
							<#import "../../common/include/form.ftl" as cf>
							<@cf.form id="${formId}" action="${ctx}/cms_articles" method="get">
			                 <div class="mis-content">     
			                    <div class="mis-srh-layout">
			                        <ul class="mis-ipt-lst">                            
			                            <li class="item">
			                                <div class="mis-ipt-row">
			                                    <div class="tl">
			                                        <span>标题:</span>
			                                    </div>
			                                    <div class="tc">
			                                        <div class="mis-ipt-mod">
			                                            <input type="text" name="title" value="${param_title!""}" placeholder="请输入标题" class="mis-ipt">
			                                        </div>
			                                    </div>
			                                </div>
			                            </li>
			                            <li class="item">
			                                <div class="mis-ipt-row">
			                                    <div class="tl">
			                                        <span>状态:</span>
			                                    </div>
			                                    <div class="tc">
			                                        <div class="mis-select">
			                                            <select   name="state">   
														    <option value="">全部</option>   
														    <option value="published" <#if param_state?? && param_state == 'published'>selected</#if>>发布</option>   
														    <option value="draft" <#if param_state?? && param_state == 'draft'>selected</#if>>未发布</option>   
														</select>
			                                        </div>
			                                    </div>
			                                </div>
			                            </li> 
			                            <li class="item">
			                                <div class="mis-ipt-row">
			                                    <div class="tl">
			                                        <span>置顶:</span>
			                                    </div>
			                                    <div class="tc">
			                                        <div class="mis-select">
			                                            <select   name="isTop">   
														    <option value=""></option>   
														    <option value="Y" <#if param_isTop?? && param_isTop =='Y'>selected</#if>>是</option>   
														    <option value="N" <#if param_isTop?? && param_isTop! == 'N'>selected</#if>>否</option>   
														</select>
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
			                    	<div class="mis-table-srhTl">
			                                <i class="mis-srh-result"></i>
			                                <span>搜索结果</span>
			                        </div>
			                        <div class="mis-opt-row">
			                            <div class="mis-opt-mod fl">
			                                <a href="${ctx}/cms_articles/create" class="mis-btn mis-inverse-btn"><i class="mis-add-ico"></i>新建</a>
			                                <button type="button" class="mis-btn mis-inverse-btn disabled-btn  disabled" disabled="disabled" onclick="editForm('channelId=${channel.id}');" ><i class="mis-alter-ico"></i>修改</button>
			                                <button type="button" class="mis-btn mis-inverse-btn"><i class="mis-export-ico"></i>导出</button>
			                                <button type="button" class="mis-btn mis-inverse-btn disabled-btn disabled" disabled="disabled"><i class="mis-detele-ico"></i>删除</button>                                
			                            </div>
			                            <div class="selectedNum fr">
			                                  <span>已选中<strong class="num">0</strong>条记录</span>
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
			                                        <td>
			                                        <label class="mis-checkbox">
			                                               <strong>
			                                                    <i class="ico"></i>
			                                                    <input type="checkbox" name="checkboxId" class="checkRow" value="${article.id}">
			                                               </strong>
			                                        </label>
			                                        </td>
													<td>${article.title}</td>
													<td>
														<#if article.state?? && article.state! == 'published'>
															<button type="button"  class="mis-btn mis-inverse-btn"
																	onclick="unpublishArticle('${article.id}')">
																	<i class="mis-delete-ico"></i> 取消发布
															</button>
														<#else>
															<button type="button"  class="mis-btn mis-inverse-btn"
																	onclick="publishArticle('${article.id}')">
																	<i class="mis-alter-ico"></i>发布
															</button>
														</#if>
													</td>
													<td>
														<#if article.isTop?? && article.isTop == 'Y'>
																<button type="button"  class="mis-btn mis-inverse-btn"
																	onclick="cancelTopArticle('${article.id}')">
																	<i class="mis-untop-ico"></i> 取消置顶
																</button>
														<#else>
																<button type="button"  class="mis-btn mis-inverse-btn"
																	onclick="topArticletopArticle('${article.id}')">
																	<i class="mis-top-ico"></i> 置顶
																</button>
														</#if>
													</td>
													<td>
														<#if article.channels??>
															<#list article.channels as channel>
																${channel.name}&nbsp;
															</#list>
														</#if>
													</td>
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
			                </@cf.form>
			            </div><!--end inner page -->
			          </div>
		</div>
						<script type="text/javascript">					
								function editForm(){
									var value = getCheckedboxValues('${formId}','checkboxId',1);
									if(value!=''){
										window.location.href="${ctx}/cms_articles/"+value+"/edit";
									}
								}
						</script>
			            </@articles>
			            <script type="text/javascript">			
							function onClick(event, treeId, treeNode, clickFlag) {
								window.location.href="${ctx}/cms_articles/list?alias="+treeNode.alias;
							}	
						</script>
						<#import "../channel/tree_click.ftl" as tc> 
						<@tc.channelTreeClick channelTreeId="misZtree" alias="${param_alias!''}">
						</@tc.channelTreeClick>
</@lo.layout>