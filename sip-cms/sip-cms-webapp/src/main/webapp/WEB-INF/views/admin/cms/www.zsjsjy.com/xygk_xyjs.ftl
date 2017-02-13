<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js","${ctx}/js/jquery-ui.min.js","${ctx}/js/jqthumb.min.js","${ctx}/js/webuploader/webuploader.min.js","${ctx}/js/photoAlbum.js"]/>
<#assign cssArray=["${ctx}/js/webuploader/webuploader.css"]/>
<#import "../../common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray cssArray=cssArray>
		<div class="mis-index-wrap">
			<div class="mis-column-innerBox mis-block-cont">
                        <div class="mis-column-innerL">
		                    <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>栏目</span>
                                </h2>                                    
                            </div> 
                            <div class="mis-ztree">
                                <ul id="misZtree" class="ztree"></ul>
                            </div>                    
             			</div>
             			<div class="mis-column-innerR">
                            <div class="mis-mod-tt">
                                <h2 class="tt t1">
                                    <span>${channel.name!}管理</span>
                                </h2>                        		                             
                            </div>
                            
                            <div class="mis-content">
		                        <!-- start mis-photo-box -->
		                        <div class="mis-photo-box">
		                            <div>
		                                <div class="mis-pInfo-inner" style="padding-left:0px;">
		                                    <div class="opt">
		                                        <button type="button" onClick="openCreateBanners();" class="mis-btn mis-main-btn" id="photoUploadBtn"><i class="mis-upload-ico"></i>上传相片</button>
		                                   		<span class="u-tips">学院风光图片</span>
		                                   		<span class="u-tips" style="float:right;">直接拖动照片可调整顺序</span>
		                                    </div>
		                                     
		                                </div>
		                            </div>
		                            <ul class="mis-photo-lst photo-detail pImg-List" id="mis-photo-lst" data-alter="false">
								  	<@bannersDirective relationId="${channel.id}">
										<#if bannerList??>
								  		<#list bannerList as banner>
		                                <li id="${banner.id}">
		                                    <div class="block">
		                                        <label>
		                                            <strong>
		                                                <i class="mis-ico-check"></i>
		                                                <input type="chckbox">
		                                            </strong>
		                                            <a href="javascript:void(0);" class="pImg">
		                                                <span class="bImg"><div class="jqthumb" style="width: 190px; height: 178px; opacity: 1;"><div style="width: 100%; height: 100%; background-image: url(${FileUtils.getFileUrl(banner.imageUrl!"")}); background-size: cover; background-position: 50% 50%; background-repeat: no-repeat;"></div></div><div class="jqthumb" style="width: 190px; height: 178px; opacity: 1;"><div style="width: 100%; height: 100%; background-image: url(${FileUtils.getFileUrl(banner.imageUrl!"")}); background-size: cover; background-position: 50% 50%; background-repeat: no-repeat;"></div></div><img src="${FileUtils.getFileUrl(banner.imageUrl!"")}" alt="" style="display: none;"></span>
		                                                <span class="pBtn">
		                                                    <span class="txt">${banner.title!}</span>
		                                                </span>
		                                            </a>
		                                        </label>
		                                        <div class="pOpt">
		                                            <a href="javascript:void(0);" class="drop"><i class="ico-drop"></i></a>
		                                            <div class="opa-lst" style="display: none;">
		                                                <a href="javascript:void(0);" bid="${banner.id}" class="lst btn-edit"><i class="mis-alter-ico"></i>编辑</a>
		                                                <a href="javascript:void(0);" bid="${banner.id}" class="lst btn-del"><i class="mis-delete-ico"></i>删除</a>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </li> 
		                                </#list>
										</#if>
								  		</@bannersDirective>                              
		                            </ul>
		                            <div id="myCoursePage" class="mis-laypage"></div>
		                       </div>
                        	   <!-- end mis-photo-box -->                  
                    		</div>
                    		<hr>
                    		<div class="mis-content" id="richText">
                    			<@channelContentDirective channelId="${channel.id}" createIfNotExists="true">   					               
				                    <div class="mis-table-layout">
				                    	<#import "../../common/include/form.ftl" as cf>
										<@cf.form id="editChannelContentForm" action="${ctx}/cms_channels/channel_contents/${channel.id}" method="put">
				                        <ul class="mis-ipt-lst">			                            
				                            <li class="item w1">
				                                <div class="mis-ipt-row">
				                                    <div class="tl">
				                                        <span>学院介绍：</span>
				                                    </div>
				                                    <div class="tc">
				                                        <div class="mis-ipt-mod">
															<textarea id="editor"  style="width:800px;height:400px;"  name="content">${channelContent.content!}</textarea>
				                                        </div>
				                                    </div>
				                                </div>
				                            </li>
				                            
				                        </ul>
				                        </@cf.form>                     
				                    </div>
			                   </@channelContentDirective> 
			                    <#import "../../common/js/ueditor.ftl" as ue> 
					            <@ue.ueditor editorIds=["editor"]>
					            </@ue.ueditor>
                    		</div>
                    		
                      </div>
           </div>
      </div>
      <form id="orderBannerForm" method="put" action="${ctx}/cms_banners/batch_update">
      </form>
      <#import "../channel/tree_click.ftl" as tc> 
	  <@tc.channelTreeClick channelTreeId="misZtree" selectedId="${channel.id}">
			<script>
				function onClick(event, treeId, treeNode, clickFlag) {
					window.location.href="${ctx}/cms_channels/edit_contents/"+treeNode.id;
				}	
			</script>
	 </@tc.channelTreeClick>
	<script>
		function openCreateBanners(){
			mylayerFn.open({
	            id: 12345,
	            type: 2,
	            title: '添加图片',
	            content: '${ctx}/cms_banners/batch_create/${channel.id}',
				area: [700,500],
	            zIndex: 19999	            
	        });
		}
		
		
		//编辑相册弹框
    $(".btn-edit").on("click",function(){
    	var id=$(this).attr("bid");
        mylayerFn.open({
            id: 12345,
            type: 2,
            title: '编辑图片信息',
            content: "${ctx}/cms_banners/"+id+"/edit",
            area: [600,400],
            //offset: [100,200],
            zIndex: 19999,
        });
    });

    //删除相册弹框
    $(".btn-del").on("click",function(){
    	var id=$(this).attr("bid");
        mylayerFn.confirm({
            title: '提示',
            icon: 0,
            content: "此相片删除后不可恢复，您确定要删除么？",
            //offset: [100,200],
            zIndex: 9999,
            yesFn: function(){
            	$.ajaxDelete("${ctx}/cms_banners/"+id,"",function(data){
            		if(data.responseCode=='00'){
            			mylayerFn.msg('删除成功！',{icon: 1},function(){
            				window.location.reload();
            			});
            		}
            	});
                
            }
        });
    });
    
        //相册下拉框
    photoDrop();
    function photoDrop(){
        var photo_lst = $("#mis-photo-lst"),
            btn = photo_lst.find(".drop"),
            lst = photo_lst.find(".opa-lst"),
            block = photo_lst.find(".block");
        btn.on("click",function(){
            var _ts = $(this),
                lst = _ts.next(".opa-lst");
            lst.show();
        });
        lst.on("mouseover",function(){
            $(this).show();
        });
        lst.on("mouseout",function(){
            $(this).hide();
        });
        block.on("mouseleave",function(){
            $(this).find(".opa-lst").hide();
        });
    }
     var startIndex = 0;
     $("#mis-photo-lst").sortable({               //启用拖拽功能
           placeholder: "ui-state-highlight1",
           opacity: 0.6,
           containment: "body",
           start:function(event,ui){
           		startIndex = $("#mis-photo-lst").children('li').index(ui.item);
           },
           stop:function(event,ui){    
           		var endIndex = $("#mis-photo-lst").children('li').index(ui.item);
           		$("#orderBannerForm").empty();
           		var j=0;
           		if(endIndex>startIndex){
           			for(i=startIndex;i<=endIndex;i++){
           				var $obj = $("#mis-photo-lst").children('li').eq(i);
           				$("#orderBannerForm").append("<input type='hidden' name='banners["+j+"].id' value='"+$obj.attr("id")+"'/>");
           				$("#orderBannerForm").append("<input type='hidden' name='banners["+j+"].orderNo' value='"+i+"'/>");
           				j++;
           			}        			
           		}else if(endIndex<startIndex){
           			for(i=endIndex;i<=startIndex;i++){
           				var $obj = $("#mis-photo-lst li").children('li').eq(i);
           				$("#orderBannerForm").append("<input type='hidden' name='banners['"+j+"'].id' value='"+$obj.attr("id")+"'/>");
           				$("#orderBannerForm").append("<input type='hidden' name='banners['"+j+"'].orderNo' value='"+i+"'/>");
           				j++;
           			}           			
           		}else{
           			return;
           		}
           		var data = $.ajaxSubmit("orderBannerForm");
           		var json = $.parseJSON(data);
				if (json.responseCode != '00') {
					mylayerFn.msg('移动失败！',{icon: 0, time: 2000});
				}
           	}
      }).disableSelection();
	</script>
</@lo.layout>