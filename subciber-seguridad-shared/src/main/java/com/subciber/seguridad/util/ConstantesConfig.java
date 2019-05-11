/**
 * 
 */
package com.subciber.seguridad.util;

/**
 * @author josep
 *
 */
public class ConstantesConfig {

	public static final Integer aplicacionId 			= 1; 
	public static final String claveEncripacionAES 		= "Peru12345678.";
	public static final Integer timeoutSession 			= 60;
	public static final Integer timeoutRecuperar		= 120;
	
	public static final Integer activo 					= 23;
	public static final Integer desactivo 				= 24;
	public static final Integer eliminado 				= 25;
	public static final String plantillaRegistroUsuario = "/plantilla/CrearUsuario.html";
	public static final String plantillaRecuperarUsuario = "/plantilla/RestablecerUsuario.html";
	public static  boolean activarDepurador 			= true;
	public static  int cambiarClave 					= 0;
	public static  int noCambiarClave 					= 1;
	
}
