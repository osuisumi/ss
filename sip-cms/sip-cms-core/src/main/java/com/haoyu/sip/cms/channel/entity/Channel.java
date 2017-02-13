/**
 * 
 */
package com.haoyu.sip.cms.channel.entity;


import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;


/**
 * 栏目
 * @author huangqunyan
 * 
 */
public class Channel extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1659701378466383202L;
	private String id;
	/** 名称 */
	private String name;
	
	/** 栏目显示类型：列表(List)、富文本(RichText)、文本域(TextArea)、文本(Input)、混合类型*/
	private String displayType;
	/**
	 * 关联ID
	 */
	private String relationId;
	/** 别名 */
	private String alias;
	/** 描述 */
	private String des;
	/** 链接 */
	private String url;
	/**	上一级栏目id 第一级的设为null*/
	private Channel parent;
	/** 页面排放的顺序  */
	private int orderNo;
	/** 主显示的子栏目，限制只能有一个 */
	private Channel showChild;
	
	private List<Channel> children = Lists.newArrayList();
	
	private String state;
	
	public Channel(){}
	
	public Channel(String id) {
		super();
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<Channel> getChildren() {
		return children;
	}

	public void setChildren(List<Channel> children) {
		this.children = children;
	}

	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Channel getParent() {
		return parent;
	}
	public void setParent(Channel parent) {
		this.parent = parent;
	}
	public Channel getShowChild() {
		return showChild;
	}
	public void setShowChild(Channel showChild) {
		this.showChild = showChild;
	}
	
	public void addChild(Channel node) {
		if(children!=null && !children.contains(node)){
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
		for(Channel catalog:children){
			catalog.sortChildren();
		}
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
		Channel other = (Channel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
