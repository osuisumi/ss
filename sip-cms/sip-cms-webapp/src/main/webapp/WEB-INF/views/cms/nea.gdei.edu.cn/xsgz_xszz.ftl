<#import "xsgz_layout.ftl" as xsgz>
<@xsgz.layout>
	<div class="g-teacher-style">
          <#include "common/include/articles.ftl"/>  
    </div>
    <script>
    	$("#xsgzMenu li").removeClass("z-crt");
    	$("#xsgzMenu #m_xszz").addClass("z-crt");
    </script>
</@xsgz.layout>