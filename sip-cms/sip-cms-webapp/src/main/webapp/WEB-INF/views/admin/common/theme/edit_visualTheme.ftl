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
                            <a href="###">编辑主题</a>
                        </div>
                    </div>
                    <div class="mis-srh-layout">
                    	<#import "../include/form.ftl" as cf>
						<@cf.form id="editVisualThemeForm" method="put" action="${ctx}/theme/visual_themes/${visualTheme.id}">
                        <ul class="mis-ipt-lst">
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>主题ID：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text"  value="${visualTheme.id}"  class="mis-ipt">
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
                                            <input type="text" name="name" value="${visualTheme.name}" class="mis-ipt">
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
                                            <input type="text" name="description" value="${visualTheme.description}"  class="mis-ipt">
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
