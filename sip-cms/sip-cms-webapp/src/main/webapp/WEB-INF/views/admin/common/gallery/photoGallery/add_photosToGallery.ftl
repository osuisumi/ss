<#import "../../file/upload_image_layer.ftl" as uill>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctx}/css/admin/neat.css">
<link rel="stylesheet" href="${ctx}/css/admin/blue/mis-style.css">
<link rel="stylesheet" href="${ctx}/js/mylayer/v1.0/skin/default/mylayer.css">
<link rel="stylesheet" href="${ctx }/js/webuploader/webuploader.css">
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/mis.js"></script>
<script type="text/javascript" src="${ctx }/js/sip-common.js"></script>
<script type="text/javascript" src="${ctx}/js/webuploader/webuploader.min.js"></script>
<script>
	function addPhotos(){
		if($("#imagesDiv li").length>1){
			var data = $.ajaxSubmit("photosForm");
			var json = $.parseJSON(data);
			if (json.responseCode == '00') {
				mylayerFn.msg('操作成功！',{icon: 0, time: 2000},function(){
					   window.location.reload();
				});
			}else{
				alert("操作失败，请重试！");			}
		}else{
			alert("请先上传图片然后再提交！");
		}
	}
	
	
</script>
</head>
<body >
	<form id="photosForm" action="${ctx}/gallery/photoGalleries/${photoGallery.id}/addPhotos" method="post">
    <div id="imagesDiv" class="mis-uplad-Aclayer">
	<@uill.uploadImagesLayer relationId="${photoGallery.id!}" divId="imagesDiv" paramName="photos[0].fileInfo" onSubmit="addPhotos()">
	
	</@uill.uploadImagesLayer>
	</div>
	</form>
</body>
</html>