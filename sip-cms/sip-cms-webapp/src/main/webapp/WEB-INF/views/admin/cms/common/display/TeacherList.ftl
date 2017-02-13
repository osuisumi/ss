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
					<#assign formId="listTeachersForm"/>
					<@teachersDirective page=param_page!"1" size=param_size!"10" relationId=channel.id!"" fullName=param_fullName!"">
					<div class="mis-column-innerR">
                            <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>${channel.name!}管理</span>
                                </h2>                                
                            </div>
                            <div class="mis-content"  id="teachersContent">
                            <#import "../../../common/include/form.ftl" as cf>
							<@cf.form id="${formId}" action="${ctx}/cms_channels/edit_contents/${channel.id}" method="get">
			                    <div class="mis-srh-layout">
			                        <ul class="mis-ipt-lst">                            
			                            <li class="item">
			                                <div class="mis-ipt-row">
			                                    <div class="tl">
			                                        <span>姓名:</span>
			                                    </div>
			                                    <div class="tc">
			                                        <div class="mis-ipt-mod">
			                                            <input type="text" name="fullName" value="${param_fullName!""}" placeholder="请输入姓名" class="mis-ipt">
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
			                                <a href="${ctx}/cms_teachers/create?channelId=${channel.id}" class="mis-btn mis-inverse-btn"><i class="mis-add-ico"></i>新建</a>
			                                <button type="button" class="mis-btn mis-inverse-btn disabled-btn  disabled" disabled="disabled" onclick="editForm('channelId=${channel.id}');" ><i class="mis-alter-ico"></i>修改</button>
			                                <button type="button" onclick="deleteTeacher()" class="mis-btn mis-inverse-btn disabled-btn disabled" disabled="disabled"><i class="mis-detele-ico"></i>删除</button>                                
			                            </div>
			                            <div class="selectedNum fr">
			                                  <span>已选中<strong class="num">0</strong>条记录</span>
			                             </div>
			                        </div>
			                        <div class="mis-table-mod">
			                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mis-table">
			                                <thead>
			                                    <tr>
			                                        <th width="5%"></th>													
													<th width="10%">姓名</th>
													<th width="8%">个人照片</th>
													<th width="8%">学历</th>
													<th width="8%">职称</th>
													<th width="10%">职务</th> 				
													<th width="5%">创建时间</th>	
			                                    </tr>
			                                </thead>
			                                <tfoot>
			                                	
			                                    <tr>
			                                        <td colspan="7">
			                                            <#import "../../../common/include/pagination.ftl" as page/>
														<@page.adminPage formId="${formId}" divId="" paginator=paginator/>
			                                        </td>
			                                    </tr>
			                                </tfoot>
			                                <tbody>												
												<#if teacherList??>
												<#list teacherList as teacher>
			                                    <tr>
			                                        <td>
			                                        <label class="mis-checkbox">
			                                               <strong>
			                                                    <i class="ico"></i>
			                                                    <input type="checkbox" name="checkboxId" class="checkRow" value="${teacher.id}">
			                                               </strong>
			                                        </label>
			                                        </td>													
													<td>${teacher.fullName!}</td>
													<td><#if teacher.avatar??><img src="${FileUtils.getFileUrl(teacher.avatar)}" style="height:48px;"></#if></td>
													<td>${teacher.eduBackground!}</td>
													<td>${teacher.jobTitle!}</td>
													<td>${teacher.position!}</td>
													<td>
														<@formatTime longtime="${teacher.createTime!}" pattern="yyyy-MM-dd HH:mm">
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
		</@teachersDirective>       
		</div>
						<script type="text/javascript">					
								function editForm(data){
									var value = getCheckedboxValues('${formId}','checkboxId',1);
									if(value!=''){
										if(data!=undefined&&data!=''){
											window.location.href="${ctx}/cms_teachers/"+value+"/edit?"+data;
										}else{
											window.location.href="${ctx}/cms_teachers/"+value+"/edit";
										}
									}
								}
								function deleteTeacher(){
									var value = getCheckedboxValues('${formId}','checkboxId',1);
									if(value!=''){
										confirm('确认要删除选中记录吗？',function(r){
											$.ajaxDelete('${ctx}/cms_teachers/'+value, "", function(){
												 mylayerFn.msg('删除成功！',{icon: 0, time: 2000},function(){
												 	$("#${formId}").submit();
												 });
										    	
											});
										});
									} 
								}
						</script>
			            <script type="text/javascript">			
							function onClick(event, treeId, treeNode, clickFlag) {
								window.location.href="${ctx}/cms_channels/edit_contents/"+treeNode.id;
							}	
						</script>
						<#import "../../channel/tree_click.ftl" as tc> 
						<@tc.channelTreeClick channelTreeId="misZtree" selectedId="${channel.id}">
						</@tc.channelTreeClick>
</@lo.layout>