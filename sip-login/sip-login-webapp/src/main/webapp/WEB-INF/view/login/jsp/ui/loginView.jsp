<jsp:directive.include file="includes/top.jsp" />
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<c:choose>
		<c:when test="${not empty param.ts and param.ts eq 'failure'}">
	  	loginJs.indexs.accountPopupHint({
			txt : "该账号操作过于频繁，请稍后再试！"
		});
		</c:when>
		<c:otherwise>
		//帐号错误提示框
		<c:forEach items="${flowRequestContext.messageContext.allMessages}" var="message">
			<c:choose>
		    <c:when test="${message.source eq 'username'}">
			    loginJs.indexs.accountPopupHint({
					txt : "${message.text}"
				});
		    </c:when>
		    <c:when test="${message.source eq 'password'}">
			    loginJs.indexs.passwordPopupHint({
					txt : "${message.text}"
				});
	    	</c:when>
		    </c:choose>
	  	</c:forEach>
	  	</c:otherwise>
  	</c:choose>
  	
	/*loginJs.indexs.accountPopupHint({
		txt : "请输入正确的帐号哦！"
	});*/

	//密码错误提示框
	/*loginJs.indexs.passwordPopupHint({
		txt : "密码错误，请重新输入密码！"
	});*/
	

})
</script>
<script src="${ctx}/js/sockjs-0.3.4.js"></script>
<script src="${ctx}/js/stomp.js"></script>
<script type="text/javascript">
        var stompClient = null;
        
        function connect() {
        	var socket = new SockJS("<c:url value='/ws/qrcodeLogin'/>");
            stompClient = Stomp.over(socket);  
            stompClient.connect({}, function (frame) {
                console.log(frame); // Inside frame object queue-suffix not sended
               // var suffix = frame.headers['queue-suffix'];
               // console.log(suffix);
              //  var destination = "/user/"+$("#qrcode").attr("code")+"/queue/qrcodeLogin/";
                var destination = "/topic/login";
                //console.log("destination:"+destination);
                stompClient.subscribe("/queue/login-"+$("#qrcode").attr("code"),function(qrcodeLoginResult ) {
                    // 处理状态更新
                    console.log(JSON.parse(qrcodeLoginResult.body));
                    var credential = JSON.parse(qrcodeLoginResult.body).credential;
                    if(!$.isEmptyObject(credential)){
                    	console.log(credential.appToken);
                    	alert(credential.id);
                    	$("#loginForm input[name='username']").val(credential.id);
                    	//$("#loginForm input[name='password']").val(credential.id);
                    	$("#qrparam input[name='at']").val(credential.appToken);
                    	$("#loginForm").append($("#qrparam"));
                    	$("#loginForm").submit();
                    }
                  	
                  });
                
            });
        }
        
        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }
        
        function sendQRCodeLoginTicketId() {
        //	stompClient.send("/app/qrcodeLogin", {}, "QRCLT_${loginTicket}");
        	alert("aaa");
        	stompClient.send("/app/qrcodeLogin", {}, $("#qrcode").attr("code"));
        }
        
        function showGreeting(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
        }
     
    </script>
<!--start #g-tp 顶部--><!--  class="g-fx-tp" -->
		<div id="g-logTp">
			<div class="g-tpc f-cb">
				<!--start #g-logo 标志-->
				<h1 class="g-logo"><a href="###" class="logo"><img src="images/logo.png" alt=""></a><span class="txt">昊誉信息科技有限公司</span></h1>
				<!--end #g-logo 标志-->
			</div>
		</div>
		<!--end #g-tp 顶部-->
		<!--start .g-log-bn-->
		<div id="g-log-bn">
			<ul class="m-slide-lst">
				<li><img src="images/login-banner1.jpg" alt=""></li>
				<li><img src="images/login-banner2.jpg" alt=""></li>
			</ul>
			<a href="javascript:void(0);" class="u-bn-prev"></a>
			<a href="javascript:void(0);" class="u-bn-next"></a>
		</div>
		<!--end .g-log-bn-->
		
		<!--start #g-logBox 登录框-->
		<div id="g-logBox">
			<div class="m-hint">
				<span class="c-txt">来试试更安全便捷的登录方式</span>				
				<span class="u-trg1"></span>
			</div>
			<div class="m-log-box">
				<div class="m-tab-li f-cb">
					<a href="javascript:void(0);" class="item1 z-crt"><span>账号登录</span></a>
					<a href="javascript:void(0);" class="item2"><span><i class="u-tdc"></i>二维码登录</span></a>
				</div>
				<div class="m-tab-cont f-cb">
					<div class="m-log-block">
						<form:form method="post" id="loginForm"  commandName="${commandName}" htmlEscape="true" onsubmit="return loginJs.indexs.formValidate();">		
											
							<label class="m-ipt-mod m-ipt-account">
								<input type="text" name="username" placeholder="昊誉账号/手机号" class="u-ipt">
								<div class="login-popup-hint">
									<i></i>
									<span class="txt">请输入正确的帐号！</span>
								</div>
								<b class="icon-war-error ico"></b>
								<div class="m-slt-mod">
									<a href="javascript:void(0);" class="show-text">
										<span class="txt">账号</span><i class="u-trg2"></i>
									</a>
									<div class="txt-lst">
										<a href="#" class="z-crt">账号</a>
										<a href="#">手机号</a>
									</div>
								</div>
							</label>
							<label class="m-ipt-mod m-ipt-password">
								<input type="password" name="password" placeholder="密码" class="u-ipt">
								<div class="login-popup-hint">
									<i></i>
									<span class="txt">你的密码不正确，请重新输入！</span>
								</div>
								<b class="icon-war-error ico"></b>
							</label>
							<div class="m-row f-cb">
								<label for="" class="m-diy-checkbox" onselectstart="return false">
									<i class="u-checkbox"></i>
									<span>10天内免登录</span>
								</label>
								<a href="#" class="fg-pw">找回密码</a>
							</div>
							<div class="m-btn-row">
								<input type="hidden" name="lt" value="${loginTicket}" />
								<input type="hidden" name="execution" value="${flowExecutionKey}" />								
								<input type="hidden" name="_eventId" value="submit" />
								<button type="submit" class="main-btn1 btn"><em>登录</em><span><i class="u-trg3"></i></span></button>
							</div>
							<div class="link-reg">
								<span>还没有昊誉账号？<a href="#">免费注册</a></span>
							</div>
						</form:form>
						<span id="qrparam">
							<input type="hidden" name="qrcltId">
							<input type="hidden" name="at">
						</span>
					</div><!--end .m-log-block 帐号密码登录-->
					<div class="m-tdc-block">
						<h4>安全登录，防止被盗</h4>
						<div class="m-tdc-img">
							<img src="" id="qrcode" onclick="sendQRCodeLoginTicketId();" code="" alt="二维码">
						</div>
						<span class="c-ex">请使用昊誉手机版扫描二维码</span>
					</div><!--end .m-log-block 二维码登录-->
				</div>
			</div>
		</div>
<jsp:directive.include file="includes/bottom.jsp" />
