<#import "../../common/include/form_layer.ftl" as fl>
	<@fl.form id="updateBannerForm" action="${ctx}/cms_banners/${banner.id}" method="put">
		<ul class="mis-ipt-lst">
        	<li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>标题：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="title" value="${banner.title!}" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>顺序号：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="orderNo" value="${banner.orderNo}" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>            
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>关联内容链接：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="articleLink" value="${banner.articleLink!}" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
 		</ul>
		</@fl.form>