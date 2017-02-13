/**
 * 
 */
package com.haoyu.sip.cms.template.entity;

import java.io.Serializable;

/**
 * @author lianghuahuang
 *
 */
public class TemplateFile implements Serializable{
	
	private int id;
	
	private int pid;
	
	private String name;
	
	private String fileContent;
	
	private String path;
	
	private long lastModified;
	
	public TemplateFile(){}
	
	public TemplateFile(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}
	
	public TemplateFile(String name, long lastModified) {
		super();
		this.name = name;
		this.lastModified = lastModified;
	}

	public TemplateFile(String name, String path, long lastModified) {
		super();
		this.name = name;
		this.path = path;
		this.lastModified = lastModified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
