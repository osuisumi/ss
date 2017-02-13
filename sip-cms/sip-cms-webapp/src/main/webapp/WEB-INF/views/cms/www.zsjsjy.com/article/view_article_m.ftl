<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8" />
<meta name="keywords" content="中山教师教育" />
<meta name="description" content="中山教师教育" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<title>中山教师教育</title>
<link rel="Shortcut Icon" href="${ctx}/css/cms/${mappingFolder!}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder!}/m/normalize.css">
<link rel="stylesheet" href="${ctx}/css/cms/${mappingFolder!}/m/style.css">
<link rel="stylesheet" href="${ctx}/js/swiper/swiper-3.3.1.min.css">
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/swiper/swiper-3.3.1.jquery.min.js"></script>
<script src="${ctx}/js/mobile_common.js"></script>
</head>
<body>
<!-- begin wrapper -->
<div id="wrapper">
    <!-- begin content -->
    <div class="g-inner-bd">
        <!--begin scroll wrap -->
        <div class="g-text-box">
            <article class="m-article-text">
                	<@articleDetailDirective articleId="${articleId}" incrBrowseNum="true">
                    <h2 class="u-tt">${article.title!}</h2>
                    <hgroup class="u-info">
                        <span>发布于<@formatTime longtime="${article.createTime!}" pattern="yyyy/MM/dd HH:mm">
														${date}
											   </@formatTime></span>
                        <span class="u-line">|</span>
                        <span><i class="u-ico-eyes"></i>${article.browseNumber!1}</span>
                    </hgroup>
                    <hgroup class="u-info">
                        <span><#if article.author??>撰稿人:${article.author}</#if></span>  
                        <span><#if article.contributedby??>供稿人:${article.contributedby}</#if></span>
                        <span><#if article.origin??>文章来源:${article.origin}</#if></span>
                    </hgroup>
                    <section class="m-txt-con">
                        		${article.content!}
                                <p id="file_lst"> 
									
								</p>
                    </section>
                    </@articleDetailDirective>
                     </article>
        </div>
        <!--end scroll wrap -->
    </div>
    <!-- end content -->
</div>

<!-- end wrapper -->
<script>
    $(function(){
		$.get('${ctx}/cms/file','relationId=${articleId}&relationType=cms-article',function(data) {
			var $file_lst = $("#file_lst");
			if (data != null) {
				$.each(data,function(i, tag) {
					$file_lst.append('附件'+(i+1)+':<a href="${FileUtils.getFileUrl("")}'+this.url+'" class="dld">'+this.fileName+'</a>');
				});
			}
		});
    })
</script>
</body>
</html>