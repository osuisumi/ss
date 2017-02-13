<#assign jsArray=["${ctx}/js/ztree/js/jquery.ztree.core.min.js","${ctx}/js/ztree/js/jquery-ui.min.js","${ctx}/js/ztree/js/jqthumb.min.js","${ctx}/js/ztree/js/photoAlbum.js","${ctx}/js/webuploader/webuploader.min.js"]/>
<#assign cssArray=["${ctx}/js/webuploader/webuploader.css"]/>
<#import "../../../common/include/layout.ftl" as lo> 
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
                                <button type="button" onclick="openCreateResource()" class="mis-btn mis-main-btn f-rt creat-new"><i class="mis-add-ico"></i>新建${channel.name!}</button>                                
                            </div>
                            
                            <div class="mis-content">
			                        <!-- start mis-photo-box -->
			                        <div class="mis-photo-box">
			                            <ul class="mis-photo-lst" id="mis-photo-lst">
			                            	<@resourcesDirective relationId="${channel.id}" page=param_page!"1" size=param_size!"10" >
			                            	<#if resourceList??>
			                            	<#list resourceList as resource>
				                                <li id="${resource.id}">
				                                    <div class="block">
				                                        <a href="###" class="pImg">
				                                            <span class="bImg"><img src="${FileUtils.getFileUrl(resource.frontCover!'')}" alt=""></span>
				                                            <span class="pBtn">
				                                                <span class="txt">${resource.name}</span>
				                                                <span class="ico"></span>
				                                            </span>
				                                        </a>
				                                        <div class="pOpt">
				                                            <a href="javascript:void(0);" class="drop"><i class="ico-drop"></i></a>
				                                            <div class="opa-lst">
				                                                <a href="javascript:void(0);" bid="${resource.id}" class="lst btn-edit"><i class="mis-alter-ico"></i>编辑</a>
				                                                <a href="javascript:void(0);" bid="${resource.id}"class="lst btn-del"><i class="mis-delete-ico"></i>删除</a>
				                                            </div>
				                                        </div>
				                                    </div>
				                                </li>
			                                </#list>
			                                </#if>
			                                </@resourcesDirective>			                                
			                            </ul>
			                            <div id="myCoursePage" class="mis-laypage"></div>
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
			function openCreateResource(){
				mylayerFn.open({
		            id: 12345,
		            type: 2,
		            title: '添加${channel.name!}',
		            content: '${ctx}/cms_resources/create?relation.id=${channel.id}&&type=pdf',
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
	            content: "${ctx}/cms_resources/"+id+"/edit",
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
	            	$.ajaxDelete("${ctx}/cms_resources/"+id,"",function(data){
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