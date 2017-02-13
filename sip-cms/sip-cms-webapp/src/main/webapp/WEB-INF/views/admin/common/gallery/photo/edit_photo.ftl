		<#import "../../include/form.ftl" as cf>
		<@cf.form id="updatePhotoForm" action="${ctx}/gallery/photos/${photo.id}" method="put" saveCallback="reloadWindow">
		<div class="mis-layer-wrap">
		<ul class="mis-ipt-lst">
        	<li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>名称：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="name" value="${photo.name!}" class="mis-ipt">
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
                            <input type="text" name="orderNo" value="${photo.orderNo}" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>            
 		</ul>
 		</div>
		</@cf.form>
		<script>
			function reloadWindow(){
				window.location.reload();
			}
		</script>