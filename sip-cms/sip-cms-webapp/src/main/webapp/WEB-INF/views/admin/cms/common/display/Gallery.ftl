<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js","${ctx}/js/jquery-ui.min.js","${ctx}/js/jqthumb.min.js","${ctx}/js/photoAlbum.js"]/>
<#import "../../../common/include/layout.ftl" as lo> 
<@lo.layout jsArray=jsArray>
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
                                <button type="button" onclick="openCreateGallery()" class="mis-btn mis-main-btn f-rt creat-new"><i class="mis-add-ico"></i>新建${channel.name!}</button>                                
                            </div>
                            
                            <div class="mis-content">
			                        <!-- start mis-photo-box -->
			                        <div class="mis-photo-box">
			                            <ul class="mis-photo-lst" id="mis-photo-lst">
			                            	<@photoGalleriesDirective relationId="${channel.id}">
			                            	<#if photoGalleryList??>
			                            	<#list photoGalleryList as photoGallery>
				                                <li id="${photoGallery.id}">
				                                    <div class="block">
				                                        <a href="${ctx}/gallery/photoGalleries/${photoGallery.id}/galleryPhotos?returnUri=cms_channels/edit_contents/${channel.id}" class="pImg">
				                                            <span class="bImg"><img src="${FileUtils.getFileUrl(photoGallery.frontCover!'')}" alt=""></span>
				                                            <span class="pBtn">
				                                                <span class="txt">${photoGallery.name}</span>
				                                                <span class="ico">${photoGallery.photoNumber}</span>
				                                            </span>
				                                        </a>
				                                        <div class="pOpt">
				                                            <a href="javascript:void(0);" class="drop"><i class="ico-drop"></i></a>
				                                            <div class="opa-lst">
				                                                <a href="javascript:void(0);" class="lst btn-edit" bid="${photoGallery.id}"><i class="mis-alter-ico"></i>编辑</a>
				                                                <a href="javascript:void(0);" class="lst btn-del" bid="${photoGallery.id}"><i class="mis-delete-ico"></i>删除</a>
				                                            </div>
				                                        </div>
				                                    </div>
				                                </li>
			                                </#list>
			                                </#if>
			                                </@photoGalleriesDirective>			                                
			                            </ul>
			                            </div>
                        	   <!-- end mis-photo-box -->                  
                    		</div>
                      </div>
           </div>
      </div>
      <#import "../../channel/tree_click.ftl" as tc> 
	  <@tc.channelTreeClick channelTreeId="misZtree" selectedId="${channel.id}">
			<script>
				function onClick(event, treeId, treeNode, clickFlag) {
					window.location.href="${ctx}/cms_channels/edit_contents/"+treeNode.id;
				}	
			</script>
	 </@tc.channelTreeClick>
	<script>
		function openCreateGallery(){
			mylayerFn.open({
	            id: 12345,
	            type: 2,
	            title: '添加${channel.name!}',
	            content: '${ctx}/gallery/photoGalleries/create?relationId=${channel.id}',
				area: [600,400],
	            zIndex: 19999	            
	        });
		}
		
		
		//编辑相册弹框
    $(".btn-edit").on("click",function(){
    	var id=$(this).attr("bid");
        mylayerFn.open({
            id: 12345,
            type: 2,
            title: '编辑${channel.name!}',
            content: "${ctx}/gallery/photoGalleries/"+id+"/edit",
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
            content: "删除后不可恢复，您确定要删除么？",
            //offset: [100,200],
            zIndex: 9999,
            yesFn: function(){
            	$.ajaxDelete("${ctx}/gallery/photoGalleries/"+id,"",function(data){
            		if(data.responseCode=='00'){
            			mylayerFn.msg('删除成功！',{icon: 1});
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