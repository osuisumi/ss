		<div class="g-hd-b">
                <div class="g-auto">
                    <h1 id="m-logo">
					    <a href="${ctx}/cms/index">
                            <img src="${ctx}/images/cms/${mappingFolder}/web-logo.png" alt="中山教师继教育"><span class="hide-txt">中山教师继教育</span>
                        </a>
                    </h1>
                    <div class="m-lr-block1 fr">
                    	<form id="loginForm" method="post" target="_blank">
                        	<@channelContentDirective alias="login">
								<#if channelContent??>
									${channelContent.content!}
								</#if>
							</@channelContentDirective>
                        </form>
                        <div class="u-server" title="服务台">
                            <div class="m-tel-box">
                                <div class="u-tel-lst">
                                    <span class="u-bor bor1"></span>
                                    <span class="u-bor bor2"></span>
                                    <ul class="tel-ul">
                                    	<@channelContentDirective alias="fwt">
												<#if channelContent??>
													${channelContent.content!}
												</#if>
										</@channelContentDirective>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- <a href="javascript:void(0);" class="u-mis-log"><i class="u-mag"></i>后台管理</a> -->
                </div><!--end auto layout -->    
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
		