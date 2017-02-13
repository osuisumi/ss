<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div id="g-sidebar" style="margin-left: 0; width: 100%; overflow: auto; min-height: 400px;">
	<ul id="menuUl" class="m-side-menu">
		<li id="childMenu_1"><h3 class="tt">字典</h3>
			<a url="${ctx}/dictType/list?orders=CREATE_TIME.DESC" class="menu-item"><span>字典索引</span></a>
			<a url="${ctx}/dictEntry/list?orders=DICT_TYPE_CODE.ASC,SORT_NO.DESC" class="menu-item"><span>字典项</span></a>
		</li>
	</ul>
</div>
<script>
$(function(){

	$('#menuUl li a').click(function(){
		$('#menuUl li a').removeClass('z-crt');
		$(this).addClass('z-crt');
		easyui_tabs_add($('#layout_center_tabs'), $(this).attr('url'), $(this).find('span').text());
	});

});
</script>