/**
 * 
 */
package com.haoyu.sip.cms.banner.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * @author Administrator
 *
 */
public class Banner extends BaseEntity {
	
	private String id;
	
	private String relationId;
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 图标路径
	 */
	private String imageUrl;
	
	/**
	 * 如果banner包含链接，可以指定打开新页还是本页打开
	 */
	private String target;
	
	/**
	 * 内容链接地址
	 */
	private String articleLink;
	
	private int orderNo;
	
	/** 附件 */
	private FileInfo fileInfo;

	public Banner() {
		super();
	}

	public Banner(String id) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getArticleLink() {
		return articleLink;
	}

	public void setArticleLink(String articleLink) {
		this.articleLink = articleLink;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	
	
	
}
