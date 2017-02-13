/**
 * 
 */
package com.haoyu.sip.jcr.entity;

/**
 * @author Administrator
 *
 */
public class Pagination {
	  /**
     * 分页大小
     */
    private int limit=10;
    /**
     * 页数
     */
    private int page=1;
    /**
     * 总记录数
     */
    private int totalCount;
    
    
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
    
    
}
