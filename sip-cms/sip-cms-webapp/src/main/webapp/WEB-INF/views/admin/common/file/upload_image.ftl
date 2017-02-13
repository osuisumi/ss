<#macro uploadImageFtl relationId="" relationType="" thumb="" thumbnailWidth="" compress="" paramName="fileInfos" paramType="list" divId="imageDiv" btnTxt="上传图片" fileNumLimit=9 onSubmit="$(this).submit();">
    					<div class="m-pbMod-udload">
							 <a href="javascript:void(0);" class="mis-opt-upbtn mis-inverse-btn picker"><i class="mis-upload-ico"></i>${btnTxt}</a>
						</div>
                        <ul class="fileList f-cb img-lst">
                            <li id="fileLiTemplet" class="fileLi block" style="display:none;">
                                <a href="javascript:;" class="deleteBtn del"></a>
                                <img>
                            </li>
                        </ul>
    	
                       
    <script>
    	   <#if relationId != ''>
					$.get('${ctx}/file','relationId=${relationId}&relationType=${relationType}',function(data) {
						if (data != null) {
							var httpHost='${FileUtils.getHttpHost()}'
							$.each(data,function(i, tag) {
								var $li = $('#fileLiTemplet').clone();
								$li.attr('id', this.id);
								$li.addClass('success').attr('fileId', this.id).show();
								//$li.attr('fileName', this.fileName);
								//$li.attr('url', this.url);
								//$li.find('.fileName').text(this.fileName);
								//$li.find('.fileBar').remove();
								$('#${divId}').find(".fileList").append($li);
								if(${fileNumLimit}==1){
									$("#${divId} .m-pbMod-udload").hide();	
								}
								$img = $li.find('img');
								$img.attr("src",httpHost+this.url);
							});
							initImageFileParam('${paramType}','${divId}', '${paramName}');
						}
					});
		</#if>
    	$(function(){
    		var imageUploader = WebUploader.create({
        		swf : '${ctx}/js/webuploader/Uploader.swf',
        		server : '${ctx}/file/uploadTemp',
        		pick : '#${divId} .picker',
        		resize : true,
        		duplicate : true,
        		accept: {
		            title: 'Images',
		            extensions: 'gif,jpg,jpeg,bmp,png',
		            mimeTypes: 'image/*'
		        }
		        <#if thumb!="">
		        ,${thumb}
		        </#if>
		        <#if compress!="">
		        ,compress:${compress}
		        </#if>
        	});
        	

        	// 当有文件被添加进队列的时候
        	imageUploader.on('fileQueued', function(file) {
        		var fileNumLimit = ${fileNumLimit};
        		var fileNum = $('#${divId}').find('.fileList').find(".fileLi").length;
            	if(fileNum > fileNumLimit){
            			alert('只允许上传${fileNumLimit}张图片');
            			imageUploader.removeFile(file.id);
            			return false;
            	}
        		
        		var $li = $('#fileLiTemplet').clone();
        		$li.attr('id', file.id).addClass('fileItem').show();
        		//$li.find('.fileName').text(file.name);
        		$('#${divId}').find(".fileList").append($li);
        		$img = $li.find('img');
        		 // 创建缩略图
	            // 如果为非图片文件，可以不用调用此方法。
	            // thumbnailWidth x thumbnailHeight 为 100 x 100
	            imageUploader.makeThumb(file, function (error, src) {
	                if (error) {
	                    $img.replaceWith('<span>不能预览</span>');
	                    return;
	                }
	                $img.attr('src', src);
	            }, 164, 110);
	            if(${fileNumLimit}==1){
					$("#${divId} .m-pbMod-udload").hide();	
				}
        		imageUploader.upload();
        	});
        	// 文件上传过程中创建进度条实时显示。
        	imageUploader.on('uploadProgress', function(file, percentage) {
        		//var $li = $('#' + file.id), $bar = $li.find('.fileBar');
        		// 避免重复创建
        		//var progress = Math.round(percentage * 100);
        		//$bar.find('.progressW').css('width', progress + '%');
        		//$bar.find('.barNum').text(progress + '%');
        		//$bar.find('.barTxt').text('已上传'+progress + '%');
        	});
        	imageUploader.on('uploadSuccess', function(file, response) {
        		if (response != null && response.responseCode == '00') {
        			var fileInfo = response.responseData;
        			$('#' + file.id).attr('fileId', fileInfo.id);
        			$('#' + file.id).attr('url', fileInfo.url);
        			$('#' + file.id).attr('fileName', file.name);
        			$('#' + file.id).addClass('success');
        			initImageFileParam('${paramType}','${divId}', '${paramName}');
        		}
        	});
        	imageUploader.on('uploadError', function(file) {
        		$('#' + file.id).find('.fileBar').addClass('error');
        		$('#' + file.id).find('.barTxt').text('上传出错');
        	});
        	imageUploader.on('uploadComplete', function(file) {
        		$('#' + file.id).find('.progress').fadeOut();
        	});


        	$('#${divId}').find(".fileList").on('click', '.deleteBtn', function() {
        		var _this = $(this);
        		confirm('是否确定删除该附件?',function(){
        			if (_this.parents('.fileLi').hasClass('fileItem')) {
        				imageUploader.removeFile(_this.parents('.fileLi').attr('id'));
        			}
        			_this.parents('.fileLi').remove();
        			initImageFileParam('${paramType}','${divId}', '${paramName}');
        			if(${fileNumLimit}==1){
        				$("#${divId} .m-pbMod-udload").show();
        			}
        		}); 
        	});
        	imageUploader.on('error', function(type) {
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
	    			$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].id" value="' + fileId + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].fileName" value="' + fileName + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].url" value="' + url + '" type="hidden"/>');
	    		}
	    	});
	    }
	    
	    
    </script>
</#macro>