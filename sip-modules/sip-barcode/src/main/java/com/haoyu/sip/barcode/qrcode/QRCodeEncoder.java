/**
 * 
 */
package com.haoyu.sip.barcode.qrcode;

import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author Administrator
 *
 */
public class QRCodeEncoder {
	 /** 
     * 编码 
     * @param contents 
     * @param width 
     * @param height 
     * @param imgPath 
     */  
    public static void encode(String contents, int width, int height, String imgPath) {  
        Map<EncodeHintType, Object> hints = getHints();  
        try {  
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,BarcodeFormat.QR_CODE, width, height, hints);  
            MatrixToImageWriter.writeToPath(bitMatrix,  "png", Paths.get(imgPath)); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
    
    /**
     * 编码
     * @param contents
     * @param width
     * @param height
     * @param stream
     */
    public static void encode(String contents, int width, int height,OutputStream stream) { 
    	Map<EncodeHintType, Object> hints = getHints();  
        try {  
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,BarcodeFormat.QR_CODE, width, height, hints);  
            MatrixToImageWriter.writeToStream(bitMatrix,  "png", stream);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }

	/**
	 * @return
	 */
	private static Map<EncodeHintType, Object> getHints() {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType,Object>();
        // 指定纠错等级  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
        // 指定编码格式  
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		return hints;
	}  
 
  
}
