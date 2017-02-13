/**
 * 
 */
package com.haoyu.sip.barcode.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * @author Administrator
 *
 */
public class QRCodeDecoder {
		
	/**
	 * 解码二维码图片内容
	 * @param filePath 二维码图片路径
	 * @return decoded results from qrcode.
	 */
	 public static String decode(String filePath){
	     // check the required parameters
	     if (filePath == null || Paths.get(filePath).toFile()==null)
	         throw new IllegalArgumentException("File not found, or invalid file name.");
	    
	     BufferedImage image;
	     try {
	         image =  ImageIO.read(Paths.get(filePath).toFile());
	     } catch (IOException ioe) {
	         throw new RuntimeException(ioe.getMessage());
	     }
	     return decode(image);

	} 
	
	/**
	  * 
	  * 解码二维码图片内容
	  * @param file 二维码图片.
	  * @return 二维码内容.
	  */
	 public static String decode(File file){
	     // check the required parameters
	     if (file == null || file.getName().trim().isEmpty())
	         throw new IllegalArgumentException("File not found, or invalid file name.");
	    
	     BufferedImage image;
	     try {
	         image =  ImageIO.read(file);
	     } catch (IOException ioe) {
	         throw new RuntimeException(ioe.getMessage());
	     }
	     return decode(image);

	} 
	 
	 /**
	  * 解码二维码图片内容
	  * @param stream 图片输入流 
	  * @return 二维码内容
	  */
	 public static String decode(InputStream stream){
	     // check the required parameters
	     if (stream == null)
	         throw new IllegalArgumentException("stream is NULL");
	     
	     BufferedImage image;
	     try {
	         image =  ImageIO.read(stream);
	     } catch (IOException ioe) {
	         throw new RuntimeException(ioe.getMessage());
	     }
	     return decode(image);

	} 
	 
	 /**
	  * 解码二维码图片内容
	  * @param url 二维码图片地址
	  * @return 二维码内容
	  */
	 public static String decode(URL url){
	     // check the required parameters
	     if (url == null)
	         throw new IllegalArgumentException("url is NULL");
	     BufferedImage image;
	     try {
	         image =  ImageIO.read(url);
	     } catch (IOException ioe) {
	         throw new RuntimeException(ioe.getMessage());
	     }
	     return decode(image);

	}

	/**
	 * @param image
	 * @return
	 */
	private static String decode(BufferedImage image) {
		if (image == null)
	         throw new IllegalArgumentException("Could not decode image.");
	     
	     LuminanceSource source = new BufferedImageLuminanceSource(image);  
         BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  

         try {
        	Map<DecodeHintType, Object> hints  =new  Hashtable<DecodeHintType,Object>();
        	hints.put(DecodeHintType.CHARACTER_SET, "GBK");  
        	Result result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();  
		} catch (NotFoundException e) {
			e.printStackTrace();
		}  
        return null;
	} 
}
