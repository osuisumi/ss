/**
 * 
 */
package com.haoyu.sip.cms.resource.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;







import java.util.Objects;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.sip.cms.resource.dao.IResourceDao;
import com.haoyu.sip.cms.resource.entity.Resource;
import com.haoyu.sip.cms.resource.service.IResourceService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class ResourceServiceImpl implements IResourceService {
	@javax.annotation.Resource 
	private IResourceDao resourceDao;
	@javax.annotation.Resource 
	private IFileService fileService;

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.service.IResourceService#createResource(com.haoyu.sip.cms.resource.entity.Resource)
	 */
	@Override
	@CacheEvict(value="resources",allEntries=true)
	public Response createResource(Resource resource) {
		if(resource==null){
			return Response.failInstance().responseMsg("createResource fail!resource is null!");
		}
		if(StringUtils.isEmpty(resource.getId())){
			resource.setId(Identities.uuid2());
		}
		if(resource.getFileInfo()!=null){
			if(StringUtils.isNotEmpty(resource.getType())&&resource.getType().equalsIgnoreCase("pdf")){
				FileInfo frontCoverFile = createPdfFrontCover(resource.getFileInfo());
				fileService.createFile(frontCoverFile, resource.getId(), "resource-pdf-frontCover");
				//上传
				fileService.createFile(resource.getFileInfo(), resource.getId(), "resource-pdf");
				resource.setFrontCover(frontCoverFile.getUrl());
				resource.setUrl(resource.getFileInfo().getUrl());
			}else{
				//上传
				fileService.createFile(resource.getFileInfo(), resource.getId(), "resource-"+Objects.toString(resource.getType()).toLowerCase());
				resource.setUrl(resource.getFileInfo().getUrl());
			}
		}
		int count = resourceDao.insertResource(resource);
		return count>0?Response.successInstance().responseData(resource):Response.failInstance().responseMsg("createResource fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.service.IResourceService#updateResource(com.haoyu.sip.cms.resource.entity.Resource)
	 */
	@Override
	public Response updateResource(Resource resource) {
		if(resource==null||StringUtils.isEmpty(resource.getId())){
			return Response.failInstance().responseMsg("updateResource is fail!resource is null or resource's id is null");
		}
		if(resource.getFileInfo()!=null){
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(new Relation(resource.getId(),"resource-"+resource.getType().toLowerCase()));
			if(oldFileInfos!=null&&!oldFileInfos.isEmpty()){
				fileService.updateFile(resource.getFileInfo(), oldFileInfos.get(0), resource.getId(), "resource-"+resource.getType().toLowerCase());
			}else{
				if(StringUtils.isNotEmpty(resource.getType())&&resource.getType().equalsIgnoreCase("pdf")){
					FileInfo frontCoverFile = createPdfFrontCover(resource.getFileInfo());
					fileService.createFile(frontCoverFile, resource.getId(), "resource-pdf-frontCover");
					//上传
					fileService.createFile(resource.getFileInfo(), resource.getId(), "resource-pdf");
					resource.setFrontCover(frontCoverFile.getUrl());
					resource.setUrl(resource.getFileInfo().getUrl());
				}else{
					//上传
					fileService.createFile(resource.getFileInfo(), resource.getId(), "resource-"+Objects.toString(resource.getType()).toLowerCase());
					resource.setUrl(resource.getFileInfo().getUrl());
				}
			}
			
		}
		int count = resourceDao.updateResource(resource);
		return count>0?Response.successInstance().responseData(resource):Response.failInstance().responseMsg("updateResource fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.service.IResourceService#deleteResource(com.haoyu.sip.cms.resource.entity.Resource)
	 */
	@Override
	@CacheEvict(value="resources",allEntries=true)
	public Response deleteResource(Resource resource) {
		if(resource==null||StringUtils.isEmpty(resource.getId())){
			return Response.failInstance().responseMsg("deleteResource is fail!resource is null or resource's id is null");
		}
		int count = resourceDao.deleteResourceByLogic(resource);
		//fileService.deleteFileFromServer(fileInfo);
		//TODO 需要调用删除文件的方法
		return count>0?Response.successInstance():Response.failInstance().responseMsg("deleteResource fail!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.service.IResourceService#findResourceById(java.lang.String)
	 */
	@Override
	public Resource findResourceById(String id) {
		if(StringUtils.isEmpty(id)){
			return null;
		}
		return resourceDao.selectResourceById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.sip.cms.resource.service.IResourceService#findResources(com.haoyu.sip.cms.resource.entity.Resource)
	 */
	@Override
	public List<Resource> findResources(Resource resource) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(resource!=null){
			if(StringUtils.isNotEmpty(resource.getName())){
				parameter.put("name", resource.getName());
			}
			if(StringUtils.isNotEmpty(resource.getType())){
				parameter.put("type", resource.getType());
			}
		}
		return resourceDao.findAll(parameter);
	}

	@Override
	public List<Resource> findResources(Resource resource, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(resource!=null){
			if(StringUtils.isNotEmpty(resource.getName())){
				parameter.put("name", resource.getName());
			}
			if(StringUtils.isNotEmpty(resource.getType())){
				parameter.put("type", resource.getType());
			}
		}
		return resourceDao.findAll(parameter,pageBounds);
	}

	@Override
	@Cacheable(key="'res_'+#parameter['cacheKey']",value="resources")
	public List<Resource> findResources(Map<String, Object> parameter,
			PageBounds pageBounds) {
		return resourceDao.findAll(parameter,pageBounds);
	}
	
	private FileInfo createPdfFrontCover(FileInfo pdfFile) {
		String pdfFilename = PropertiesLoader.get("file.temp.dir") + pdfFile.getUrl();

		PDDocument document = null;
		try {
			document = PDDocument.load(new File(pdfFilename));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			BufferedImage image = pdfRenderer.renderImageWithDPI(0, 30,
					ImageType.RGB);
			String fileUrl = Identities.uuid2()+".jpg";
			boolean isWrite = ImageIO.write(image, "jpg", new File(PropertiesLoader.get("file.temp.dir")+fileUrl));
			if(isWrite){
				FileInfo fi = new FileInfo();
				fi.setFileName("frontCover.jpg");
				fi.setUrl(fileUrl);				
				return fi;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(document!=null){
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
