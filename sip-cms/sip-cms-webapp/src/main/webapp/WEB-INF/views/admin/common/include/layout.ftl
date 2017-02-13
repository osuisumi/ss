<#macro layout cssArray=[] jsArray=[]>
<#escape x as x?html>
<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN"> 
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/css/admin/neat.css">
<link rel="stylesheet" href="${ctx}/css/admin/blue/mis-style.css">
<link rel="stylesheet" href="${ctx}/js/mylayer/v1.0/skin/default/mylayer.css">
<#if cssArray??>
	<#list cssArray as css>
<link rel="stylesheet" href="${css}">	
	</#list>
</#if>
<title>${siteInfo.name!}</title>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/mylayer/v1.0/mylayer.js"></script>
<script type="text/javascript" src="${ctx}/js/mis.js"></script>
<script type="text/javascript" src="${ctx }/js/sip-common.js"></script>
<#if jsArray??>
	<#list jsArray as js>
<script type="text/javascript" src="${js}"></script>	
	</#list>
</#if>
<script type="text/javascript">
$(function(){
    //树打开关闭
    <#if menuTreeId??>
    	misTreeFn('${menuTreeId}',true);
    <#else>
    	misTreeFn(1,true);
    </#if>
    treecolor();
});
function openUpdatePersonalPassword(){
	mylayerFn.open({
		id: "updateUserPasswordLayer",
		type: 2,
		title: '修改密码',
		content: '${ctx}/auth_users/editPersonalPassword',
		area: [500,400],
		zIndex: 19999,
		yes: {
				close: true,
				btnName: '.mylayer-confirm',
				yesFn : function(){
					                
				}
		}

	});
}
</script>
</head>
<body>
<!--begin wrap -->
<div class="mis-wrap">
    <!--begin header -->
    <div class="mis-hd">
        <#include "header.ftl" />
    </div>
    <!--end header -->
    <!--begin side -->
    <div class="mis-sd" id="misSide">
    	<div class="mis-shrink-tree"><a href="javascript:;" class="opt"></a></div>
        <div class="mis-tree-wp">
            <#include "authMenuTree.ftl"/>
        </div>   
    </div>
    <!--end side  -->
    <!--begin content body -->
    <div class="mis-bd" id="misContent">
        <!--begin content body inner -->
        <div class="mis-bd-inner">
           <#nested/>
			 <!--begin footer frame -->
	        <div class="mis-ft">
	            <#include "footer.ftl"/>
	        </div>
        </div>
        <!--end content body inner -->
       
        <!--end footer frame -->
    </div>
    <!--end content body -->
</div>
<!--end wrap -->

</body>
</html>
</#escape>
</#macro>