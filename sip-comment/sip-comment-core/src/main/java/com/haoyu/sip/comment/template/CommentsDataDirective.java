package com.haoyu.sip.comment.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.attitude.service.IAttitudeService;
import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.service.ICommentService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Collections3;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class CommentsDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ICommentService commentService;
	@Resource
	private IAttitudeService attitudeService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String,Object> paramerts = getSelectParam(params);
		
		List<Comment> comments = Lists.newArrayList();
		
		if (paramerts.containsKey("isInCludeChildren") && "N".equals(paramerts.get("isInCludeChildren").toString())) {
			comments = commentService.list(paramerts, pageBounds);
			
			if (pageBounds != null && pageBounds.isContainsTotalCount()) {
				PageList pageList = (PageList)comments;
				env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
			
		}else {
			String relationId = paramerts.get("relationId").toString();
			String relationType = paramerts.get("relationType").toString();
			Relation relation = new Relation();
			relation.setId(relationId);
			relation.setType(relationType);
			comments = buildTree(commentService.findCommentByRelation(relation, null));
		}
		env.setVariable("comments" , new DefaultObjectWrapper().wrap(comments));
	
		if(CollectionUtils.isNotEmpty(comments)){
			Map<String, Object> param = Maps.newHashMap();
			param.put("relationIds",Collections3.extractToList(comments, "id"));
			param.put("attitude","support");
			Map<String,AttitudeStat> attitudeStatMapForSupport = attitudeService.getAttitudeStatByParam(param);
			env.setVariable("attitudeStatMapForSupport", new DefaultObjectWrapper().wrap(attitudeStatMapForSupport));
			
			if (paramerts.containsKey("attitudeRelationType") && StringUtils.isNotEmpty(paramerts.get("attitudeRelationType").toString())) {				
				Map<String,AttitudeUser> attitudeUsers =  attitudeService.getAttitudesByRelationIdsAndRelationType(Collections3.extractToList(comments, "id"),paramerts.get("attitudeRelationType").toString(),ThreadContext.getUser().getId());
				env.setVariable("attitudeUsers" , new DefaultObjectWrapper().wrap(attitudeUsers));
			}
			
			param = Maps.newHashMap();
			param.put("commentIds",Collections3.extractToList(comments, "id"));
			Map<String, Integer> commentChildNumMap = commentService.getChildNum(param);
			env.setVariable("commentChildNumMap", new DefaultObjectWrapper().wrap(commentChildNumMap));
		}
		
		body.render(env.getOut());
	}
	
	private List<Comment> buildTree(List<Comment> comments){
		List<Comment> root = Lists.newArrayList();
		if(Collections3.isNotEmpty(comments)){
			Map<String,Comment> commentMap = Collections3.extractToMap(comments, "id", null);
			for(Comment comment:comments){
				if(StringUtils.isNotEmpty(comment.getParentId())){
					comment.setParentComment(commentMap.get(comment.getParentId()));
				}
				if(StringUtils.isNotEmpty(comment.getMainId())){
					Comment parent = commentMap.get(comment.getMainId());
					parent.getChildComments().add(comment);
				}else{
					root.add(commentMap.get(comment.getId()));
				}
			}
		}
		return root;
	}
}
