<#macro layout>
<#assign jsArray=["${ctx}/js/jquery.dsTab.js","${ctx}/js/jquery.placeholder.min.js","${ctx}/js/myFocus/jquery.flexslider-min.js"]/>
<#import "common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>  
            <div class="g-bd-index">
                <div class="g-auto">
				 <div class="g-frame-mod">
                    <div class="g-crumbs">
                        <div class="m-crumbs">   
                        	<#import "common/include/bread_crumbs.ftl" as cru/> 
                        	<@cru.crumbs channelId="${channel.id}"/>                                                 
                        </div><!--end m-crumbs 面包屑导航-->
                    </div><!--end g-crumbs-->
                    <div class="g-layout">
	                    <div class="g-school-infor">
	                        <div class="m-infor-side">
	                            <ul id="xygkMenu" class="side-menu">
	                                <@channelsDirective parentAlias='xygk'>	
				                    	<#list channelList as channel>
				                    		<li class="menu-li">
			                                    <a href="${ctx}/cms/${channel.alias}" id="m_${channel.alias}"><i></i>${channel.name}</a>
			                                </li>
										</#list>
									</@channelsDirective>
	                            </ul>
	                        </div>
	                        <div class="m-infor-mn">
	                           <#nested>
	                        </div>
	                    </div>
	                </div>
	   			 </div>
	         </div>
	    </div>
</@lo.layout>
</#macro>







