<#macro uploadFileFtl relationId relationType="" paramName="fileInfos" paramType="list" divId="fileDiv" btnTxt="上传文件" fileNumLimit=0 fileTypeLimit="">
	<#if relationId != ''>
		<script>
			$.get('${ctx}/file','relationId=${relationId}&relationType=${relationType}',function(data) {
				if (data != null) {
					$.each(data,function(i, tag) {
						var $li = $('#${divId}').find('#fileLiTemplet').clone();
						$li.addClass('success').attr('fileId', this.id).show();
						//$li.attr('fileName', "");
						//$li.attr('url', "");
						$li.find('.fileName').text(this.fileName);
						$li.find('.fileBar').remove();
						$('#${divId}').find(".fileList").append($li);
					});
					initFileParam('${divId}', '${paramName}');
				}
			});
		</script>
	</#if>
	<ul class="fileList f-cb mis-sfile-lst">
       <li id="fileLiTemplet" class="fileLi" style="display: none;">
          <i class="mis-sfile-ico word"></i>
          <a href="javascript:void(0);" class="fileName name"></a>
          <span class="size"></span>
          <div class="fileBar mis-pbar">
          <div class="bar"><div class="barLength yet" style="width: 0%;"><span class="barNum"></span></div></div>
          <span class="barTxt bar-txt">上传中....</span>
          </div>
          <a href="javascript:void(0);" class="deleteBtn dlt"><i class="mis-delete-ico1"></i>删除</a>
       </li>                        
    </ul>
    <script>
    	$(function(){
    		var uploader = WebUploader.create({
        		swf : '${ctx}/js/webuploader/Uploader.swf',
        		server : '${ctx}/file/uploadTemp',
        		pick : '#${divId} .picker',
        		resize : true,
        		duplicate : true,
        		accept : {
        		    extensions: '${fileTypeLimit!}'
        		}
        	});
        	// 当有文件被添加进队列的时候
        	uploader.on('fileQueued', function(file) {
        		var fileNumLimit = parseInt('${fileNumLimit}');
        		if(fileNumLimit != 0){
        			var fileNum = $('#${divId}').find('.fileList').find(".fileLi").length;
            		if(fileNum > fileNumLimit){
            			alert('只允许上传'+fileNumLimit+'个附件');
            			uploader.removeFile(file.id);
            			return false;
            		}
        		}
        		var $li = $('#${divId} #fileLiTemplet').clone();
        		$li.attr('id', file.id).addClass('fileItem').show();
        		$li.find('.fileName').text(file.name);
        		$('#${divId}').find(".fileList").append($li);
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
        		$bar.find('.barLength').css('width', progress + '%');
        		$bar.find('.barNum').text(progress + '%');
        		$bar.find('.barTxt').text('上传中');
        	});
        	uploader.on('uploadSuccess', function(file, response) {
        		if (response != null && response.responseCode == '00') {
        			$('#' + file.id).find('.fileBar').addClass('finish');
        			$('#' + file.id).find('.barTxt').text('上传完成');
        			var fileInfo = response.responseData;
        			$('#' + file.id).attr('fileId', fileInfo.id);
        			$('#' + file.id).attr('url', fileInfo.url);
        			$('#' + file.id).attr('fileName', fileInfo.fileName);
        			$('#' + file.id).addClass('success');
        			initFileParam('${divId}', '${paramName}');
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
        			initFileParam('${divId}', '${paramName}');
        		});
        	});
        	uploader.on('error', function(type) {
        		if (type == 'Q_TYPE_DENIED') {
        			alert('请检查上传的文件类型');
        		}
        	}); 
    	});
	
	    function initFileParam(divId, paramName) {
	    	var $list = $('#'+divId).find(".fileList");
	    	$list.find('.fileParam').remove();
	    	$list.find('.fileLi.success').each(function(i) {
	    		var fileId = $(this).attr('fileId');
	    		var fileName = $(this).attr('fileName');
	    		var url = $(this).attr('url');
	    		if('${paramType}' == 'entity'){
	    			$(this).append('<input class="fileParam" name="'+paramName+'.id" value="' + fileId + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'.fileName" value="' + fileName + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'.url" value="' + url + '" type="hidden"/>');
	    		}else{
	    			$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].id" value="' + fileId + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].fileName" value="' + fileName + '" type="hidden"/>');
		    		$(this).append('<input class="fileParam" name="'+paramName+'[' + i + '].url" value="' + url + '" type="hidden"/>');
	    		}
	    	});
	    	initFileType($list);
	    }
	    
	    function initFileType(obj){
	    	var $file_name_par = obj.find(".fileLi");
	    	$file_name_par.each(function(){
	    		var _ts = $(this);
	    		var $names = _ts.find(".fileName").text(); //文件名字
	            var $file_ico = _ts.find(".mis-sfile-ico");
	            var strings = $names.split(".");
	            var s_length = strings.length;
	            var suffix = strings[s_length -1];
	            if(s_length == 1){
	               
	            }else {
	                if(suffix == "doc" || suffix == "docx"){
	                	$file_ico.addClass("doc");
	                }else if(suffix == "xls" || suffix == "xlsx"){
	                	$file_ico.addClass("excel");
	                }else if(suffix == "ppt" || suffix == "pptx"){
	                	$file_ico.addClass("ppt");
	                }else if(suffix == "pdf"){
	                	$file_ico.addClass("pdf");
	                }else if(suffix == "txt"){
	                	$file_ico.addClass("txt");
	                }else if(suffix == "zip" || suffix == "rar"){
	                	$file_ico.addClass("zip");
	                }else if(suffix == "jpg" || suffix == "jpeg" || suffix == "png" || suffix == "gif"){
	                	$file_ico.addClass("pic");
	                }else if(
	                    suffix == "mp4" || 
	                    suffix == "avi" || 
	                    suffix == "rmvb" || 
	                    suffix == "rm" || 
	                    suffix == "asf" || 
	                    suffix == "divx" || 
	                    suffix == "mpg" || 
	                    suffix == "mpeg" || 
	                    suffix == "mpe" || 
	                    suffix == "wmv" || 
	                    suffix == "mkv" || 
	                    suffix == "vob" || 
	                    suffix == "3gp"
	                    ){
	                	$file_ico.addClass("video");
	                }else {
	                	$file_ico.addClass("other");
	                }
	            }
	    	});
	    }
    </script>
</#macro>