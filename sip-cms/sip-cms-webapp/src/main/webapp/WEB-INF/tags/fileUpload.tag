<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="relationId" type="java.lang.String" required="false"%>
<%@ attribute name="btnTitle" type="java.lang.String" required="false"%>
<%@ attribute name="fileLimit" type="java.lang.String" required="false"%> 
<%@ attribute name="isImage" type="java.lang.Boolean" required="false"%> 
<%@ attribute name="isList" type="java.lang.Boolean" required="false"%> 
<%@ attribute name="prefix" type="java.lang.String" required="false"%> 
<%@ taglib prefix="file" uri="http://file.haoyu.com" %>  
<div class="am-file-upload">
	<div class="am-ful-btn">
		<a href="javascript:void(0);" class="au-file-show-btn l-btn" id="picker"> <i class="au-link-ico"></i><span id="btnTitle">选择附件</span></a> 
	</div>
	<ul id="fileList" class="am-ful-lst">
	</ul>
</div>
<c:if test="${not empty relationId}">
	<script>
		$.get('${pageContext.request.contextPath}/file','relationId=${relationId}',function(data) {
			if (data != null) {
				var $tag_lst = $("#fileList");
				<c:choose>
					<c:when test="${isImage}">
						$.each(data,function(i, tag) {
							$tag_lst.append('<div id="' + this.id + '" class="fileLi file-item thumbnail">' +
								                '<img width=100 height=100 src="${file:getFileUrl("")}'+this.url+'">' +
								                '<div class="info">' +this.fileName+ '</div>' +
								            '</div>'
											);
						});
					</c:when>
					<c:otherwise>
						$.each(data,function(i, tag) {
							$tag_lst.append('<li class="fileLi success" fileId="'+this.id+'" fileName="'+this.fileName+'" url="'+this.url+'">'+
												'<a class="txt" href="${file:getFileUrl("")}'+this.url+'">'+this.fileName+'</a>'+
												'<a class="dlt" title="删除附件">×</a>'+
											'</li>'
											);
						});
						
					</c:otherwise>
				</c:choose>
				initFileParam();
			}
		});
	</script>
</c:if>
<script>
	$(function(){
		if('${btnTitle}' != ''){
			$('#btnTitle').text('${btnTitle}');
		}
		
		initRemoteUploader('${pageContext.request.contextPath}/file/uploadTemp.do','${fileLimit}','${isImage}','${isList}','${prefix}');
	});
</script>