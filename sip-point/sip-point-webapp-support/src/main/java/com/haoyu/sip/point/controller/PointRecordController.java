package com.haoyu.sip.point.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.point.entity.PointRecord;
import com.haoyu.sip.point.service.IPointRecordService;


@RequestMapping("**/point_record")
@Controller
public class PointRecordController extends AbstractBaseController{
	@Resource
	private IPointRecordService pointRecordService;
	
	@RequestMapping(method=RequestMethod.POST)
	public Response save(PointRecord pointRecord){
		return pointRecordService.createPointRecord(pointRecord);
	}
	

}
