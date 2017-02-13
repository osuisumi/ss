<@articleDetail articleId="${articleId}" incrBrowseNum="true">
	<#if article??>
		<#assign article=article>
	</#if>
</@articleDetail>
<#import "common/common.ftl" as c> 
<@c.html>
	<!--start #g-text-box 阅读内容-->
			<div id="g-text-box" class="g-space-null">
				<article class="g-text">
					<hgroup class="m-text-tt">
						<h1>${article.title!}</h1>
					</hgroup>
					<hgroup class="m-bn-info">
						<span class="txt">时间：<@formatTime longtime="${article.createTime!}" pattern="yyyy-MM-dd HH:mm">
														${date}
											   </@formatTime></span>
						<span class="txt">来源：${article.source!}</span>
						<span class="txt">撰稿人：${article.author!}</span>
						<span class="txt">浏览次数：${article.browseNumber!1}</span>
						
					</hgroup>
					<section class="m-text-con">
						${article.content!}
					</section>
				</article>
			</div>
			<!--end #g-login-box 阅读内容-->
</@c.html>
<script type="text/javascript">

$(function(){
	//显示侧边栏导航
	showSideNavFn();
	//开启模拟滚动条
	$("body").myScrollFn();
});

</script>