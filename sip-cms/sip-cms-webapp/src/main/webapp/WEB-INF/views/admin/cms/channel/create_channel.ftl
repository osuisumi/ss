		<#import "../../common/include/form_layer.ftl" as fl>
		<@fl.form id="createArticlesForm" action="${ctx}/cms_channels" method="post">
		<ul class="mis-ipt-lst">
        	<li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>名称：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="name" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>别名(唯一)：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="alias" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>所属站点：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-select">
                            <select name="relationId">
                            	<@siteInfosDirective page="1" size="100">
                            		<#if siteInfoList??>
									<#list siteInfoList as siteInfo>
										<option value="${siteInfo.id}">${siteInfo.name}(${siteInfo.domain})</option>
									</#list>
									</#if>
                            	</@siteInfosDirective>
                            </select>
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
												<input type="text" id="channelParent" readonly  class="mis-ipt"   onclick="showMenu();"/>
												<a id="selectMenuBtn" href="javascript:;" class="selectBtn" onclick="showMenu(); return false;">请选择</a>
	                                        </div>                                        
	                                        <div id="menuZtreeContent" class="menuZtreeContent">
	                                        	<ul id="parentChannelTree" class="ztree selectTree"></ul>
	                                    	</div>
	                                    </div>	                                    
                                    </div>
                              </div>  
             </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>显示类型：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-select">
                            <select  name="displayType">
								<option value="ArticleList">文章列表</option>
								<option value="MarqueeImageArticle">滚动图文</option>
								<option value="MarqueeImage">轮播图片</option>
								<option value="Gallery">图片集</option>
								<option value="ResourceList">文件列表</option>
								<option value="CodeSegment">代码片段</option>
								<option value="Mixed">混合类型</option>
								<option value="RichText">富文本</option>
								<option value="TextInput">简单文本</option>
							</select> 
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>状态：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-select">
                            <select  name="state">
								<option value="draft">编辑</option>
								<option value="published">发布</option>
							</select> 
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>顺序号：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="orderNo" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>链接：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="url" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
 		</ul>
		</@fl.form>
		<#import "select_menu.ftl" as sm> 
		<@sm.selectMenu channelTreeId="parentChannelTree" onClick="selectParentChannel" channelInputId="channelParent" channelInputName="parent" menuContentId="menuZtreeContent" selectedId=pid!''>
			<script>
				function selectParentChannel(e, treeId, treeNode){
						$("input[name^='parent']").remove();
						$("#channelParent").append("<input type='hidden' name='parent.id' value='"+treeNode.id+"' />");
						$("#channelParent").val(treeNode.name);
				}
			</script>
		</@sm.selectMenu>