/**
 * 
 */
package com.haoyu.sip.core.jdbc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.utils.PropertiesLoader;

/**
 * @author lianghuahuang
 *
 */
public class MybatisDao extends SqlSessionDaoSupport {
	
	protected SqlSessionFactory sqlSessionFactory;
	
	protected String getDbUuid(){
		return PropertiesLoader.get("db.uuid");
	}
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		    if (this.getSqlSession()==null) {
		    	this.setSqlSessionTemplate(new SqlSessionTemplate(sqlSessionFactory));
		    }
   }

	protected String namespace = StringUtils.replace(this.getClass().getName(),
			"Dao", "Mapper");
	
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> T selectOne(String statement) {
		return this.getSqlSession().selectOne(namespace+"."+statement);
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> T selectOne(String statement, Object parameter) {
		return this.getSqlSession().selectOne(namespace+"."+statement, parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public <T> T selectByPrimaryKey(Object parameter) {
		return this.getSqlSession().selectOne(namespace+".selectByPrimaryKey", parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return this.getSqlSession().selectMap(namespace+"."+statement, mapKey);
	}

	/**
	 * {@inheritDoc}
	 */
	public <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey) {
		return this.getSqlSession().selectMap(namespace+"."+statement, parameter,
				mapKey);
	}

	/**
	 * {@inheritDoc}
	 */
	public <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey,  PageBounds pageBounds) {
		if(pageBounds==null){
			return this.selectMap(statement, parameter, mapKey);
		}
		return this.getSqlSession().selectMap(namespace+"."+statement, parameter,
				mapKey, pageBounds);
	}

	/**
	 * {@inheritDoc}
	 */
	public <E> List<E> selectList(String statement) {
		return this.getSqlSession(). selectList(namespace+"."+statement);
	}

	/**
	 * {@inheritDoc}
	 */
	public <E> List<E> selectList(String statement, Object parameter) {
		return this.getSqlSession(). selectList(namespace+"."+statement, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	public <E> List<E> selectList(String statement, Object parameter,
			PageBounds pageBounds) {
		if(pageBounds==null){
			return this.selectList(statement, parameter);
		}
		return this.getSqlSession().selectList(namespace+"."+statement, parameter,
				pageBounds);
	}

	/**
	 * {@inheritDoc}
	 */
	public void select(String statement, ResultHandler handler) {
		this.getSqlSession().select(namespace+"."+statement, handler);
	}

	/**
	 * {@inheritDoc}
	 */
	public void select(String statement, Object parameter, ResultHandler handler) {
		this.getSqlSession().select(namespace+"."+statement, parameter, handler);
	}

	/**
	 * {@inheritDoc}
	 */
	public void select(String statement, Object parameter, PageBounds pageBounds,
			ResultHandler handler) {
		if(pageBounds==null){
			this.select(statement, parameter, handler);
		}else{
			this.getSqlSession().select(namespace+"."+statement, parameter, pageBounds, handler);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int insert(String statement) {
		return this.getSqlSession().insert(namespace+"."+statement);
	}
	
	public int insert(Object parameter){
		return this.getSqlSession().insert(namespace+".insert", parameter); 
	}

	/**
	 * {@inheritDoc}
	 */
	public int insert(String statement, Object parameter) {
		return this.getSqlSession().insert(namespace+"."+statement, parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(String statement) {
		return this.getSqlSession().update(namespace+"."+statement);
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(String statement, Object parameter) {
		return this.getSqlSession().update(namespace+"."+statement, parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int update(Object parameter) {
		return this.getSqlSession().update(namespace+".updateByPrimaryKey", parameter);
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(String statement) {
		return this.getSqlSession().delete(namespace+"."+statement);
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(String statement, Object parameter) {
		return this.getSqlSession().delete(namespace+"."+statement, parameter);
	}
	
	public int deleteByPhysics(Object parameter){
		return this.getSqlSession().delete(namespace+".deleteByPhysics", parameter);
	}
	
	public int deleteByLogic(Object parameter){
		return this.getSqlSession().update(namespace+".deleteByLogic",parameter);
	}
}
