<#import "../include/simple_layout_m.ftl" as slo> 
<@slo.layout pullUpEvent="nextPage()">
    <!-- begin content -->
    <div class="g-bd article" id="content">
        <!--begin scroll wrap -->
        <div class="g-scroller-wrap" id="scroller">
            <!-- begin book lst -->
            <div class="g-book-lst">
                <ul class="m-book-lst">
                <@resourcesDirective relationId="${channel.id!}" page=param_page!'1'  size=param_size!"6">
	                 <#if resourceList??>
		             <#list resourceList as resource>   
	                    <li>
	                        <a href="${FileUtils.getFileUrl(resource.url!'')}" class="block">
	                            <span class="img"><img src="${FileUtils.getFileUrl(resource.frontCover!'')}" alt=""></span>
	                            <p>${resource.name}</p>
	                        </a>
	                    </li>
	                  </#list>
	                  </#if> 
	                  <#assign paginator=paginator>
	            </@resourcesDirective>                   
                </ul>
            </div>
            <!-- end book lst -->
            <div id="pullUp">  
                <div class="pullUpIcon">
                    加载中<span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>  
                <div class="pullUpTxt">上拉显示更多...</div>  
            </div>
        </div>
        <!--end scroll wrap -->
    </div>
    <!-- end content -->
    <script type="text/javascript">
        	var  totalPages= ${paginator.totalPages};
			var currentPage = ${paginator.page};
			function nextPage(){
					if(totalPages-currentPage>0){	
				           $.get("${ctx}/cms/${channel.id}/loadMore",{page:currentPage+1,size:${paginator.limit}},function(data){
				           			$(".m-book-lst").append(data);
				           			currentPage=currentPage+1;
				           });
				     }
		     }
	</script>
</@slo.layout>