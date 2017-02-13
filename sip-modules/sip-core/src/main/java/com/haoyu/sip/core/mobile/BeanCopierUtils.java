package com.haoyu.sip.core.mobile;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.core.entity.BaseEntity;

public class BeanCopierUtils {
	
	private static Logger logger = LoggerFactory.getLogger(BeanCopierUtils.class);
	
	public static Map<String, BeanCopier> beanCopierMap = Maps.newHashMap();
	
	public static Object getTargetEntity(Object sourceEntity, Class targetClass,Converter extraConver)  {
		Object targetEntity = null;
		try {
			targetEntity = targetClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("create instance error!" + targetClass, e);
		}
		if(targetEntity != null){
			BeanCopier copier = BeanCopier.create(sourceEntity.getClass(), targetClass,true);
			copier.copy(sourceEntity, targetEntity, new DefaultConver(sourceEntity.getClass(),targetClass));
			if(extraConver != null){
				copier.copy(sourceEntity, targetEntity, extraConver);
			}
		}
		return targetEntity;
	}

	public static List getTargetList(List sourceList, Class targetClass,Converter extraConver) {
		List result = Lists.newArrayList();
		for(Object obj:sourceList){
			result.add(getTargetEntity(obj,targetClass,extraConver));
		}
		return result;
	}
	
	public static Map<?, ?> getTargetMap(Map<String, ?> sourceMap, Class targetClass,Converter extraConver) throws InstantiationException, IllegalAccessException{
		Map resultMap = Maps.newHashMap();
		for (String key : sourceMap.keySet()) {
			Object targetObject = getTargetEntity(sourceMap.get(key),targetClass,extraConver);
			resultMap.put(key, targetObject);
		}
		return resultMap;
	}
}

class DefaultConver implements Converter{
	
	private static Logger logger = LoggerFactory.getLogger(DefaultConver.class);
	
	private Class targetClass;
	
	private Class sourceClass;
	
	public DefaultConver(Class sourceClass,Class targetClass){
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}

	@Override
	public Object convert(Object sourcevalue, Class arg1, Object context) {

		if (sourcevalue instanceof com.haoyu.sip.core.entity.User) {
			return sourcevalue;
		}
		if (sourcevalue instanceof com.haoyu.sip.core.entity.Relation) {
			return sourcevalue;
		}
		if(sourcevalue instanceof List){
			String fieldName = getfieldName(context.toString());
			Type sourceType = null;
			Type targetType = null;
			try {
				Field sourceField = sourceClass.getDeclaredField(fieldName);
				sourceField.setAccessible(true);
				ParameterizedType pt = (ParameterizedType) sourceField.getGenericType();
				sourceType = pt.getActualTypeArguments()[0];
				Field targetField = targetClass.getDeclaredField(fieldName);
				targetField.setAccessible(true);
				ParameterizedType ptt = (ParameterizedType) targetField.getGenericType();
				targetType = ptt.getActualTypeArguments()[0];
			} catch (NoSuchFieldException | SecurityException e) {
				logger.warn("conver list property excption" + sourcevalue ,e);
			}
			if(sourceType != null && targetType != null && !sourceType.toString().equals(targetType.toString())){
				Class clazz = (Class) targetType;
				return BeanCopierUtils.getTargetList((List)sourcevalue,clazz , null);
			}
			
		}
		if(sourcevalue instanceof Map){
			String fieldName = getfieldName(context.toString());
			Type sourceType = null;
			Type targetType = null;
			try {
				Field sourceField = sourceClass.getDeclaredField(fieldName);
				sourceField.setAccessible(true);
				ParameterizedType pt = (ParameterizedType) sourceField.getGenericType();
				sourceType = pt.getActualTypeArguments()[pt.getActualTypeArguments().length-1];
				Field targetField = targetClass.getDeclaredField(fieldName);
				targetField.setAccessible(true);
				ParameterizedType ptt = (ParameterizedType) targetField.getGenericType();
				targetType = ptt.getActualTypeArguments()[pt.getActualTypeArguments().length-1];
				
				if(sourceType != null && targetType != null && !sourceType.toString().equals(targetType.toString())){					
					Class clazz = (Class) targetType;
					return BeanCopierUtils.getTargetMap((Map)sourcevalue,clazz , null);
				}
			} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				logger.warn("conver map property excption" + sourcevalue ,e);
			}
		}
		if(sourcevalue instanceof BaseEntity){
			String fieldName = getfieldName(context.toString());
			try {
				Field targetField = targetClass.getDeclaredField(fieldName);
				targetField.setAccessible(true);
				Class clazz = targetField.getType();
				return BeanCopierUtils.getTargetEntity(sourcevalue, clazz, null);
			} catch (NoSuchFieldException | SecurityException e) {
				logger.warn("conver baseEntity property exception" + sourcevalue ,e);
			}
		}
		return sourcevalue;
	}
	
	private String getfieldName(String setterName){
		String result = setterName.substring(3);
		String firstCode = String.valueOf(result.charAt(0));
		result = result.replaceFirst(firstCode, firstCode.toLowerCase());
		return result;
	}
	
}
