<#macro layout>
<#assign jsArray=["${ctx}/js/jquery.dsTab.js"]/>
<#import "common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray> 
	<div class="g-teacher-bn" id="teacherBn">
        <ul class="m-teacher-bn">
            <li>
                <a href="javascript:;">
                    <img src="${ctx}/images/cms/${mappingFolder}/tea-bn3.jpg" alt="">
                </a>
            </li>
        </ul>
    </div>
    
    <div class="g-bd" id="innerContent">
        <div class="g-auto">
            <div class="g-content">
                <div class="g-iframe contentFrame f-cb">
                    <div class="g-iframe-sd1">
                        <div class="g-sd-mod">
                            <div class="g-sd-tt">
                                <h3 class="tt">学院概况</h3>
                                <span>College Profile</span>
                            </div>
                            <div class="g-sd-dt">
                                <ul id="xygkMenu" class="m-item-lst">
                                    <@channelsDirective parentAlias='xygk'>	
				                    	<#list channelList as channel>
				                    		<li id="m_${channel.alias}" >
			                                    <a href="${ctx}/cms/${channel.alias}" >${channel.name}</a>
			                                </li>
										</#list>
									</@channelsDirective>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="g-iframe-mn1">
                        <div class="g-mn-mod">
                            <div class="m-crm">
                                <#import "common/include/bread_crumbs.ftl" as cru/> 
                        		<@cru.crumbs channelId="${channel.id}"/>  
                            </div>
                            <#nested/>
                        </div>
                    </div>
                </div>    
            </div>
        </div>
    </div>      
</@lo.layout>
</#macro>