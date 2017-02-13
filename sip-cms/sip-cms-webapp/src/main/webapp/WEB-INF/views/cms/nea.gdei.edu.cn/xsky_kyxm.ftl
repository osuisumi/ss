<#import "xsky_layout.ftl" as xsky>
<@xsky.layout>
	<div class="g-teacher-style">
          <#include "common/include/articles.ftl"/>  
    </div>
    <script>
    	$("#xskyMenu li").removeClass("z-crt");
    	$("#xskyMenu #m_xshd").addClass("z-crt");
    </script>
</@xsky.layout>