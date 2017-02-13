<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js"]/>
<#import "../../../common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>
		<div class="mis-index-wrap">
			<div class="mis-column-innerBox mis-block-cont">
                        <div class="mis-column-innerL">
		                    <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>栏目</span>
                                </h2>  
                            </div> 
                            <div class="mis-ztree">
                                <ul id="misZtree" class="ztree"></ul>
                            </div>                    
             			</div>
					<#assign formId="listArticlesForm"/>
					<@articlesDirective page=param_page!"1" size=param_size!"10" channelId=channel.id state=param_state!""  title=param_title!"" isTop=param_isTop!"">
					<div class="mis-column-innerR">
                            <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>${channel.name!}内容管理</span>
                                </h2>                                
                            </div>
                            <div class="mis-content" style="padding-left:10px;" id="articleContent">
							<#import "../../../common/include/form.ftl" as cf>
							<@cf.form id="${formId}" action="${ctx}/cms_channels/edit_contents/${channel.id}" method="get">
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
			                        </ul>
			                        <div class="mis-btn-row indent1">
			                            <button type="submit" class="mis-btn mis-main-btn"><i class="mis-srh-ico"></i>查询</button>
			                            <button type="reset" class="mis-btn mis-default-btn"><i class="mis-refresh-ico"></i>重置</button>
			                        </div>
			                    </div>
			                    <div class="mis-table-layout">
			                    	<div class="mis-table-srhTl">
			                                <i class="mis-srh-result"></i>
			                                <span>搜索结果</span>
			                        </div>
			                        <div class="mis-opt-row">
			                            <div class="mis-opt-mod fl">
			                                <a href="${ctx}/cms_articles/create/image_article?channelId=${channel.id}" class="mis-btn mis-inverse-btn"><i class="mis-add-ico"></i>新建</a>
			                                <button type="button" class="mis-btn mis-inverse-btn disabled-btn  disabled" disabled="disabled" onclick="editForm('channelId=${channel.id}');" ><i class="mis-alter-ico"></i>修改</button>
			                                <button type="button" onclick="deleteArticle()" class="mis-btn mis-inverse-btn disabled-btn disabled" disabled="disabled"><i class="mis-detele-ico"></i>删除</button>                                
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
			                                        <th width="30%">图片</th>
													<th width="20%">标题</th>
													<th width="5%">状态</th>
													<th width="5%">创建时间</th>	
			                                    </tr>
			                                </thead>
			                                <tfoot>
			                                	
			                                    <tr>
			                                        <td colspan="5">
			                                            <#import "../../../common/include/pagination.ftl" as page/>
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
			                                       <td style="height:200px;"><img src="${FileUtils.getFileUrl(article.frontCover!'')}" alt=""></td> 
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
			                    </div> <!--end module layout -->
			                </@cf.form>
			            </div><!--end inner page -->
			          </div>
		</div>
						<script type="text/javascript">					
								function editForm(data){
									var value = getCheckedboxValues('${formId}','checkboxId',1);
									if(value!=''){
										if(data!=undefined&&data!=''){
											window.location.href="${ctx}/cms_articles/"+value+"/edit/image_article?"+data;
										}else{
											window.location.href="${ctx}/cms_articles/"+value+"/edit/image_article";
										}
									}
								}
								
								function publishArticle(id){
										$.ajaxPut('${ctx}/cms_articles/'+id, "state=published", function(){
								    		$("#${formId}").submit();
										});
								}
								
								function unpublishArticle(id){
										$.ajaxPut('${ctx}/cms_articles/'+id, "state=draft", function(){
								    		$("#${formId}").submit();
										});
								}
								
								
								function deleteArticle(){
									var value = getCheckedboxValues('${formId}','checkboxId',1);
									if(value!=''){
										confirm('确认要删除选中记录吗？',function(r){
											$.ajaxDelete('${ctx}/cms_articles/'+value, "", function(){
										    	$("#${formId}").submit();
											});
										});
									} 
								}
						</script>
			            </@articlesDirective>
			            <script type="text/javascript">			
							function onClick(event, treeId, treeNode, clickFlag) {
								window.location.href="${ctx}/cms_channels/edit_contents/"+treeNode.id;
							}	
						</script>
						<#import "../../channel/tree_click.ftl" as tc> 
						<@tc.channelTreeClick channelTreeId="misZtree" selectedId="${channel.id}">
						</@tc.channelTreeClick>
</@lo.layout>