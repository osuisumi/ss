		<#import "../../common/include/form_layer.ftl" as fl>
		<@fl.form id="updateChannelForm" action="${ctx}/cms_channels/${channel.id}" method="put">
		<ul class="mis-ipt-lst">
        	<li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>名称：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="name" value="${channel.name!}" class="mis-ipt">
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
                            <input type="text" readonly value="${channel.alias!}" class="mis-ipt">
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
										<option value="${siteInfo.id}" <#if channel.relationId?? && (channel.relationId==siteInfo.id)>selected</#if>>${siteInfo.name}(${siteInfo.domain})</option>
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
                        <span>状态：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-select">
                            <select  name="state">
								<option value="draft">编辑</option>
								<option value="published" <#if channel.state?? && channel.state=='published'>selected</#if>>发布</option>
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
                            <input type="text" name="orderNo" value="${channel.orderNo!}" class="mis-ipt">
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
                            <input type="text" name="url" value="${channel.url!}" class="mis-ipt">
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
