<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout>
		<div class="mis-inner-wrap">
                <div class="mis-mod">
                    <div class="mis-crm">
                        <div class="crm">
                            <a href="###"><i class="mis-home-ico"></i>首页</a>
                            <span class="trg">&gt;</span>
                            <a href="${ctx}/cms_siteInfos/list">站点管理</a>
                            <span class="trg">&gt;</span>
                            <a href="###">编辑站点</a>
                        </div>
                    </div>
                    <div class="mis-srh-layout">
                    	<#import "../../common/include/form.ftl" as cf>
						<@cf.form id="editSiteInfoForm" action="${ctx}/cms_siteInfos/${siteInfo.id}"  method="put">
                        <ul class="mis-ipt-lst">
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>网站名称：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="name" value="${siteInfo.name!}" placeholder="${siteInfo.name!}" class="mis-ipt">
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
                                            <input type="text" name="domain" value="${siteInfo.domain!}" placeholder="${siteInfo.domain!}" class="mis-ipt">
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
                                            <input type="text" name="icp" value="${siteInfo.icp!}"  class="mis-ipt">
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
                                            <input type="text" name="description" value="${siteInfo.description!}" placeholder="${siteInfo.description!}" class="mis-ipt">
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
                                            <input type="text" name="copyRight" value="${siteInfo.copyRight!}"  class="mis-ipt">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>镜像文件夹：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" name="ctxPath" value="${siteInfo.mappingFolder!}" placeholder="${siteInfo.mappingFolder!}" class="mis-ipt">
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
															<option value="${visualTheme.id}" <#if (siteInfo.frontEndTheme.id!"")==visualTheme.id>selected</#if>>${visualTheme.name}</option>	
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
															<option value="${visualTheme.id}" <#if (siteInfo.adminTheme.id!"")==visualTheme.id>selected</#if>>${visualTheme.name}</option>	
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
                                           <textarea id="editor" name="footerHtml" class="ace" style="width:800px;height:400px;">${siteInfo.footerHtml!}</textarea>
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
