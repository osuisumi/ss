/**
 * 
 */
package com.haoyu.sip.auth.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.auth.dao.IAuthMenuDao;
import com.haoyu.sip.auth.entity.AuthMenu;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class AuthMenuDao extends MybatisDao implements IAuthMenuDao {
	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuDao#selectMenuById(java.lang.String)
	 */
	@Override
	public AuthMenu selectMenuById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuDao#selectMenuForList(com.haoyu.sip.auth.entity.Menu, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<AuthMenu> selectMenuForList(Map<String,Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByMenu", parameter, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuDao#selectMenuForMap(com.haoyu.sip.auth.entity.Menu, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public Map<String, AuthMenu> selectMenuForMap(Map<String,Object> parameter, PageBounds pageBounds) {
		return super.selectMap("selectByMenu", parameter, "id", pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuDao#insertMenu(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public int insertMenu(AuthMenu menu) {
		menu.setDefaultValue();
		return super.insert(menu);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuDao#updateMenu(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public int updateMenu(AuthMenu menu) {
		menu.setUpdateTime(System.currentTimeMillis());
		return super.update(menu);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuDao#deleteMenuByLogic(com.haoyu.sip.auth.entity.Menu)
	 */
	@Override
	public int deleteMenuByLogic(AuthMenu menu) {
		menu.setUpdateTime(System.currentTimeMillis());
		return super.deleteByLogic(menu);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.auth.dao.IMenuDao#deleteMenuByPhysics(java.lang.String)
	 */
	@Override
	public int deleteMenuByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public int batchDeleteByIds(List<String> ids) {
		return super.delete("batchDeleteByIds", ids);
	}


}
