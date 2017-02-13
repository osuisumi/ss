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
                                 <i class="mis-go-shrink"></i>    
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
                            <div class="mis-content" id="codeSegmentContent">
                                
                                <@channelContentDirective channelId="${channel.id}" createIfNotExists="true">   					               
				                    <div class="mis-srh-layout">
				                    	<#import "../../../common/include/form.ftl" as cf>
										<@cf.form id="editChannelContentForm" action="${ctx}/cms_channels/channel_contents/${channel.id}" method="put">
				                        <ul class="mis-ipt-lst">			                            
				                            <li class="item w1">
				                                <div class="mis-ipt-row">
				                                    <div class="tl">
				                                        <span>PC端内容：</span>
				                                    </div>
				                                    <div class="tc">
				                                        <div class="mis-ipt-mod">
															<textarea id="editor" class="ace_content" style="width:800px;height:400px;"  name="content">${channelContent.content!}</textarea>
				                                        </div>
				                                    </div>
				                                </div>
				                            </li>
				                            <li class="item w1">
				                                <div class="mis-ipt-row">
				                                    <div class="tl">
				                                        <span>移动端内容：</span>
				                                    </div>
				                                    <div class="tc">
				                                        <div class="mis-ipt-mod">
															<textarea id="meditor" class="ace_mcontent" style="width:800px;height:400px;"  name="mobileContent">${channelContent.mobileContent!}</textarea>
				                                        </div>
				                                    </div>
				                                </div>
				                            </li>
				                        </ul>
				                        </@cf.form>                     
				                    </div>
			                   </@channelContentDirective> 
					            <#import "../../../common/js/ace.ftl" as ace> 
					            <@ace.aceditor selectors=[".ace_content",".ace_mcontent"]>
					            </@ace.aceditor>
                            </div>                                 
                        </div>
             </div>
		</div>		
		<#import "../../channel/tree_click.ftl" as tc> 
		<@tc.channelTreeClick channelTreeId="misZtree" selectedId="${channel.id}">
			<script>
				function onClick(event, treeId, treeNode, clickFlag) {
					window.location.href="${ctx}/cms_channels/edit_contents/"+treeNode.id;
				}	
			</script>
		</@tc.channelTreeClick>
</@lo.layout>