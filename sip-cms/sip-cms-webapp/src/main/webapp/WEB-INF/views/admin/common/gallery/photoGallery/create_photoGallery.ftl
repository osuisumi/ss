<#assign cssArray=["${ctx}/js/mylayer/v1.0/skin/default/mylayer.css","${ctx }/js/webuploader/webuploader.css"]/>
<#assign jsArray=["${ctx}/js/webuploader/webuploader.min.js"]/>
<#import "../../include/layout_layer.ftl" as ll> 
<@ll.layout jsArray=jsArray cssArray=cssArray> 	
		<#import "../../include/form.ftl" as cf>
		<@cf.form id="createPhotoGalleryForm" action="${ctx}/gallery/photoGalleries" method="post" saveCallback="reloadWindow">
		<input type="hidden" name="relation.id" value="${relationId!}"/>
		<div class="mis-layer-wrap">
		<ul class="mis-ipt-lst">
        	<li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>名称：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="name" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>描述：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="alias" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>            
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>封面图片：</span>
                    </div>
                    <div class="tc" id="fileDiv">
                    	<div class="m-pbMod-udload">
							 <a href="javascript:void(0);" class="mis-opt-upbtn mis-inverse-btn picker"><i class="mis-upload-ico"></i>上传图片</a>
						</div>
                        <ul class="fileList f-cb img-lst">
                            <li id="fileLiTemplet" class="fileLi block" style="display:none;">
                                <a href="javascript:;" class="deleteBtn del"></a>
                                <img>
                            </li>
                        </ul>
                    </div>
                </div>
            </li> 
 		</ul>
 		</div>
		</@cf.form>
		<script>
			function reloadWindow(){
				window.location.reload();
			}
		</script>
		<script>
    	$(function(){
    		var uploader = WebUploader.create({
        		swf : '${ctx}/js/webuploader/Uploader.swf',
        		server : '${ctx}/file/uploadTemp',
        		pick : '#fileDiv .picker',
        		resize : true,
        		fileNumLimit:1,
        		duplicate : true,
        		accept: {
		            title: 'Images',
		            extensions: 'gif,jpg,jpeg,bmp,png',
		            mimeTypes: 'image/*'
		        }
        	});
        	// 当有文件被添加进队列的时候
        	uploader.on('fileQueued', function(file) {
        		var fileNumLimit = 1
        		var fileNum = $('#fileDiv').find('.fileList').find(".fileItem").length;
            	if(fileNum > fileNumLimit){
            			alert('只允许上传1张图片');
            			uploader.removeFile(file.id);
            			return false;
            	}
        		
        		var $li = $('#fileLiTemplet').clone();
        		$li.attr('id', file.id).addClass('fileItem').show();
        		$li.find('.fileName').text(file.name);
        		$('#fileDiv').find(".fileList").append($li);
        		$img = $li.find('img');
        		 // 创建缩略图
	            // 如果为非图片文件，可以不用调用此方法。
	            // thumbnailWidth x thumbnailHeight 为 100 x 100
	            uploader.makeThumb(file, function (error, src) {
	                if (error) {
	                    $img.replaceWith('<span>不能预览</span>');
	                    return;
	                }
	                $img.attr('src', src);
	            }, 164, 110);
        		$(".m-pbMod-udload").hide();
        		uploader.upload();
        	});
        	// 文件上传过程中创建进度条实时显示。
        	uploader.on('uploadProgress', function(file, percentage) {
        		//var $li = $('#' + file.id), $bar = $li.find('.fileBar');
        		// 避免重复创建
        		//var progress = Math.round(percentage * 100);
        		//$bar.find('.progressW').css('width', progress + '%');
        		//$bar.find('.barNum').text(progress + '%');
        		//$bar.find('.barTxt').text('已上传'+progress + '%');
        	});
        	uploader.on('uploadSuccess', function(file, response) {
        		if (response != null && response.responseCode == '00') {
        			var fileInfo = response.responseData;
        			$('#' + file.id).attr('fileId', fileInfo.id);
        			$('#' + file.id).attr('url', fileInfo.url);
        			$('#' + file.id).attr('fileName', file.name);
        			$('#' + file.id).addClass('success');
        			initImageFileParam('entity','fileDiv', 'fileInfo');
        		}
        	});
        	uploader.on('uploadError', function(file) {
        		$('#' + file.id).find('.fileBar').addClass('error');
        		$('#' + file.id).find('.barTxt').text('上传出错');
        	});
        	uploader.on('uploadComplete', function(file) {
        		$('#' + file.id).find('.progress').fadeOut();
        	});
        	$('#fileDiv').find(".fileList").on('click', '.deleteBtn', function() {
        		var _this = $(this);
        		confirm('是否确定删除该附件?',function(){
        			if (_this.parents('.fileLi').hasClass('fileItem')) {
        				uploader.removeFile(_this.parents('.fileLi').attr('id'));
        			}
        			_this.parents('.fileLi').remove();
        			initImageFileParam('entity','fileDiv', 'fileInfo');
        			$(".m-pbMod-udload").show();
        		});
        	});
        	uploader.on('error', function(type) {
        		if (type == 'Q_TYPE_DENIED') {
        			alert('请检查上传的文件类型');
        		}
        	}); 
    	});
	
	    function initImageFileParam(paramType,divId, paramName) {
	    	var $list = $('#'+divId).find(".fileList");
	    	$list.find('.fileParam').remove();
	    	$list.find('.fileLi.success').each(function(i) {
	    		var fileId = $(this).attr('fileId');
	    		var fileName = $(this).attr('fileName');
	    		var url = $(this).attr('url');
	    		$(this).append('<input class="fileParam" name="'+paramName+'.id" value="' + fileId + '" type="hidden"/>');
		    	$(this).append('<input class="fileParam" name="'+paramName+'.fileName" value="' + fileName + '" type="hidden"/>');
		    	$(this).append('<input class="fileParam" name="'+paramName+'.url" value="' + url + '" type="hidden"/>');
	    	});
	    }
	    
	    
    </script>
</@ll.layout>