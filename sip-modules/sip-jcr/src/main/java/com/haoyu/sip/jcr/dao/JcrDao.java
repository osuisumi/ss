/**
 * 
 */
package com.haoyu.sip.jcr.dao;

import java.util.Collection;
import java.util.List;


import org.apache.jackrabbit.ocm.exception.RepositoryException;
import org.apache.jackrabbit.ocm.query.Query;
import org.springframework.extensions.jcr.JcrTemplate;
import org.springframework.extensions.jcr.jackrabbit.ocm.JcrMappingTemplate;
import org.springframework.extensions.jcr.support.JcrDaoSupport;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.haoyu.sip.jcr.entity.JcrEntity;
import com.haoyu.sip.jcr.entity.Pagination;

/**
 * @author Administrator
 *
 */
public class JcrDao<T extends JcrEntity> extends JcrDaoSupport {

	public   JcrMappingTemplate getJcrMappingTemplate() {
		JcrTemplate jcrTemplate = this.getTemplate();
		if (jcrTemplate instanceof JcrMappingTemplate) {
			return (JcrMappingTemplate) jcrTemplate;
		}
		return null;
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T selectById(String id) {
		try {
			return (T) this.getJcrMappingTemplate().getObjectByUuid(id);
		} catch (RepositoryException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据路径查询
	 * @param path
	 * @return
	 */
	public T selectByPath(String path) {
		try {
			return (T) this.getJcrMappingTemplate().getObject(path);
		} catch (RepositoryException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 构造查询条件进行查询，建议子类根据对象定义，调用该方法实现按字段查询
	 * @param query
	 * @return
	 */
	public List<T> selectByQuery(Query query) {
		if(query!=null){
			Collection<T> collections = this.getJcrMappingTemplate()
					.getObjects(query);
			try {
				return Lists.newArrayList(collections.iterator());
			} catch (RepositoryException e) {
			//	e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<T> selectByQuery(Query query,long limit,long offset) {
		if(query!=null){
			Collection<T> collections = this.getJcrMappingTemplate()
					.getObjects(query,limit,offset);
			try {
				return Lists.newArrayList(collections.iterator());
			} catch (RepositoryException e) {
			//	e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<T> selectByQuery(Query query,Pagination pagination){
		if(pagination!=null){
			Collection<T> collection = this.getJcrMappingTemplate()
			.getObjects(query);
			int limit = pagination.getLimit();
			int page = pagination.getPage();
			List<T> list = Lists.newArrayList();
			if(collection!=null&&collection.size()>0){				
				for(int i=((page-1)*limit);i<page*limit;i++){
					list.add(Iterables.get(collection, i));
				}
				pagination.setTotalCount(collection.size());
			}
			return list;
		}
		return this.selectByQuery(query);
		
	}
	
	/**
	 * 新增
	 * @param entity
	 * @return 主键id
	 */
	public String insert(T entity) {
		this.getJcrMappingTemplate().insert(entity);
		this.getJcrMappingTemplate().save();
		return entity.getId();
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public String deleteById(String id) {
		this.getJcrMappingTemplate().removeById(id);
		this.getJcrMappingTemplate().save();
		return id;
	}
	
	/**
	 * 根据查询条件删除
	 * @param query
	 */
	protected void deleteByQuery(Query query) {
		this.getJcrMappingTemplate().removeByQuery(query);
		this.getJcrMappingTemplate().save();
	}
	
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	public T update(T t) {
		this.getJcrMappingTemplate().update(t);
		this.getJcrMappingTemplate().save();
		return t;
	}

}
