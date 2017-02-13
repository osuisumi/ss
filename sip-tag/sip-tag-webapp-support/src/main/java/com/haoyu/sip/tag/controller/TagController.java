/**
 * 
 */
package com.haoyu.sip.tag.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.tag.entity.Tag;
import com.haoyu.sip.tag.entity.TagRelation;
import com.haoyu.sip.tag.service.ITagService;

/**
 * @author lianghuahuang
 *
 */
@RestController
@RequestMapping("/tags")
public class TagController extends AbstractTagController {
	@Autowired
	private ITagService tagService;
	
	@RequestMapping(method=RequestMethod.POST)
	public Response createTag(Tag tag){
		return tagService.createTag(tag);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.PUT)
	public Response updateTag(@PathVariable String id,Tag tag){
		tag.setId(id);
		return tagService.updateTag(tag);
	}
	
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	public Response deleteTag(@PathVariable String id){
		Tag tag = new Tag();
		tag.setId(id);
		return tagService.deleteTag(tag);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Tag> findTags(TagRelation tagRelation){
		return tagService.findTagByNameAndRelations(tagRelation.getTag()!=null?tagRelation.getTag().getName():null, tagRelation.getRelation()!=null?Lists.newArrayList(tagRelation.getRelation().getId()):null, this.getPageBounds(10, true));
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public Tag findTagById(@PathVariable String id){
		return tagService.findTagById(id);
	}
	
	@RequestMapping(value="/relations",method=RequestMethod.GET)
	public List<Tag> findTagByRelations(List<String> relations){
		return tagService.findTagByNameAndRelations(null,relations, this.getPageBounds(10, true));
	}
}
