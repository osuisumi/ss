<#import "xygk_layout.ftl" as xygk>
<@xygk.layout>
	<div class="g-teacher-style">
     	<div class="g-photo-lst">
             <ul class="m-photo-lst">
             	 <@photosDirective galleryRelationId="${channel.id}">
		        	<#list photoList as photo>    
			        	<li>
		                    <a href="javascript:;" class="block">
		                    <span class="img">
		                       <img src="${FileUtils.getFileUrl(photo.fileInfo.url!'')}" alt="">
		                    </span>
		                    <p>${photo.name!}</p>
		                    </a>
		                </li>
		        	</#list>
		        </@photosDirective>
                 
            </ul>
       </div>     
    </div>
    <script>
    	$("#xygkMenu li").removeClass("z-crt");
    	$("#xygkMenu #m_xyfc").addClass("z-crt");
    </script>
</@xygk.layout>