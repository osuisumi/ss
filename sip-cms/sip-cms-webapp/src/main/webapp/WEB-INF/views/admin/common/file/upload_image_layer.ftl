<#macro uploadImagesLayer relationId relationType="" paramName="fileInfos" paramType="list" divId="fileDiv"  fileNumLimit=9 onSubmit="$(this).submit();">
        <div class="picker mis-upLay-img">
            <a href="javascript:;" class="u-uplay-img"></a>
            <p class="txt">请选择图片，每次最多${fileNumLimit}个</p>            
        </div>
        <ul class="fileList f-cb mis-hadup-img">
            <li id="fileLiTemplet" class="fileLi" style="display: none;">
            	<img><i class="deleteBtn u-close">&times;</i>
            	<div class="fileBar u-progress">
            		<ins class="progressW"></ins><span class="barTxt txt">已上传0%</span>
            	</div>
          	</li>
        </ul>             
        <div class="mis-btn-row mis-subBtn-row">
            <button type="button" onclick="${onSubmit}" class="mis-btn mis-main-btn">提交</button>
            <button type="button"  class="mis-btn mis-default-btn mylayer-cancel">取消</button>
        </div>
    </div>
    <script>
    	$(function(){
    		var uploader = WebUploader.create({
        		swf : '${ctx}/js/webuploader/Uploader.swf',
        		server : '${ctx}/file/uploadTemp',
        		pick : '#${divId} .picker',
        		resize : true,
        		duplicate : true,
        		fileNumLimit:${fileNumLimit},
        		accept: {
		            title: 'Images',
		            extensions: 'gif,jpg,jpeg,bmp,png',
		            mimeTypes: 'image/*'
		        }
        	});
        	// 当有文件被添加进队列的时候
        	uploader.on('fileQueued', function(file) {
        		var fileNumLimit = parseInt('${fileNumLimit}');
        		if(fileNumLimit != 0){
        			var fileNum = $('#${divId}').find('.fileList').find(".fileItem").length;
            		if(fileNum > fileNumLimit){
            			alert('只允许上传'+fileNumLimit+'个图片');
            			uploader.removeFile(file.id);
            			return false;
            		}
        		}
        		
        		var $li = $('#fileLiTemplet').clone();
        		$li.attr('id', file.id).addClass('fileItem').show();
        		$li.find('.fileName').text(file.name);
        		$('#${divId}').find(".fileList").append($li);
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
        		
        		uploader.upload();
        	});
        	// 文件上传过程中创建进度条实时显示。
        	uploader.on('uploadProgress', function(file, percentage) {
        		var $li = $('#' + file.id), $bar = $li.find('.fileBar');
        		// 避免重复创建
        		/* if (!$percent.length) {
        			$li.find('.state').html('<div class="sche">' + '<div class="bl">' + '<div class="bs" role="progressbar" style="width: 0%"></div>' + '</div>' + '<span class="num">' + '0%' + '</span>' + '<span class="status"></span>' + '</div>');
        			$percent = $li.find('.sche');
        		} */
        		var progress = Math.round(percentage * 100);
        		$bar.find('.progressW').css('width', progress + '%');
        		//$bar.find('.barNum').text(progress + '%');
        		$bar.find('.barTxt').text('已上传'+progress + '%');
        	});
        	uploader.on('uploadSuccess', function(file, response) {
        		if (response != null && response.responseCode == '00') {
        			//$('#' + file.id).find('.fileBar').addClass('finish');
        			//$('#' + file.id).find('.barTxt').text('已上传');
        			var fileInfo = response.responseData;
        			$('#' + file.id).attr('fileId', fileInfo.id);
        			$('#' + file.id).attr('url', fileInfo.url);
        			$('#' + file.id).attr('fileName', file.name);
        			$('#' + file.id).addClass('success');
        			initImageFileParam('${paramType}','${divId}', '${paramName}');
        		}
        	});
        	uploader.on('uploadError', function(file) {
        		$('#' + file.id).find('.fileBar').addClass('error');
        		$('#' + file.id).find('.barTxt').text('上传出错');
        	});
        	uploader.on('uploadComplete', function(file) {
        		$('#' + file.id).find('.progress').fadeOut();
        	});
//        	$('#uploadBtn').click(function() {
//        		uploader.upload();
//        	});
        	$('#${divId}').find(".fileList").on('click', '.deleteBtn', function() {
        		var _this = $(this);
        		confirm('是否确定删除该附件?',function(){
        			if (_this.parents('.fileLi').hasClass('fileItem')) {
        				uploader.removeFile(_this.parents('.fileLi').attr('id'));
        			}
        			_this.parents('.fileLi').remove();
        			initImageFileParam('${paramType}','${divId}', '${paramName}');
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
	    		if(paramType == 'entity'){
	    			$(this).append('<input class="fileParam" name="'+paramName+'.id" value="' + fileId + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'.fileName" value="' + fileName + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'.url" value="' + url + '" type="hidden"/>');
	    		}else{
	    			if(paramName.indexOf("[0]")>0){
	    				$(this).append('<input class="fileParam" name="'+paramName.replace(/\[0\]/, "["+i+"]")+'.id" value="' + fileId + '" type="hidden"/>');
			    		$(this).append('<input class="fileParam" name="'+paramName.replace(/\[0\]/, "["+i+"]")+'.fileName" value="' + fileName + '" type="hidden"/>');
			    		$(this).append('<input class="fileParam" name="'+paramName.replace(/\[0\]/, "["+i+"]")+'.url" value="' + url + '" type="hidden"/>');
	    			}else{
		    			$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].id" value="' + fileId + '" type="hidden"/>');
			    		$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].fileName" value="' + fileName + '" type="hidden"/>');
			    		$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].url" value="' + url + '" type="hidden"/>');
		    		}
	    		}
	    	});
	    }
	    
	    
    </script>
</#macro>