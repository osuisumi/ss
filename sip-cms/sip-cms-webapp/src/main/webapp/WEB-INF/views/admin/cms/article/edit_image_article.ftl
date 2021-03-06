<#assign cssArray=["${ctx}/js/mylayer/v1.0/skin/default/mylayer.css","${ctx }/js/webuploader/webuploader.css"]/>
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
								<@cf.form id="createArticlesForm" action="${ctx}/cms_articles/${article.id}" method="put">
		                        <ul class="mis-ipt-lst">
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>标题：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
		                                            <input type="text" name="title" value="${article.title}" placeholder="${article.title}" class="mis-ipt">                                            
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>栏目：</span>
		                                    </div>
		                                    <div class="tc">
		                                    	<div class="mis-treeSelect">
			                                        <div class="mis-ipt-mod">
														<input type="text" id="articleChannel" readonly  class="mis-ipt"   onclick="showMenu();"/>
														<a id="selectMenuBtn" href="javascript:;" class="selectBtn" onclick="showMenu(); return false;">请选择</a>
			                                        </div>                                        
			                                        <div id="menuZtreeContent" class="menuZtreeContent">
			                                        	<ul id="channelTree" class="ztree selectTree"></ul>
			                                    	</div>
			                                    </div>	                                    
		                                    </div>
		                                </div>  
		                                <#assign channelArray="[]"/>
		                                <#if article.channels??>
		                                	<#assign channelArray="["/>
		                                	<#list article.channels as channel>
		                                		<#assign channelArray=channelArray+"\""+channel.id+"\""/>
		                                		<#if channel_has_next>
		                                			<#assign channelArray=channelArray+","/>
		                                		</#if>
		                                	</#list>                                	
		                                	<#assign channelArray=channelArray+"]"/>
		                                </#if>                             
		                                <#import "../channel/select_menu_checkbox.ftl" as smc> 
									    <@smc.selectMenuCheckbox channelInputId="articleChannel" channelInputName="channels" menuContentId="menuZtreeContent" checkedArray=channelArray>
									    </@smc.selectMenuCheckbox>
		                            </li>
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>置顶：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-select">
		                                            <select name="isTop">   
													    <option value="N">否</option>   
													    <option value="Y" <#if article.isTop=='Y'>selected</#if>>是</option>   
													</select>
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>撰稿人：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
		                                            <input type="text" name="author" value="${article.author!}" placeholder="${article.author!}" class="mis-ipt">
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>供稿人：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
		                                            <input type="text" name="contributedby" value="${article.contributedby!}" placeholder="${article.contributedby!}" class="mis-ipt">
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>来源：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
		                                            <input type="text" name="origin" value="${article.origin!}" placeholder="${article.origin!}" class="mis-ipt">
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                            <li class="item">
						                <div class="mis-ipt-row">
						                    <div class="tl">
						                        <span>配图：</span>
						                    </div>
						                    <div class="tc" id="frontCoverDiv">
						                    	<#import "../../common/file/upload_image.ftl" as ui/> 
						                    	<#assign thumb="{width:356,height:150,quality:70,type:'image/jpeg'}" compress="{width: 856, height: 360, quality: 90,allowMagnify: false,crop: false, preserveHeaders: true,noCompressIfLarger: false,compressSize: 50000}"/>
												<@ui.uploadImageFtl divId="frontCoverDiv" compress=compress relationId='${article.id}' relationType="cms-article-image" paramName="frontCoverImageFile" paramType="entity" fileNumLimit=1 />
						                    </div>
						                </div>
						            </li>
		                            <li class="item w1">
		                                <div class="mis-ipt-row">
		                                    <div class="tl">
		                                        <span>内容：</span>
		                                    </div>
		                                    <div class="tc">
		                                        <div class="mis-ipt-mod">
													<textarea id="editor" style="width:800px;height:600px;"  name="content">${article.content!}</textarea>
		                                        </div>
		                                    </div>
		                                </div>
		                            </li>
		                             <li class="item w1">
							            <div class="mis-ipt-row">
							                <div class="tl">
							                    <span>附件：</span>
							                </div>
							                <div class="tc"  id="fileDiv">
							                    <div class="m-pbMod-udload">
							                        <a href="javascript:void(0);" class="mis-opt-upbtn mis-inverse-btn picker"><i class="mis-upload-ico"></i>上传文件</a>
							                    </div>
								                <#import "../../common/file/upload_file.ftl" as uploadFileList /> 
												<@uploadFileList.uploadFileFtl relationId="${article.id}" relationType="cms-article"  paramName="fileInfos" />
							                 </div>
							             </div>
							          </li>
		                        </ul>
		                        </@cf.form>
		                        
		                    </div>
                </div><!--end module layout -->
            </div>
            <#import "../../common/js/ueditor.ftl" as ue> 
            <@ue.ueditor editorId="editor">
            </@ue.ueditor>
            <script type="text/javascript">			
				function onClick(event, treeId, treeNode, clickFlag) {
					window.location.href="${ctx}/cms_channels/edit_contents/"+treeNode.id;
				}	
			</script>
			<#import "../channel/tree_click.ftl" as tc> 
			<@tc.channelTreeClick channelTreeId="misZtree">
			</@tc.channelTreeClick>
</@lo.layout>
