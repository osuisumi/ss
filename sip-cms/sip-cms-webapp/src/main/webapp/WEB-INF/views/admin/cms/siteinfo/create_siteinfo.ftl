<#assign cssArray=["${ctx}/js/ztree/css/zTreeStyle/zTreeStyle.css"]/>
<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.all-3.5.js"]/>
<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout cssArray=cssArray jsArray=jsArray>
		<div class="mis-inner-wrap">
                <div class="mis-mod">
                    <div class="mis-crm">
                        <div class="crm">
                            <a href="###"><i class="mis-home-ico"></i>首页</a>
                            <span class="trg">&gt;</span>
                            <a href="${ctx}/cms_articles/list">站点管理</a>
                            <span class="trg">&gt;</span>
                            <a href="###">新增站点</a>
                        </div>
                    </div>
                    <div class="mis-srh-layout">
                    	<#import "../../common/include/form.ftl" as cf>
						<@cf.form id="createSiteInfoForm" action="${ctx}/cms_siteInfos" method="post">
                        <ul class="mis-ipt-lst">
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>网站名称：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="name" value="" placeholder="请输入网站名称..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>网站域名：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="domain" value="" placeholder="请输入网站域名..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>网站备案号：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="icp" value="" placeholder="请输入网站备案号..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item w1">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>网站描述：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="description" value="" placeholder="" class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>网站版权：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="copyRight" value="" placeholder="请输入网站版权..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>镜像路径：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="ctxPath" value="" placeholder="请输入镜像路径..." class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>前端主题样式</span>
                                    </div>
                                    <div class="tc">
                                         <div class="mis-select">
                                            <select name="frontEndTheme.id">
                                            	<@visualThemesDirective page=param_page!"1" size=param_size!"100" visualThemeSetId="CMS_FRONT_END">
                                            		<#if visualThemeList??>
														<#list visualThemeList as visualTheme>	
															<option value="${visualTheme.id}">${visualTheme.name}</option>	
														</#list>
													</#if>
                                            	</@visualThemesDirective>
                                            </select>
                                        </div>
                                    </div>
                                </div>                               
                                
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>后台主题样式</span>
                                    </div>
                                    <div class="tc">
                                         <div class="mis-select">
                                            <select name="adminTheme.id">
                                            	<@visualThemesDirective page=param_page!"1" size=param_size!"100" visualThemeSetId="CMS_ADMIN">
                                            		<#if visualThemeList??>
														<#list visualThemeList as visualTheme>	
															<option value="${visualTheme.id}">${visualTheme.name}</option>	
														</#list>
													</#if>
                                            	</@visualThemesDirective>
                                            </select>
                                        </div>
                                    </div>
                                </div>                               
                                
                            </li>
                            <li class="item w1">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>网站页脚：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                           <textarea id="editor" class="ace" style="width:800px;height:400px;"></textarea>
                                           <#import "../../common/js/ace.ftl" as ace> 
                                           <@ace.aceditor selectors=[".ace"]>
            							   </@ace.aceditor>
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
