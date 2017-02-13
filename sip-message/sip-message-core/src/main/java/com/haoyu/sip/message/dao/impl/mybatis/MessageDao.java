package com.haoyu.sip.message.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.message.dao.IMessageDao;
import com.haoyu.sip.message.entity.Message;

@Repository
public class MessageDao extends MybatisDao implements IMessageDao {

	@Override
	public Message selectMessageById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertMessage(Message message) {
		message.setDefaultValue();
		return super.insert(message);
	}
	
	@Override
	public int batchInsert(Map<String, Object> parameter) {
		return super.insert("batchInsert", parameter);
	}

	@Override
	public int updateMessage(Message message) {
		return super.update(message);
	}

	@Override
	public int deleteMessageByLogic(Message message) {
		return super.deleteByLogic(message);
	}

	@Override
	public int deleteMessageByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<Message> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}


}
