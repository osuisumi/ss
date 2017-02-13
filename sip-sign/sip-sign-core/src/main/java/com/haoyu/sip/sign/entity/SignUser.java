package com.haoyu.sip.sign.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;

public class SignUser extends BaseEntity{
	
	private static final long serialVersionUID = -5820102083514964901L;

	private String id;
	
	private SignStat signStat;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SignStat getSignStat() {
		return signStat;
	}

	public void setSignStat(SignStat signStat) {
		this.signStat = signStat;
	}

	public static String getId(String signStatId, String today) {
		return DigestUtils.md5Hex(signStatId+today);
	}

}
