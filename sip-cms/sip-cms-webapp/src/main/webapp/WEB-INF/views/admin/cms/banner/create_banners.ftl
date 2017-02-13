	<#import "../../common/include/form_layer.ftl" as fl>
	<form id="bannersForm" action="${ctx}/cms_banners/batch_create/${relationId}"  method="post">
    <div id="imagesDiv" class="mis-uplad-Aclayer">
    <#import "../../common/file/upload_image_layer.ftl" as uill>
	<@uill.uploadImagesLayer relationId="${relationId!}" divId="imagesDiv" paramName="fileInfos" onSubmit="createBanners()">
	
	</@uill.uploadImagesLayer>
	</div>
	</form>
	<script>
		function createBanners(){
			if($("#imagesDiv li").length>1){
				var data = $.ajaxSubmit("bannersForm");
				var json = $.parseJSON(data);
				if (json.responseCode == '00') {
					mylayerFn.msg('操作成功！',{icon: 0, time: 2000},function(){
						   window.location.reload();
					});
				}else{
					alert("操作失败，请重试！");
				}
			}else{
				alert("请先上传图片然后再提交！");
			}
		}
	</script>	