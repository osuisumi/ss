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
public class AuthRole extends BaseEntity {
	private String id;
	
	private String code;
	
	private String name;
	
	private String summary;
	
	private String relationId;
	
	private List<AuthUser> authUsers;
	
	private List<AuthPermission> permissions;
	
	private List<AuthMenu> menus;
	
	public AuthRole(){}
	
	public AuthRole(String id){
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public List<AuthUser> getAuthUsers() {
		return authUsers;
	}

	public void setAuthUsers(List<AuthUser> authUsers) {
		this.authUsers = authUsers;
	}

	public List<AuthPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<AuthPermission> permissions) {
		this.permissions = permissions;
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
		AuthRole other = (AuthRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
