package com.haoyu.sip.cms.article.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.haoyu.sip.cms.channel.entity.Channel;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileInfo;

/**
 * 实体类   文章
 * @author huangqunyan
 */
public class Article extends BaseEntity {

	private static final long serialVersionUID = 2741302028571651549L;
	/** id*/
	private String id;
	
	/** 展现形式：富文本(RichText)、文本域(TextArea)、文本(Input)*/
	private String displayType; 
	/** 标题 */
	private String title;
	/** 副标题 */
	private String subtitle;
	/** 内容 */
	private String content;
	/** 状态：发布，草稿，待审核等 */
	private String state;
	/** 浏览次数 */
	private int browseNumber;
	/** 链接 */
	private String url;
	/**撰稿人*/
	private String author;
	/**供稿人*/
	private String contributedby;
	/**来源*/
	private String origin;
	/** 发布人 */
	private User publisher;
	/** 发布时间 */
	private long publishTime;
	/** 所在栏目 */
	private Channel channel=new Channel();
	/** 置顶  Y 是    N 否*/
	private String isTop;
	
	private String frontCoverImage;
	
	private FileInfo frontCoverImageFile;
	
	private List<Channel> channels;
	
	/** 附件 */
	private List<FileInfo> fileInfos = Lists.newArrayList();


	public Article() {                
		super();
	}
	public Article(String id) {
		super();
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplayType() {
		return displayType;
	}
	
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if(StringUtils.isNotEmpty(state)&&!state.equals("published")){
			this.state = "draft";
		}else{
			this.state = state;
		}
	}

	public Channel getChannel() {
		if(channel==null&&channels!=null&&!channels.isEmpty()){
			return channels.get(0);
		}
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public long getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		if(isTop==null||!isTop.equals("Y")){
			this.isTop = "N";
		}else{
			this.isTop = isTop;
		}
	}
	
	
	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}
	public String getFrontCoverImage() {
		return frontCoverImage;
	}
	public void setFrontCoverImage(String frontCoverImage) {
		this.frontCoverImage = frontCoverImage;
	}
	public FileInfo getFrontCoverImageFile() {
		return frontCoverImageFile;
	}
	public void setFrontCoverImageFile(FileInfo frontCoverImageFile) {
		this.frontCoverImageFile = frontCoverImageFile;
	}
	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
/*		if(StringUtils.isEmpty(state)){
			state="published";
		}*/
		if(StringUtils.isEmpty(isTop)){
			isTop="N";
		}
		//如果状态设置为发布，则设置发布时间
		publishTime = System.currentTimeMillis();
		User user = ThreadContext.getUser();
		if(user!=null&&user.getId()!=null){
				publisher=ThreadContext.getUser();
		}
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContributedby() {
		return contributedby;
	}
	public void setContributedby(String contributedby) {
		this.contributedby = contributedby;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public List<Channel> getChannels() {
		return channels;
	}
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}


}
