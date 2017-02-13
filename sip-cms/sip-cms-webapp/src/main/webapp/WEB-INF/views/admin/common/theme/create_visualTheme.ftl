<#import "../include/layout.ftl" as lo> 
<@lo.layout>
		<div class="mis-inner-wrap">
                <div class="mis-mod">
                    <div class="mis-crm">
                        <div class="crm">
                            <a href="###"><i class="mis-home-ico"></i>首页</a>
                            <span class="trg">&gt;</span>
                            <a href="${ctx}/theme/visual_themes/list">主题管理</a>
                            <span class="trg">&gt;</span>
                            <a href="###">新增主题</a>
                        </div>
                    </div>
                    <div class="mis-srh-layout">
                    	<#import "../include/form.ftl" as cf>
						<@cf.form id="createVisualThemesForm" action="${ctx}/theme/visual_themes" method="post">
                        <ul class="mis-ipt-lst">
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题ID：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="id" value="" placeholder="请输入主题ID..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题名称：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="name" value="" placeholder="请输入主题名称..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>所属主题集：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-select">
                                            <select name="visualThemeSet.id">
                                            	<@visualThemeSetsDirective page=param_page!"1" size=param_size!"100">
                                            		<#if visualThemeSetList??>
														<#list visualThemeSetList as visualThemeSet>	
															<option value="${visualThemeSet.id}">${visualThemeSet.name}</option>	
														</#list>
													</#if>
                                            	</@visualThemeSetsDirective>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </li>
							<li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题路径：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="resourceDir" value="" placeholder="请输入主题路径..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题描述：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="description" value="" placeholder="请输入主题描述..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        </@cf.form>
                    </div>
                </div><!--end module layout -->
            </div>
</@lo.layout>
