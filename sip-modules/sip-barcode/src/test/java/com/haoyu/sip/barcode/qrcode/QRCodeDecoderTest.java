/**
 * 
 */
package com.haoyu.sip.barcode.qrcode;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;

import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class QRCodeDecoderTest {

	/**
	 * Test method for {@link com.haoyu.sip.barcode.qrcode.QRCodeDecoder#decode(java.io.File)}.
	 */
	@Test
	public void testDecodeFile() {
		String content = QRCodeDecoder.decode(new File("c:/qrcode.png"));
		System.out.println(content);
	}

	/**
	 * Test method for {@link com.haoyu.sip.barcode.qrcode.QRCodeDecoder#decode(java.io.InputStream)}.
	 */
	//@Test
	public void testDecodeInputStream() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDecodeURL(){
		try {
			String content = QRCodeDecoder.decode(new java.net.URL("http://d9.yihaodianimg.com/N01/M01/0D/7A/CgQCr1KW1juAFH9YAAAatCrcuRI48200.png"));
		//System.out.println(content);
				assertEquals("http://m.yihaodian.com/mw/openapp/9/0/0?tracker_u=10305758297",content);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
