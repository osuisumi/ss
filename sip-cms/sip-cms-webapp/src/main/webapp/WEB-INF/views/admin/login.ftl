<@siteInfoDetailDirective>
	<#assign siteInfo=siteInfo/>
	<#assign mappingFolder="${siteInfo.mappingFolder}"/>
</@siteInfoDetailDirective>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<title>${siteInfo.name!}</title>
<meta content="${siteInfo.name!}" name="author"/>
<link rel="Shortcut Icon" href="${ctx }/images/${mappingFolder}/favicon.ico">
<link rel="stylesheet" href="${ctx}/css/reset.css">
<link rel="stylesheet" href="${ctx}/css/login/login.css">
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dsTab.js"></script>
<script type="text/javascript" src="${ctx}/js/login.js"></script>
</head>
<body>
	<div id="g-logWrap">
		<div id="g-logTp">
			<div class="g-tpc f-cb">
				<h1 class="g-logo">
					<a href="${ctx}/cms/index" class="logo"><img src="${ctx}/images/${mappingFolder}/admin-logo.png" alt=""></a>
					<span class="txt">${siteInfo.name!}</span>
				</h1>
			</div>
		</div>
		<div id="g-log-bn">
			<ul class="m-slide-lst">
				<li><img src="${ctx}/images/${mappingFolder}/login-banner1.jpg" alt=""></li>
			</ul>
			<a href="javascript:void(0);" class="u-bn-prev"></a>
			<a href="javascript:void(0);" class="u-bn-next"></a>
		</div>
		<div id="g-logBox">
			<div class="m-log-box">
				<div class="m-tab-li f-cb">
					<a href="javascript:void(0);" class="item1 z-crt"><span>账号登录</span></a>
				</div>
				<div class="m-tab-cont f-cb">
					<div class="m-log-block">
						<form id="fm1" action="${ctx }/admin/login" method="post">
							<label class="login-block m-ipt-mod m-ipt-account">
								<input id="username" name="username" class="required u-ipt" tabindex="1" placeholder="用户名" type="text" value="" size="25" autocomplete="off"/>
								<div id="usernameError" class="login-popup-hint">
									<i></i>
									<span class="txt">请输入正确的帐号！</span>
								</div>
								<b class="icon-war-error ico"></b>
							</label>
							<label class="login-block m-ipt-mod m-ipt-password">
								<input id="password" name="password" class="required u-ipt" tabindex="2" placeholder="密码" type="password" value="" size="25" autocomplete="off"/>
								<div id="passwordError" class="login-popup-hint">
									<i></i>
									<span class="txt">你的密码不正确，请重新输入！</span>
								</div>
								<b class="icon-war-error ico"></b>
							</label>
							<div class="m-row f-cb">

							</div>
							<div class="m-btn-row">
								<button type="submit" class="main-btn1 btn"><em>登录</em></button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="g-logft">
			<div class="g-ftc">
				${cmsBackstageFooter?replace('{now}',(.now?string("yyyy")))}
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#g-log-bn').dsTab({
				itemEl        : '.m-slide-lst li',
				btnElName     : 'm-fouse',
		        btnItem       : 'a',
			    maxSize       : 5,
			    currentClass  : 'z-crt', //按钮当前样式
		        autoCreateTab : false,
			    prev          : '.u-bn-prev',
		        next          : '.u-bn-next',
			    changeType    : 'fade',
			    change        : true,
			    changeTime    : 5000,
			    overStop      : false,
			});
			if('${errorMsg!''}' != ''){
			 $('#passwordError .txt').text('${errorMsg!''}');
			 $('#passwordError').show();
			}
		});
	</script>
	<script type="text/javascript" charset="utf-8" src="http://lead.soperson.com/20001894/10064293.js"></script>
</body>
</html>