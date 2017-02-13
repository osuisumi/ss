/**
 * 
 */
package com.haoyu.sip.auth.entity;

import java.util.ArrayList;
import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class AuthResource extends BaseEntity {
	
	private static final long serialVersionUID = -6756250599078095280L;

	private String id;
	
	private String name;
	
	private String code;
	
	private String relationId;
	
	private AuthResource parent;
	
	private List<AuthResource> children = new ArrayList<AuthResource>();
	
	private List<AuthPermission> permissions;
	
	public AuthResource(){}
	
	public AuthResource(String id){
		this.id = id;
	}

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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public AuthResource getParent() {
		return parent;
	}

	public void setParent(AuthResource parent) {
		this.parent = parent;
	}

	public List<AuthResource> getChildren() {
		return children;
	}

	public void setChildren(List<AuthResource> children) {
		this.children = children;
	}

	public List<AuthPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<AuthPermission> permissions) {
		this.permissions = permissions;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	public void addChild(AuthResource resource){
		if(!children.contains(resource)){
			this.children.add(resource);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		AuthResource other = (AuthResource) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//获取以当前对象为跟节点的树的所有id
	public List<String> getTreeAllResourceId(List<String> result){
		result.add(this.getId());
		if(children!=null && children.size()>0){
			for(AuthResource r:children){
				r.getTreeAllResourceId(result);
			}
		}
		return result;
	}
	
	//获取以当前对象为跟节点的树的所有permission的id
	public List<String> getTreeAllPermissionId(List<String> result){
		if(permissions!=null &&permissions.size()>0){
			for(AuthPermission p:permissions){
				result.add(p.getId());
			}
		}
		if(children!=null && children.size()>0){
			for(AuthResource r:children){
				r.getTreeAllPermissionId(result);
			}
		}
		return result;
	}
	
	//根据id获取字树
	public AuthResource getChildTreeById(String childId){
		if(this.getId().equals(childId)){
			return this;
		}else{
			AuthResource result = null;
			if(children!=null &&children.size()>0){
				for(AuthResource r:children){
					AuthResource child = r.getChildTreeById(childId);
					if(child!=null){
						result = child;
						break;
					}
				}
			}
			return result;
		}
	}
}
