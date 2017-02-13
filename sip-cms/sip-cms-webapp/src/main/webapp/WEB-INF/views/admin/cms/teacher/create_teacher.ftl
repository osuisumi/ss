<#assign cssArray=["${ctx }/js/webuploader/webuploader.css"]/>
<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js","${ctx}/js/ztree/js/jquery.ztree.excheck.js","${ctx}/js/webuploader/webuploader.min.js"]/>
<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray cssArray=cssArray>
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
             			<div class="mis-column-innerR">
                            <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>${channel.name!}内容管理</span>
                                </h2>                                
                            </div>
		                    <div class="mis-srh-layout">
		                    	<#import "../../common/include/form.ftl" as cf>
								<@cf.form id="createTeachersForm" action="${ctx}/cms_teachers" method="post" saveCallback="redirectEditContent()">
		                        <ul class="mis-ipt-lst">
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>姓名：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
		                                            <input type="text" name="fullName" value="" placeholder="请输入姓名..." class="mis-ipt">                                            
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                             <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>学历：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
		                                            <input type="text" name="eduBackground" value="" placeholder="请输入学历..." class="mis-ipt">
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>职称：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
		                                            <input type="text" name="jobTitle" value="" placeholder="请输入职称..." class="mis-ipt">
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>职务：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
													<input type="text" name="position" value="" placeholder="请输入职务..." class="mis-ipt">
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>		                            
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>个人头像：</span>
		                                    </div>
		                                    <div class="tc" id="imageDiv">
		                                        <#import "../../common/file/upload_image.ftl" as ui/> 
												<@ui.uploadImageFtl divId="imageDiv" relationId='' paramName="fileInfo" paramType="entity" fileNumLimit=1 />
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item w1">
							            <div class="mis-ipt-row">
							                <div class="tl">
							                    <span>个人简介</span>
							                </div>
							                <div class="tc">
							                	<div class="mis-ipt-mod">
							                    	<textarea id="editor" style="width: 95%; height: 250px;"  name="personalProfile" ></textarea>
							                 	</div>
							                 </div>
							             </div>
							          </li>
		                        </ul>
		                        </@cf.form>		                        
		                    </div>
                </div><!--end module layout -->
            </div>
            <script type="text/javascript">			
				function onClick(event, treeId, treeNode, clickFlag) {
					window.location.href="${ctx}/cms_channels/edit_contents/"+treeNode.id;
				}
				function redirectEditContent(){
					window.location.href="${ctx}/cms_channels/edit_contents/${channel.id}";
				}	
			</script>
			<#import "../channel/tree_click.ftl" as tc> 
			<@tc.channelTreeClick channelTreeId="misZtree">
			</@tc.channelTreeClick>
</@lo.layout>
