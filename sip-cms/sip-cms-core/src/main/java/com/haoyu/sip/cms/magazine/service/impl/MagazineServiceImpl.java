/**
 * 
 */
package com.haoyu.sip.cms.magazine.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.haoyu.sip.cms.magazine.entity.Magazine;
import com.haoyu.sip.cms.magazine.service.IMagazineService;
import com.haoyu.sip.cms.resource.service.IResourceService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.gallery.entity.Photo;
import com.haoyu.sip.gallery.service.IPhotoGalleryService;
import com.haoyu.sip.utils.Identities;


/**
 * @author lianghuahuang
 *
 */
@Service
public class MagazineServiceImpl implements IMagazineService {
	@Resource
	private IPhotoGalleryService photoGalleryService;
	
	@Resource
	private IFileService fileService;
	
	@Resource
	private IResourceService resourceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.haoyu.sip.cms.magazine.service.IMagazineService#createMagazine(com
	 * .haoyu.sip.cms.magazine.entity.Magazine)
	 */
	@Override
	public Response createMagazine(Magazine magazine) {
		if (magazine.getFileInfo() != null) {
			FileInfo fi = magazine.getFileInfo();
			if(magazine.getType().equalsIgnoreCase("photoGallery")){
				return createPhotoGallery(magazine, fi);
			}else if(magazine.getType().equalsIgnoreCase("pdf")){	
				magazine.setId(Identities.uuid2());
				FileInfo frontCoverFile = createPdfFrontCover(fi);
				fileService.createFile(frontCoverFile, magazine.getId(), "magazine-pdf-frontCover");
				//上传
				fileService.createFile(magazine.getFileInfo(), magazine.getId(), "magazine-pdf");
				com.haoyu.sip.cms.resource.entity.Resource resource = new com.haoyu.sip.cms.resource.entity.Resource();
				resource.setId(magazine.getId());
				resource.setName(magazine.getName());
				resource.setSummary(magazine.getDescription());
				resource.setType(magazine.getType());
				resource.setFrontCover(frontCoverFile.getUrl());
				resource.setUrl(magazine.getFileInfo().getUrl());
				return resourceService.createResource(resource);
			}
		}
		return Response.failInstance().responseMsg(
				"create Magazine fail! upload file is null!");
	}

	/**
	 * @param magazine
	 * @param fi
	 */
	private Response createPhotoGallery(Magazine magazine, FileInfo fi) {
		if (fi.getFileName().lastIndexOf(".zip") <= 0) {
			return Response.failInstance().responseMsg(
					"上传的文件格式不正确，必须为zip压缩格式");
		}

		try {
			// Initiate ZipFile object with the path/name of the zip file.
			ZipFile zipFile = new ZipFile(
					PropertiesLoader.get("file.temp.dir") + fi.getUrl());
			// Get the list of file headers from the zip file
			List<FileHeader> fileHeaderList = zipFile.getFileHeaders();
			// Loop through the file headers
			String imagesRegex = "images/((^?[0-9])+(\\.(?i)(jpg|png|gif|bmp)))";
			String frontCoverRegex = "((?i)frontCover)+(\\.(?i)(jpg|png|gif|bmp))";
			List<Photo> photos = Lists.newArrayList();
			long currentMillis = System.currentTimeMillis();
			String extractFileDir = zipFile.getFile().getParent()
					+ File.separator + currentMillis;
			String extractRelativeFileDir = StringUtils
					.substringBeforeLast(fi.getUrl(), "/");
			for (int i = 0; i < fileHeaderList.size(); i++) {
				FileHeader fileHeader = fileHeaderList.get(i);
				String fileName = fileHeader.getFileName();
				if (fileName.matches(imagesRegex)) {
					Photo photo = new Photo();
					photo.setName(Paths.get(fileName).getFileName()
							.toString());
					photo.setOrderNo(Integer.parseInt(StringUtils
							.substringBetween(fileName, "images/", ".")));
					FileInfo photoFileInfo = new FileInfo();
					photoFileInfo.setUrl(extractRelativeFileDir
							+ File.separator + currentMillis
							+ File.separator + fileName);
					photoFileInfo.setFileName(photo.getName());
					photo.setFileInfo(photoFileInfo);
					photos.add(photo);
				} else if (fileName.matches(frontCoverRegex)) {
					FileInfo frontCoverFileInfo = new FileInfo();
					frontCoverFileInfo.setUrl(extractRelativeFileDir
							+ File.separator + currentMillis
							+ File.separator + fileName);
					frontCoverFileInfo.setFileName(Paths.get(fileName).getFileName()
							.toString());
					magazine.getPhotoGallery().setFileInfo(
							frontCoverFileInfo);
				} else if (!fileName.equals("images/")) {
					return Response
							.failInstance()
							.responseMsg(
									"上传的压缩文件图片路径或格式不正确，根目录必须存放前缀为frontCover后缀名为jpg|png|gif|bmp的图片，且根目录下的images文件夹下存放的文件格式必须为图片");
				}

			}
			if (magazine.getPhotoGallery() != null && photos.size() > 0) {
				zipFile.extractAll(extractFileDir);
				magazine.getPhotoGallery().setPhotoNumber(photos.size());
				photoGalleryService.createPhotoGallery(magazine
						.getPhotoGallery());
				photoGalleryService.addPhotosToGallery(magazine
						.getPhotoGallery().getId(), photos);

				return Response.successInstance();
			}

		} catch (ZipException e) {
			e.printStackTrace();
		}
		return Response.failInstance();
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
