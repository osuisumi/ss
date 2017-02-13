<#import "../../include/form_layer.ftl" as fl>
		<@fl.form id="updatePersonalPasswordForm" action="${ctx}/auth_users/updatePersonalPassword" onConfirm="updatePersonalPassword()" method="put">
		<ul class="mis-ipt-lst">
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>旧密码：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="password" id="sourcePassword" name="sourcePassword" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>新密码：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="password" id="newPassword" name="newPassword" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>重复新密码：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="password" id="repeatPassword" name="repeatPassword" value="" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
 		</ul>
</@fl.form>
<script>
	function updatePersonalPassword(){
		if($("#sourcePassword").val()==''){
			mylayerFn.msg('请输入旧密码！',{icon: 0, time: 2000});
			return;
		}
		if($("#newPassword").val()==''||$("#newPassword").val().length<6){
			mylayerFn.msg('新密码不能为空且不能小于6位长度！',{icon: 0, time: 2000});
			return;
		}
		if($("#repeatPassword").val()!=$("#newPassword").val()){
			mylayerFn.msg('输入的新密码与重复密码不一致！',{icon: 0, time: 2000});
			return;
		}
		
		var data = $.ajaxSubmit("updatePersonalPasswordForm");
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
				mylayerFn.msg('操作成功！',{icon: 0, time: 2000},function(){
					reloadWindow();
				});
		}else{
				mylayerFn.msg(json.responseMsg,{icon: 0, time: 3000})
		}
		
	}
</script>