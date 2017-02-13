<#macro pagination paginator pageForm type divId>
<#if paginator.totalPages &gt; 1>
		<#if paginator.page == 1>
			<a href="###" class="prev disabled">&lt;</a>
		<#else>
			<a href="###" class="prev" onclick="previous('${pageForm}','${type}','${divId }')">&lt;</a>
		</#if>
	<#if paginator.page != 1>
		<a name="pageNo_1" href="###" onclick="indexPage('${pageForm}','${type}','${divId }')">1</a>
	</#if>
	<#if paginator.page &gt; 3>
		<a name="pageNo_${paginator.page-2}" href="###" onclick="goPage('${pageForm}','${type}','${divId }','${paginator.page-2}')">${paginator.page-2}</a>
	</#if>
	<#if paginator.page &gt; 2>
		<a name="pageNo_${paginator.page-1}" href="###" onclick="goPage('${pageForm}','${type}','${divId }','${paginator.page-1}')">${paginator.page-1}</a>
	</#if>

	<a name="pageNo_${paginator.page}" class="z-crt">${paginator.page}</a>

	<#if paginator.page + 1 &lt; paginator.totalPages>
		<a name="pageNo_${paginator.page+1}" href="###" onclick="goPage('${pageForm}','${type}','${divId }','${paginator.page+1}')">${paginator.page+1}</a>
	</#if>
	<#if paginator.page + 2 &lt; paginator.totalPages>
		<a name="pageNo_${paginator.page+2}" href="###" onclick="goPage('${pageForm}','${type}','${divId }','${paginator.page+2}')">${paginator.page+2}</a>
	</#if>
	<#if paginator.page != paginator.totalPages>
		<a name="pageNo_${paginator.totalPages}" href="###" onclick="goPage('${pageForm}','${type}','${divId }','${paginator.totalPages}')">${paginator.totalPages}</a>
	</#if>

	<#if paginator.page == paginator.totalPages>
			<a href="###" class="next disabled">&gt;</a>
	<#else>
			<a href="###" class="next" onclick="next('${pageForm}','${type}','${divId }')">&gt;</a>
	</#if>
</#if>
<input type="hidden" class="page" name="page" value="${paginator.page }">
<input type="hidden" class="totalPages" value="${paginator.totalPages}">
<script>
	function indexPage(formId,type,divId){
		$("#"+formId+" .page").val(1);
		submitPage(formId,type,divId);
	}
	
	function lastPage(formId,type,divId){
		var totalPages = $("#"+formId+" .totalPages");
		$("#"+formId+" .page").val(totalPages.val());
		submitPage(formId,type,divId);
	}
	
	function previous(formId,type,divId){
		var page = $("#"+formId+" .page");
		if(parseInt($(page).val())>1){
			$(page).val(parseInt($(page).val())-1);
			submitPage(formId,type,divId);
		}else{
			alert("当前为第一页");
		}
	}
	
	function next(formId,type,divId){
		var page = $("#"+formId+" .page"); 
		var totalPages = $("#"+formId+" .totalPages");
		if(parseInt($(page).val())<parseInt(totalPages.val())){	
			$(page).val(parseInt($(page).val())+1);
			submitPage(formId,type,divId);
		}else{ 
			alert("已经是最后一页");
		}
	}
	
	function skipPage(formId,type,divId){
		var goPage = $("#"+formId+" .page");
		var totalPages = $("#"+formId+" .totalPages");
		if(parseInt($(goPage).val()) <= parseInt(totalPages.val())&&parseInt($(goPage).val())>=1){
			$("#"+formId+" .page").val($(goPage).val());
			submitPage(formId,type,divId);
		}else{
			alert("页数不存在");
		}
	}
	
	function goPage(formId,type,divId,pageNo){
		$("#"+formId+" .page").val(pageNo);
		submitPage(formId,type,divId);
	}
	
	function submitPage(formId,type,divId){
			if(type != null && type == 'ajax'){
				$.ajaxQuery(formId, divId);
			}else{
				document.getElementById(formId).submit();
			}
	}
</script> 
</#macro>