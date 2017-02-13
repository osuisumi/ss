			 <@siteInfoDetailDirective>
				<ul class="m-ft-nav">
			 		${siteInfo.footerHtml!}
				</ul>
				<div class="m-ft-info">
                    <div class="tdc">
                    	<#if siteInfo.weixinQrcode??>
                        <img src="${FileUtils.getFileUrl(siteInfo.weixinQrcode!)}" alt="">
                        </#if>
                    </div>
                    <div class="info">
                        <p>扫一扫右侧二维码，关注我们</p>
                        <p>${siteInfo.copyRight!}<br/>${siteInfo.icp!}</p>
                    </div>
                </div>
			 </@siteInfoDetailDirective>