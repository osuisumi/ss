<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form id="saveAdvertForm" action="${ctx}/advert/save" method="post">
	<input type="hidden" id="advertId" name="id" value="${advert.id }" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="alter-table-v">
		<tbody>
			<tr>
				<td width="13%">标题:</td>
				<td width="87%" style="text-align: left;"><input type="text"
					name="title" class="easyui-textbox" required
					value="${advert.title}" style="width: 90%" /></td>
			</tr>
			<tr>
				<td width="13%">序号:</td>
				<td width="87%" style="text-align: left;"><input type="text"
					name="sortNo" class="easyui-textbox" required
					value="${advert.sortNo}" style="width: 50%" /></td>
			</tr>			
			<tr>
				<td width="13%">位置:</td>
				<td width="87%" style="text-align: left;">
					<select name="location" class="easyui-combobox" style="width: 158px;" editable="false" required value="${advert.location}">
							${dict:getEntryOptionsSelected('ADVERT_LOCATION',advert.location)}
					</select>
				</td>
			</tr>
			<tr>
				<td width="13%">状态:</td>
				<td width="87%" style="text-align: left;">
					<select name="state" class="easyui-combobox" style="width: 158px;" editable="false" required value="${advert.state}">
						${dict:getEntryOptionsSelected('ADVERT_STATE',advert.state)}
				</select></td>
			</tr>			
			<tr>
				<td>图片:</td>
				<td colspan="3" style="text-align: left; height: 200px;">
					<div id="imagePreviewDiv" style="height:150px;">
						<c:choose>
							<c:when test="${not empty advert.imageUrl }">
								<img id="imagePreView"
									src="${file:getFileUrl(advert.imageUrl) }" width="200"
									height="150">
							</c:when>
							<c:when test="${empty advert.imageUrl }">
								<img id="imagePreView" width="200" height="150">
							</c:when>
						</c:choose>
					</div> 
					<a class="l-btn" id="picker"><span>上传图片</span></a> 
					<p class="help-block">仅支持JPG、JPEG、PNG格式（2M以下）,  建议图片尺寸: 1920 x 400</p>
					<ul id="fileList"></ul>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<div style="text-align: center">
		<button type="button" onclick="saveAdvert()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		initImageUploader($('#advertId').val(), 'advert');
	});

	function saveAdvert() {
		if (!$('#saveAdvertForm').form('validate')) {
			return false;
		}
		if($('#imagePreView').attr('src') == null){
			alert('请上传图片');
			return false;
		}
		var data = $.ajaxSubmit('saveAdvertForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_tabs_update('listAdvertForm', 'layout_center_tabs');
				easyui_modal_close('editAdvertDiv');
			});
		}
	}
</script>