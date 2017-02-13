/**
 * 
 */
package com.haoyu.sip.cms.channel.entity;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * @author lianghuahuang
 *
 */
public class ChannelContent extends BaseEntity {
	
	private String channelId;
	
	private String content;
	
	private String mobileContent;
	
	/**
	 * @return the mobileContent
	 */
	public String getMobileContent() {
		return mobileContent;
	}

	/**
	 * @param mobileContent the mobileContent to set
	 */
	public void setMobileContent(String mobileContent) {
		this.mobileContent = mobileContent;
	}

	public ChannelContent(){}
	
	public ChannelContent(String channelId){
		this.channelId = channelId;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
