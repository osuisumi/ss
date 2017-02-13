/**
 * 
 */
package com.haoyu.sip.auth.entity;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class AuthMenu extends BaseEntity {
	
	private static final long serialVersionUID = -1111335262083152142L;

	private String id;
	
	private String name;
	
	private AuthPermission permission = new AuthPermission();
	
	private int orderNo;
	
	private String relationId;
	
	private AuthMenu parent;
	
	private List<AuthMenu> children = Lists.newArrayList();
	
	private List<AuthRole> roles;
	// 先序遍历，拼接JSON字符串
	public String toString() {
			String result = "{" + "id : '" + (permission!=null?permission.getId():"") + "'" + ", name : '" + (permission!=null?permission.getName():"") + "'";

			if (children != null && children.size() != 0) {
				result += ", children : " + children.toString();
			} else {
				result += ", leaf : true";
			}

			return result + "}";
	}

	public AuthMenu() {
	}

	public AuthMenu(String id) {
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

	public AuthPermission getPermission() {
		return permission;
	}

	public void setPermission(AuthPermission permission) {
		this.permission = permission;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public AuthMenu getParent() {
		return parent;
	}

	public void setParent(AuthMenu parent) {
		this.parent = parent;
	}

	public List<AuthMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AuthMenu> children) {
		this.children = children;
	}

	public List<AuthRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AuthRole> roles) {
		this.roles = roles;
	}
	
	public void addChild(AuthMenu node) {
		if(!children.contains(node)){
			children.add(node);
		}
	}
	
	public boolean hasChild(){
		if (children != null && !children.isEmpty()){
			return true;
		}
		return false;
	}
	

	public void sortChildren(){
		if (children != null && !children.isEmpty()){
			Collections.sort(children, (first,second)->Integer.compare(first.getOrderNo(), second.getOrderNo()));
		}
		for(AuthMenu menu:children){
			menu.sortChildren();
		}
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
		AuthMenu other = (AuthMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	//获取以当前对象为跟节点的树的所有id
	public List<String> getTreeAllMenuId(List<String> result){
		result.add(this.getId());
		if(children!=null && children.size()>0){
			for(AuthMenu m:children){
				m.getTreeAllMenuId(result);
			}
		}
		return result;
	}
	
	
	public AuthMenu getChildTreeById(String childId){
		if(this.getId().equals(childId)){
			return this;
		}else{
			AuthMenu result = null;
			if(children!=null &&children.size()>0){
				for(AuthMenu r:children){
					AuthMenu child = r.getChildTreeById(childId);
					if(child!=null){
						result = child;
						break;
					}
				}
			}
			return result;
		}
	}
	
	public List<AuthMenu> getLeaf(List<AuthMenu> result){
		if(this.getChildren() == null || this.getChildren().size()<=0){
			result.add(this);
		}else{
			for(AuthMenu child:children){
				child.getLeaf(result);
			}
		}
		return result;
	}
}
