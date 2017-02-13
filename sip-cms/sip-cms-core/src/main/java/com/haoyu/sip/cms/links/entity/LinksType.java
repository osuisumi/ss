/**
 * 
 */
package com.haoyu.sip.cms.links.entity;

import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author Administrator
 * 链接类型
 */
public class LinksType extends BaseEntity {
	
	private String id;
	
	private String relationId;
	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 排序号
	 */
	private int orderNo;
	
	private List<Links> links;

	public LinksType() {
		super();
	}

	public LinksType(String id) {
		super();
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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

	public List<Links> getLinks() {
		return links;
	}

	public void setLinks(List<Links> links) {
		this.links = links;
	}
}
