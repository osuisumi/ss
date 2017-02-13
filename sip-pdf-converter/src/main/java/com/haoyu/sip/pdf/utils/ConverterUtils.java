package com.haoyu.sip.pdf.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.words.Document;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.pdf.exception.NotSupportWpsException;

public class ConverterUtils {
	
    public static Response convertFromPpt(String pptUrl, String pdfUrl) throws NotSupportWpsException{
		try {
//	        InputStream license = new FileInputStream("/license.xml");
	    	InputStream license = ConverterUtils.class.getClassLoader().getResourceAsStream("license.xml");   // license路径
	    	InputStream slides = new FileInputStream(pptUrl);
	        License aposeLic = new License();
	        aposeLic.setLicense(license);
	    	
	        Presentation pres = new Presentation(slides);
	        File file = new File(pdfUrl);// 输出pdf路径
	        FileOutputStream fileOS = new FileOutputStream(file);
	        pres.save(fileOS, SaveFormat.Pdf);
			return Response.successInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			throw new NotSupportWpsException();
		}
		return null;
    }
    
    public static Response convertFromDoc(String docUrl, String pdfUrl){
    	Document doc;
		try {
			doc = new Document(docUrl);
			doc.save(pdfUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.successInstance();
    }
}
