/**
 * 
 */
package com.haoyu.sip.cms.siteinfo.entity;

import java.util.Map;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.theme.entity.VisualTheme;

/**
 * @author Administrator
 *
 */
public class SiteInfo extends BaseEntity {
	
	private String id;
	
	private String relationId;
	
	/**
	 * 网站名称
	 */
	private String name;
	
	/**
	 * 网站描述：在页面显示时添加到meta description
	 */
	private String description;
	
	/**
	 * 域名
	 */
	private String domain;
	
	
	private VisualTheme adminTheme=new VisualTheme();
	
	private VisualTheme frontEndTheme=new VisualTheme();
	
	private String mappingFolder;
	
	/**
	 * 备案号
	 */
	private String icp;
	
	/**
	 * 版权
	 */
	private String copyRight;
	
	/**
	 * footerhtml内容
	 */
	private String footerHtml;
	
	/**
	 * 微信二维码
	 */
	private String weixinQrcode;
	
	/**
	 * 用于上传二维码，与数据库无关
	 */
	private FileInfo fileInfo;
	
	private Map<String,String> siteInfoAttributes;

	public SiteInfo() {
		super();
	}

	public SiteInfo(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getCopyRight() {
		return copyRight;
	}

	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}

	public String getFooterHtml() {
		return footerHtml;
	}

	public void setFooterHtml(String footerHtml) {
		this.footerHtml = footerHtml;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getWeixinQrcode() {
		return weixinQrcode;
	}

	public void setWeixinQrcode(String weixinQrcode) {
		this.weixinQrcode = weixinQrcode;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public Map<String, String> getSiteInfoAttributes() {
		return siteInfoAttributes;
	}

	public void setSiteInfoAttributes(Map<String, String> siteInfoAttributes) {
		this.siteInfoAttributes = siteInfoAttributes;
	}

	

	/**
	 * @return the adminTheme
	 */
	public VisualTheme getAdminTheme() {
		return adminTheme;
	}

	/**
	 * @param adminTheme the adminTheme to set
	 */
	public void setAdminTheme(VisualTheme adminTheme) {
		this.adminTheme = adminTheme;
	}

	/**
	 * @return the frontEndTheme
	 */
	public VisualTheme getFrontEndTheme() {
		return frontEndTheme;
	}

	/**
	 * @param frontEndTheme the frontEndTheme to set
	 */
	public void setFrontEndTheme(VisualTheme frontEndTheme) {
		this.frontEndTheme = frontEndTheme;
	}

	/**
	 * @return the mappingFolder
	 */
	public String getMappingFolder() {
		return mappingFolder;
	}

	/**
	 * @param mappingFolder the mappingFolder to set
	 */
	public void setMappingFolder(String mappingFolder) {
		this.mappingFolder = mappingFolder;
	}

	
	
}
