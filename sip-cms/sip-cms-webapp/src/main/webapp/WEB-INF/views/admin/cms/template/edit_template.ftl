   
   					<@channelTemplateDirective type=type id=id>   					               
                    <div class="mis-srh-layout">
                    	<#import "../../common/include/form.ftl" as cf>
                    	<#if templateFile?? && templateFile.fileContent??>
                    		<#assign method="put"/>
                    		<#assign buttons=["<button type='button' onclick='deleteTemplate();'  class='mis-btn mis-default-btn'><i class='mis-delete-ico'></i>删除</button>"]/>
                    	<#else>
                    		<#assign method="post"/>
                    		<#assign buttons=[]/>
                    	</#if>
                    	
						<@cf.form id="editTemplateForm" action="${ctx}/cms_templates/channels/${type}/${id}" method="${method}" buttons=buttons>
                        <ul class="mis-ipt-lst">
                            <li class="item">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>模板路径：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
                                            <input type="text" readonly value="${templateFile.path!}" class="mis-ipt">                                            
                                        </div>
                                    </div>
                                </div>
                            </li>
                            
                            
                            <li class="item w1">
                                <div class="mis-ipt-row">
                                    <div class="tl">
                                        <span>内容：</span>
                                    </div>
                                    <div class="tc">
                                        <div class="mis-ipt-mod">
											<textarea id="editor" class="ace" style="width:800px;height:600px;"  name="fileContent">${templateFile.fileContent!}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        </@cf.form>  
                        <script>
                        	function deleteTemplate(){
                        		$.ajaxDelete($("#editTemplateForm").attr("action"),"",function(response){
                        			if(response.responseCode == '00'){
                        				$("#templateContent").load($("#editTemplateForm").attr("action"))
                        			}else{
                        				
                        			}
                        		})
                        	}
                        </script>                      
                    </div>
                   </@channelTemplateDirective> 
            <#import "../../common/js/ace.ftl" as ace> 
            <@ace.aceditor selectors=[".ace"]>
            </@ace.aceditor>