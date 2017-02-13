package com.haoyu.sip.auth;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.haoyu.sip.auth.entity.AuthPermission;
import com.haoyu.sip.auth.entity.AuthResource;
import com.haoyu.sip.auth.service.IAuthPermissionService;



public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{
	
	public static final String PREMISSION_STRING="perms[{0}]";
	public static final String REST_STRING="rest[{0}]";
	
	private String filterChainDefinitions;
	
	@Autowired
	private IAuthPermissionService authPermissionService;
	
	private String lastfilterChainDefinitions;
	
	private String resourceRelationId;



	/**
	 * @return the lastfilterChainDefinitions
	 */
	public String getLastfilterChainDefinitions() {
		return lastfilterChainDefinitions;
	}


	/**
	 * @param lastfilterChainDefinitions the lastfilterChainDefinitions to set
	 */
	public void setLastfilterChainDefinitions(String lastfilterChainDefinitions) {
		this.lastfilterChainDefinitions = lastfilterChainDefinitions;
	}


	/**
	 * @param resourceRelationId the resourceRelationId to set
	 */
	public void setResourceRelationId(String resourceRelationId) {
		this.resourceRelationId = resourceRelationId;
	}


	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}
    
    
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		if(authPermissionService!=null){
	    	StringBuffer filter=new StringBuffer();//改正后的url配置
	    	AuthResource authResource = new AuthResource();
	    	if(resourceRelationId!=null&&StringUtils.isNotEmpty(resourceRelationId)){
	    		authResource.setRelationId(resourceRelationId);
	    	}
	    	List<AuthPermission> permissions = authPermissionService.findPermissionByResource(authResource, null);
	    	Map<String,StringBuffer> chainDefinitionsMap = Maps.newLinkedHashMap();
	    	if(permissions!=null&&!permissions.isEmpty()){
		    	for(AuthPermission permission:permissions){
		    		if(!StringUtils.isEmpty(permission.getActionURI())) {
		    			if(!chainDefinitionsMap.containsKey(permission.getActionURI())&&StringUtils.isNotEmpty(permission.getAction())){
		    				chainDefinitionsMap.put(permission.getActionURI(), new StringBuffer("= authc,cu,").append(MessageFormat.format(REST_STRING,Objects.toString(permission.getActionURI()).toLowerCase())) );
		    			}else if(StringUtils.isEmpty(permission.getAction())){
			    			if(chainDefinitionsMap.containsKey(permission.getActionURI())){
			    				chainDefinitionsMap.get(permission.getActionURI()).append(",").append(MessageFormat.format(PREMISSION_STRING,String.join(":",permission.getResource().getCode(),permission.getActionURI())));
			    			}else{
			    				chainDefinitionsMap.put(permission.getActionURI(), new StringBuffer(" = authc,cu,").append(MessageFormat.format(PREMISSION_STRING,String.join(":", permission.getResource().getCode(),permission.getActionURI())) ));
			    			}
		    			}
		            }
		    	}
		    	for(Map.Entry<String,StringBuffer> entry:chainDefinitionsMap.entrySet()){
		    		filter.append(entry.getKey()).append(entry.getValue()).append("\n");
		    	}
	    	}
	    
	    	this.filterChainDefinitions = filterChainDefinitions+filter.toString();
	    	if(StringUtils.isNotEmpty(lastfilterChainDefinitions)){
	    		this.filterChainDefinitions +=lastfilterChainDefinitions;
	    	}
		}
	}

	
    public Section getObject(){
        Ini ini = new Ini();//网上好多都是在这里配置URL的。但是发现是错误的。
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        return section;
    }

    public Class<?> getObjectType() {
        return this.getClass();
    }

    public boolean isSingleton() {
        return false;
    }

}