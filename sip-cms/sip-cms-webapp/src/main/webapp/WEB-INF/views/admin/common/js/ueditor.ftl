<#macro ueditor  editorIds=[] editorId="editor"> 
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
<script>
	<#if editorIds?? && (editorIds?size > 0)>
		<#list editorIds as editor>
			var ue_${editor};
			$(function(){
				UE.delEditor('${editor}');
				ue_${editor} = UE.getEditor('${editor}',{
					toolbars: [
					           [
					               'source',
					               'bold', //加粗
					               'italic', //斜体
					               'underline', //下划线
					               'strikethrough', //删除线
					               'fontborder', //字符边框
					               'horizontal', //分隔线
					               'fontfamily', //字体
					               'fontsize', //字号
					               'justifyleft', //居左对齐
					               'justifyright', //居右对齐
					               'justifycenter', //居中对齐
					               'justifyjustify', //两端对齐
					               'forecolor', //字体颜色
					               'backcolor', //背景色
					               'lineheight', //行间距
					               'map',
					               'simpleupload', //单图上传
					               'insertimage' //多图上传
					           ]
					       ],
					scaleEnabled: true,
					allowDivTransToP: false
				});
				ue_${editor}.ready(function() {
				    ue_${editor}.execCommand('serverparam', {
				        'relations': '${ThreadContext.getUser().getId()}',
				    });
				});
			});
		</#list>
	<#else>
		var ue_${editorId};
		$(function(){
			UE.delEditor('${editorId}');
			ue_${editorId} = UE.getEditor('${editorId}',{
				toolbars: [
				           [
				               'source',
				               'bold', //加粗
				               'italic', //斜体
				               'underline', //下划线
				               'strikethrough', //删除线
				               'fontborder', //字符边框
				               'horizontal', //分隔线
				               'fontfamily', //字体
				               'fontsize', //字号
				               'justifyleft', //居左对齐
				               'justifyright', //居右对齐
				               'justifycenter', //居中对齐
				               'justifyjustify', //两端对齐
				               'forecolor', //字体颜色
				               'backcolor', //背景色
				               'lineheight', //行间距
				               'simpleupload', //单图上传
				               'insertimage' //多图上传
				           ]
				       ],
				scaleEnabled: true,
				allowDivTransToP: false
			});
			ue_${editorId}.ready(function() {
			    ue_${editorId}.execCommand('serverparam', {
			        'relations': '${ThreadContext.getUser().getId()}',
			    });
			});
		});
	</#if>
	
</script>	
</#macro>