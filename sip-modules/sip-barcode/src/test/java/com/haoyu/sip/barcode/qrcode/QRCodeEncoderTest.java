/**
 * 
 */
package com.haoyu.sip.barcode.qrcode;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class QRCodeEncoderTest {

	/**
	 * Test method for {@link com.haoyu.sip.barcode.qrcode.QRCodeEncoder#encode(java.lang.String, int, int, java.lang.String)}.
	 */
	@Test
	public void testEncodeStringIntIntString() {
		QRCodeEncoder.encode("https://qr.alipay.com/pmt37yerjo4x9t2239", 200, 200, "c:/qrcode.png");
	}

	/**
	 * Test method for {@link com.haoyu.sip.barcode.qrcode.QRCodeEncoder#encode(java.lang.String, int, int, java.io.OutputStream)}.
	 */
	//@Test
	public void testEncodeStringIntIntOutputStream() {
		fail("Not yet implemented");
	}

}
