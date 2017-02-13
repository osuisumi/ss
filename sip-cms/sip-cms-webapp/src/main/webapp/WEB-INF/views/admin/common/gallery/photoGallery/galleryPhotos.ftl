<#assign jsArray=["${ctx}/js/jquery-ui.min.js","${ctx}/js/jqthumb.min.js","${ctx}/js/photoAlbum.js"]/>
<#import "../../include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>
		<div class="mis-index-wrap">
                <div class="mis-mod">
                    <div class="mis-mod-tt" id="defaultTit">
                        <h2 class="tt t1"><span>图片列表</span></h2>
                        <#if returnUri??>
                        <a href="${ctx}/${returnUri}" class="mis-btn mis-main-btn f-rt"><i class="mis-back-ico"></i>返回相册</a>
                        </#if>
                    </div>
                    <div class="mis-mod-tt" id="photoOpa">
                        <div class="all-chk">
                            <label class="chk-lbl" id="checkAll">
                                <strong>
                                    <i class="mis-ico-check"></i>
                                    <input type="checkbox">
                                </strong>
                                <span class="txt">本页全选</span>
                            </label>
                            <span>已选择1张</span>
                        </div>
                        <button type="button" class="mis-btn mis-main-btn f-rt" id="finishMag"><i class="mis-save-ico"></i>完成管理</button>
                        <button type="button" class="mis-btn mis-inverse-btn f-rt" id="deletePhotos"><i class="mis-delete-ico"></i>删除</button>
                        <button type="button" class="mis-btn mis-inverse-btn f-rt" id="movePhoto"><i class="mis-move-ico"></i>移动到相册</button>
                    </div>
                    <div class="mis-content">
                        <!-- start mis-photo-box -->
                        <div class="mis-photo-box">
                            <div class="mis-photo-info">
                                <div class="mis-pInfo-inner">
                                    <span class="bImg">
                                        <img src="${FileUtils.getFileUrl(photoGallery.frontCover!'')}" alt="">
                                    </span>
                                    <div class="img-name">
                                        <b>${photoGallery.name!}</b>
                                        <span>${photoGallery.photoNumber!"0"}张</span>
                                        <i class="ico-drop1"></i>
                                    </div>
                                    <div class="opt">
                                        <button type="button" class="mis-btn mis-main-btn" onclick="openAddPhotos()" id="photoUploadBtn"><i class="mis-upload-ico"></i>上传照片</button>
                                      <!--  <button type="button" class="mis-btn mis-main-btn" id="mgBtn"><i class="mis-setting-ico"></i>批量管理</button>-->
                                    </div>
                                </div>
                                <div class="mis-photo-edit">
                                    <b class="photo-name">${photoGallery.name!}</b>
                                    <a href="javascript:void(0);" class="u-edit" id="editPhotoInfo">编辑相册信息</a>
                                    <span class="u-tips">直接拖动照片可调整顺序</span>
                                </div>
                            </div>
                            <ul class="mis-photo-lst photo-detail pImg-List" id="mis-photo-lst" data-alter="false">
                            	<@photosDirective photoGalleryId="${photoGallery.id}">
                            	<#if photoList??>
                            	<#list photoList as photo>
                                <li id="${photo.id}">
                                    <div class="block">
                                        <label>
                                            <strong>
                                                <i class="mis-ico-check"></i>
                                                <input type="chckbox">
                                            </strong>
                                            <a href="javascript:void(0);" class="pImg">
                                                <span class="bImg"><div class="jqthumb" style="width: 190px; height: 178px; opacity: 1;"><div style="width: 100%; height: 100%; background-image: url(${FileUtils.getFileUrl(photo.fileInfo.url!'')}); background-repeat: no-repeat; background-position: 50% 50%; background-size: cover;"></div></div><img style="display: none;" src="${FileUtils.getFileUrl(photo.fileInfo.url!'')}" alt=""></span>
                                              <#--  <#if photo.name??>
                                                <span class="pBtn">
                                                    <span class="txt">${photo.name!}</span>
                                                </span>
                                                </#if>-->
                                            </a>
                                        </label>
                                        <div class="pOpt">
                                            <a href="javascript:void(0);" class="drop"><i class="ico-drop"></i></a>
                                            <div style="display: none;" class="opa-lst">
                                                <a href="javascript:void(0);" class="lst btn-edit" bid="${photo.id}"><i class="mis-alter-ico"></i>编辑</a>
                                                <a href="javascript:void(0);" class="lst btn-del" bid="${photo.id}"><i class="mis-delete-ico"></i>删除</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                </#list>
                                </#if>
                                </@photosDirective>
                            </ul>
                            <div id="myCoursePage" class="mis-laypage"></div>
                        </div>
                        <!-- end mis-photo-box -->                  
                    </div>  
                </div><!--end module layout -->
            </div>
            <form id="orderPhotosForm" method="put" action="${ctx}/gallery/photoGalleries/${photoGallery.id}/updatePhotos">
      		</form>
            <script>
		function openAddPhotos(){
			mylayerFn.open({
	            id: 12345,
	            type: 2,
	            title: '添加图片',
	            content: '${ctx}/gallery/photoGalleries/${photoGallery.id}/addPhotos',
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
            content: "${ctx}/gallery/photos/"+id+"/edit",
            area: [500,300],
            //offset: [100,200],
            zIndex: 19999,
        });
    });

    //删除弹框
    $(".btn-del").on("click",function(){
    	var id=$(this).attr("bid");
        mylayerFn.confirm({
            title: '提示',
            icon: 0,
            content: "此相片删除后不可恢复，您确定要删除么？",
            //offset: [100,200],
            zIndex: 9999,
            yesFn: function(){
            	$.ajaxDelete("${ctx}/gallery/photoGalleries/${photoGallery.id}/removePhotos","photos[0].id="+id,function(data){
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
           		$("#orderPhotosForm").empty();
           		var j=0;
           		if(endIndex>startIndex){
           			for(i=startIndex;i<=endIndex;i++){
           				var $obj = $("#mis-photo-lst").children('li').eq(i);
           				$("#orderPhotosForm").append("<input type='hidden' name='photos["+j+"].id' value='"+$obj.attr("id")+"'/>");
           				$("#orderPhotosForm").append("<input type='hidden' name='photos["+j+"].orderNo' value='"+i+"'/>");
           				j++;
           			}        			
           		}else if(endIndex<startIndex){
           			for(i=endIndex;i<=startIndex;i++){
           				var $obj = $("#mis-photo-lst li").children('li').eq(i);
           				$("#orderPhotosForm").append("<input type='hidden' name='photos['"+j+"'].id' value='"+$obj.attr("id")+"'/>");
           				$("#orderPhotosForm").append("<input type='hidden' name='photos['"+j+"'].orderNo' value='"+i+"'/>");
           				j++;
           			}           			
           		}else{
           			return;
           		}
           		var data = $.ajaxSubmit("orderPhotosForm");
           		var json = $.parseJSON(data);
				if (json.responseCode != '00') {
					mylayerFn.msg('移动失败！',{icon: 0, time: 2000});
				}
           	}
      }).disableSelection();
	</script>
</@lo.layout>