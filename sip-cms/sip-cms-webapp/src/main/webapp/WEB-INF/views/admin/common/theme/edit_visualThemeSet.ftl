<#import "../include/layout.ftl" as lo> 
<@lo.layout>
		<div class="mis-inner-wrap">
                <div class="mis-mod">
                    <div class="mis-crm">
                        <div class="crm">
                            <a href="###"><i class="mis-home-ico"></i>首页</a>
                            <span class="trg">&gt;</span>
                            <a href="${ctx}/theme/visual_theme_sets/list">主题集管理</a>
                            <span class="trg">&gt;</span>
                            <a href="###">编辑主题集</a>
                        </div>
                    </div>
                    <div class="mis-srh-layout">
                    	<#import "../include/form.ftl" as cf>
						<@cf.form id="editVisualThemeSetsForm" method="put" action="${ctx}/theme/visual_theme_sets/${visualThemeSet.id}">
                        <ul class="mis-ipt-lst">
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题集ID：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text"  value="${visualThemeSet.id}"  class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题集名称：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="name" value="${visualThemeSet.name}" class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题集描述：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="description" value="${visualThemeSet.description}"  class="mis-ipt">
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
