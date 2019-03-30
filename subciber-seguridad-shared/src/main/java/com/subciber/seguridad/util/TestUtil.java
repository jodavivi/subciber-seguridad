/**
 * 
 */
package com.subciber.seguridad.util;

import java.time.LocalDateTime;
import java.util.Random;

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
	     
	    String originalString = "APP00013";
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
	    
	    
	    LocalDateTime a = LocalDateTime.parse("2019-02-15T13:02:13.298");
	    LocalDateTime b =  LocalDateTime.parse("2019-04-15T12:44:15.477");
	    System.out.println(a.compareTo(b));
	    System.out.println(b.compareTo(a));
	    System.out.println(a.compareTo(a));
	    
	    String saludo ="000001";
	    System.out.println(saludo.substring(3));
	    
	    String estado = "23505";
	    
	    if(estado.equals("23505")) {
	    	System.out.println("Int¿resaaa");
	    }
	    
	    
	    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        
        System.out.println(saltStr);
        
	    
	}

}
