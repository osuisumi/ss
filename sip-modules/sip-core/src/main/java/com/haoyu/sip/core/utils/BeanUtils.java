package com.haoyu.sip.core.utils;

import java.util.List;

import com.google.common.collect.Lists;

public class BeanUtils extends org.springframework.beans.BeanUtils{
	
	public static List getCopyList(List sourceList, Class targetClass) {
		List result = Lists.newArrayList();
		for (Object object : sourceList) {
			Object targetEntity = null;
			try {
				targetEntity = targetClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			copyProperties(object, targetEntity);
			result.add(targetEntity);
		}
		return result;
	}
	
	public static Object getCopyEntity(Object source, Class targetClass) {
		Object targetEntity = null;
		try {
			targetEntity = targetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		copyProperties(source, targetEntity);
		return targetEntity;
	}

}
