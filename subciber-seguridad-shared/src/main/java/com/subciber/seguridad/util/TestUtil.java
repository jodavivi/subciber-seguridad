/**
 * 
 */
package com.subciber.seguridad.util;

import java.time.LocalDateTime;

/**
 * @author josep
 *
 */
public class TestUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String secretKey = "Peru12345678.";
	     
	    String originalString = "APP0001";
	    String encryptedString = EncriptacionAES.encrypt(originalString, secretKey) ;
	    String decryptedString = EncriptacionAES.decrypt(encryptedString, secretKey) ;
	     
	    System.out.println(originalString);
	    System.out.println(encryptedString);
	    System.out.println(decryptedString);
	    
	    LocalDateTime actual = LocalDateTime.now();
	    LocalDateTime actualSuma = actual.plusMinutes(15);
	    System.out.println(actual.toString());
	    System.out.println(actualSuma.toString());
	    String token = EncriptacionAES.encrypt(actual.toString()+";" +actualSuma.toString(), secretKey);
	    System.out.println(token);
	    System.out.println(EncriptacionAES.decrypt(token, secretKey));

	    String cadena  = "DavidVillanueva,";
	    StringBuilder sb = new StringBuilder(cadena);
	    sb.setLength(cadena.length() - 1);
	    System.out.println(sb.toString());
	    
	}

}
