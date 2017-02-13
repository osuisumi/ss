package com.haoyu.sip.file.dao;

import java.util.Map;

public interface IFileResourceDao {

	int updateChildFileParent(String id);

	int getCount(Map<String, Object> param);

}
