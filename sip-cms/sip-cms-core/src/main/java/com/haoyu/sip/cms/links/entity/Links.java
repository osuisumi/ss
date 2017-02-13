/**
 * 
 */
package com.haoyu.sip.cms.links.entity;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author Administrator
 *
 */
public class Links extends BaseEntity {
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 英文链接标题
	 */
	private String titleEn;
	
	/**
	 * 概要，简介
	 */
	private String summary;
	
	/**
	 * 图标路径 
	 */
	private String icon;
	
	/**
	 * 链接类型:友情链接、便民服务、综合管理、重点项目
	 */
	private LinksType linksType;
	
	/**
	 * 排序号
	 */
	private int orderNo;
	
	/**
	 * 链接地址
	 */
	private String url;

	public Links() {
		super();
	}

	public Links(String id) {
		super();
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public LinksType getLinksType() {
		return linksType;
	}

	public void setLinksType(LinksType linksType) {
		this.linksType = linksType;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
