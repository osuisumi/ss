/**
 * 
 */
package com.haoyu.sip.auth.entity;

import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class AuthPermission extends BaseEntity {

	private static final long serialVersionUID = -4251141476444201108L;
	
	private String id;

	private String name;
	
	private AuthResource resource;
	
	private String action;
	
	private String actionURI;
	
	/**
	 *  策略模式：
	 *  Private: Only Owner (usually creator) have permissions to a resource.
	 *  Public: Other user have permission (read,write) to a resource
	 *  Public_Read:所有用户可读
	 *  Public_Write:所有用户可写
	 *  Owner_Full_Control：资源创建者拥有全部权限
	 */
	private String policy;
	
	private List<AuthRole> roles;
	
	private List<AuthMenu> menus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AuthResource getResource() {
		return resource;
	}

	public void setResource(AuthResource resource) {
		this.resource = resource;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}

	public List<AuthMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<AuthMenu> menus) {
		this.menus = menus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthPermission other = (AuthPermission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
