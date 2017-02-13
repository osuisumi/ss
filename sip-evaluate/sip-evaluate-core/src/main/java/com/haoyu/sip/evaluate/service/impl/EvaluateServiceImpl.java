package com.haoyu.sip.evaluate.service.impl;

import java.util.Collections;
import java.util.Comparator;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.evaluate.dao.IEvaluateDao;
import com.haoyu.sip.evaluate.entity.Evaluate;
import com.haoyu.sip.evaluate.entity.EvaluateItem;
import com.haoyu.sip.evaluate.service.IEvaluateService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class EvaluateServiceImpl implements IEvaluateService{
	
	@Resource
	private IEvaluateDao evaluateDao;

	@Override
	public Response createEvaluate(Evaluate evaluate) {
		if (StringUtils.isEmpty(evaluate.getId())) {
			evaluate.setId(Identities.uuid2());
		}
		evaluate.setDefaultValue();
		int count = evaluateDao.insert(evaluate);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Evaluate getEvaluate(String id) {
		Evaluate evaluate = evaluateDao.selectByPrimaryKey(id);
		if (evaluate != null && Collections3.isNotEmpty(evaluate.getEvaluateItems())) {
			Collections.sort(evaluate.getEvaluateItems(), new Comparator<EvaluateItem>() {
				@Override
				public int compare(EvaluateItem o1, EvaluateItem o2) {
					if (o1.getSortNo() == null && o2.getSortNo() == null || o1.getSortNo().compareTo(o2.getSortNo()) == 0) {
						return (int) (o1.getCreateTime() - o2.getCreateTime());
					}else{
						if (o1.getSortNo() == null) {
							return -1;
						}else if(o2.getSortNo() == null){
							return 1;
						}else{
							return o1.getSortNo().compareTo(o2.getSortNo());
						}
					}
				}
			});
		}
		return evaluate;
	}

}
