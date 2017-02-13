package com.haoyu.sip.tag.entity;

import com.haoyu.sip.core.entity.BaseEntity;


public class Tag extends BaseEntity{
	
	private static final long serialVersionUID = -5493686983254726649L;
	
	private String id;

    private String name;

    private String summary;

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
        this.name = name == null ? null : name.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Tag) {
			obj = (Tag)obj;
			if (((Tag) obj).getName().equals(this.name)) {
				return true;
			}
		}
    	return false;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
}