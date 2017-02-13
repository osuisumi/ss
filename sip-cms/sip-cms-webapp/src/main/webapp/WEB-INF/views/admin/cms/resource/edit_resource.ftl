		<#import "../../common/include/form_layer.ftl" as fl>
		<@fl.form id="updateResourceForm" action="${ctx}/cms_resources/${resource.id}" method="put">
		<input type="hidden" name="relation.id" value="${resource.relation.id!}"/>
		<input type="hidden" name="type" value="${resource.type!}"/>
		<ul class="mis-ipt-lst">
        	<li class="item">
                <div class="mis-ipt-row">
                    <div class="tl">
                        <span>名称：</span>
                    </div>
                    <div class="tc">
                        <div class="mis-ipt-mod">
                            <input type="text" name="name" value="${resource.name!}" class="mis-ipt">
                        </div>
                    </div>
                </div>
            </li>
            <li class="item w1">
				<div class="mis-ipt-row">
					<div class="tl">
						<span>${resource.type!}文件：</span>
					</div>
					<div class="tc"  id="fileDiv">
						<div class="m-pbMod-udload">
							<a href="javascript:void(0);" class="mis-opt-upbtn mis-inverse-btn picker"><i class="mis-upload-ico"></i>上传文件</a>
						</div>
						<#import "../../common/file/upload_file.ftl" as uploadFileList /> 
						<@uploadFileList.uploadFileFtl relationId="${resource.id}" relationType="resource-${resource.type}" fileNumLimit=1 paramType="entity" paramName="fileInfo" />
					</div>
				</div>
			</li>            
 		</ul>
		</@fl.form>