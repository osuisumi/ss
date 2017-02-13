package com.haoyu.sip.comment.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Collections3;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class CommentsDirective extends AbstractTemplateDirectiveModel {

	@Resource
	private ICommentService commentService;
	@Resource
	private IAttitudeService attitudeService;
	@Resource
	private RedisTemplate redisTemplate;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = getPageBounds(params);
		Map<String, Object> parameter = getSelectParam(params);

		String relationId = (String) parameter.get("relationId");
		String key = null;
		if (pageBounds == null) {
			key = PropertiesLoader.get("redis.app.key") + ":list_comment:" + relationId + ":" + DigestUtils.md5Hex(parameter.toString());
		}else {
			key = PropertiesLoader.get("redis.app.key") + ":list_comment:" + relationId + ":" + DigestUtils.md5Hex(parameter.toString() + pageBounds.toString());
		}
		ValueOperations<String, List<Comment>> valueOper = redisTemplate.opsForValue();
		List<Comment> comments = Lists.newArrayList();
		if (redisTemplate.hasKey(key)) {
			comments = valueOper.get(key);
			env.setVariable("comments", new DefaultObjectWrapper().wrap(comments));
		} else {
			comments = commentService.list(parameter, pageBounds);
			if (parameter.containsKey("getChild")) {
				boolean getChild = (boolean) parameter.get("getChild");
				if (getChild) {
					comments = this.buildTree(comments);
				}
			}
			valueOper.set(key, comments);
			redisTemplate.expire(key, 2, TimeUnit.HOURS);
			env.setVariable("comments", new DefaultObjectWrapper().wrap(comments));
		}
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList) comments;
			env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		if (CollectionUtils.isNotEmpty(comments)) {
			if (parameter.containsKey("getAttitude") && ThreadContext.getUser() != null && StringUtils.isNotEmpty(ThreadContext.getUser().getId())) {
				boolean getAttitude = (boolean) parameter.get("getAttitude");
				if (getAttitude) {
					//获取点赞数
					Map<String, Object> param = Maps.newHashMap();
					param.put("relationIds", Collections3.extractToList(comments, "id"));
					param.put("attitude", "support");
					Map<String, AttitudeStat> attitudeNumMap = attitudeService.getAttitudeStatByParam(param);
					env.setVariable("attitudeNumMap", new DefaultObjectWrapper().wrap(attitudeNumMap));
					//获取点赞状态
					String attitudeRelationType = "comment";
					if (parameter.containsKey("attitudeRelationType") && StringUtils.isNotEmpty(parameter.get("attitudeRelationType").toString())){
						attitudeRelationType = parameter.get("attitudeRelationType").toString();
					}
					Map<String,AttitudeUser> attitudeUserMap = attitudeService.getAttitudesByRelationIdsAndRelationType(Collections3.extractToList(comments, "id"), attitudeRelationType, ThreadContext.getUser().getId());
					env.setVariable("attitudeUserMap", new DefaultObjectWrapper().wrap(attitudeUserMap));
				}
			}
		}
		body.render(env.getOut());
	}

	private List<Comment> buildTree(List<Comment> comments) {
		List<Comment> root = Lists.newArrayList();
		if (Collections3.isNotEmpty(comments)) {
			Map<String, Comment> commentMap = Collections3.extractToMap(comments, "id", null);
			for (Comment comment : comments) {
				if (StringUtils.isNotEmpty(comment.getParentId())) {
					comment.setParentComment(commentMap.get(comment.getParentId()));
				}
				if (StringUtils.isNotEmpty(comment.getMainId()) && (!"null".equals(comment.getMainId()))) {
					Comment parent = commentMap.get(comment.getMainId());
					parent.getChildComments().add(comment);
				} else {
					root.add(commentMap.get(comment.getId()));
				}
			}
		}
		return root;
	}
}
