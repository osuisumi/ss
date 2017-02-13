<div class="mis-hd-inner">
   	<h1 class="mis-logo">
          <a href="###">
                <img src="${ctx}/images/${mappingFolder}/admin-logo.png" alt="${siteInfo.name!}">
          </a>
   	</h1>
   	<div class="mis-tuser">
                <a href="javascript:;" class="tuser-show">
                    <img src="" alt="">
                    <strong class="txt"><span class="name">${ThreadContext.getUser().getRealName()}</span></strong>
                    <span class="status"><!--<i class="mis-super-ico"></i>超级管理员--></span>
                    <i class="trg"></i>
                </a>
                <div class="tuser-lst">
                    <i class="trg"></i>
                    <ul class="lst">
                        <li class="item"><a href="javascript:openUpdatePersonalPassword();" class="setting"><span class="txt">修改密码</span></a></li>
                        <li class="item last"><a href="${ctx}/logout" class="exit"><span class="txt">退出</span></a></li>
                    </ul>
                </div>
    </div>
</div>