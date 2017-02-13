/**
 * 
 */
package com.haoyu.sip.attitude.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;

/**
 * @author lianghuahuang
 *
 */
@RestController
@RequestMapping("/attitudes")
public class AttitudeController extends AbstractBaseController {
		
	@Autowired
	private IAttitudeService attitudeService;
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createAttitudeUser(AttitudeUser attitudeUser){
		if(ThreadContext.getUser()!=null){
			attitudeUser.setCreator(ThreadContext.getUser());
		}
		return attitudeService.createAttitudeUser(attitudeUser);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public AttitudeUser findAttitudeUserById(@PathVariable String id){
		return attitudeService.findAttitudeUserById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<AttitudeUser> getAttitudeUsers(AttitudeUser attitudeUser){
		return attitudeService.findAttitudeUserByAttitudeAndRelation(attitudeUser.getAttitude(), attitudeUser.getRelation(), this.getPageBounds(10, true));
	}
	
	@RequestMapping(value="/attitudeStats",method=RequestMethod.GET)
	public Map<String,AttitudeStat> getAttitudeStat(AttitudeUser attitudeUser){
		return attitudeService.getAttitudeStatByAttitudeAndRelation(attitudeUser.getAttitude(), attitudeUser.getRelation());
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteAttitudeUser(String relationId,String attitude){
		return attitudeService.deleteAttitideUser(attitude, relationId, ThreadContext.getUser().getId());
	}
	
	@RequestMapping(value="attitudeUserMap",method=RequestMethod.GET)
	public Map<String,AttitudeUser> getAttitudeUserMap(String relationIds,String relationType){
		return attitudeService.getAttitudesByRelationIdsAndRelationType(Arrays.asList(relationIds.split(",")), relationType, ThreadContext.getUser().getId());
	}
	
}
