		<div class="g-auto">
            <div class="m-tp-nav">
                <a href="http://www.gdei.edu.cn" target="_blank"><i class="u-home-ico"></i>学校首页</a>
                <span class="line">|</span>
                <a href="javascript:;"><i class="u-mail-ico"></i>院长信箱</a>
                <span class="line">|</span>
                <a href="javascript:;"><i class="u-msg-ico"></i>联系我们</a>
            </div>
            <div class="m-tp-login">
                <div class="m-selectblock">
                    <a href="javascript:;" class="show-txt">
                        <span class="txt">请选择登录平台</span>
                        <i class="trg"></i>
                    </a>
                    <dl class="lst">
                        <dd>
                            <a href="javascript:;">登录平台一</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">登录平台二</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">登录平台三</a>
                        </dd>
                    </dl>
                </div>
                <input type="text" placeholder="用户名" id="userName" name="username">
                <input type="password" placeholder="密码" id="password" name="password">
                <a href="javascript:;" class="btn">登录</a>
            </div>
        </div>
		<script>
	       	    function checkLogin()
				{
					if ($("#userName").val() == '') {
							alert('请输入用户名！');
							$("#userName").focus();
							return false;
					} else if ($("#password").val() == '') {
							alert('请输入登陆密码！');
							$("#password").focus();
							return false;
					} 
					return true;
				}
	       		function login(action,passwordName){
	       			if(checkLogin()){
	       				$("#loginForm").attr("action",action);
	       				$("#password").attr("name",passwordName);
	       				$("#loginForm").submit();
	       			}
	       		}
	    </script>