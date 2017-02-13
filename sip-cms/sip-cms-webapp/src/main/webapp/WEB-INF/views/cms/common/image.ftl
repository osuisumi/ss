<#macro imageFtl url default width="" height="">
	<#if url != ''>
		<img src=${FileUtils.getFileUrl(url)} <#if width??>width="${width}"</#if> <#if height??>height="${height}"</#if>>
		<#else>
		<img src=${default}>
	</#if>
</#macro>