/**
 * 
 */
package com.subciber.seguridad.util;

/**
 * @author josep
 *
 */
public class GenerarTokensTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Estructura Token
		//sessionId;IdUsuario;usuario,email;fechacreacion;fechaexpiracion;codigodeaccesos
		EncriptacionAES encriptador = new EncriptacionAES();
		String session = "l4QEV0A8K8M4SzT9Dd8JhfEfBnZdsc2ZCeqfeGen";
		String usuarioId="1";
		String usuario="U000001";
		String email="jodavivi@gmail.com";
		String fechaCreacion = "2019-02-15T12:44:15.477";
		String fechaExpiracion="2019-04-15T12:44:15.477";
		String codigosAccesos = encriptador.encrypt("SEG0001", ConstantesConfig.claveEncripacionAES);
		
		String datosAccesos = usuarioId+";" +usuario+";" + email+";" + fechaCreacion+";" + fechaExpiracion+";" + codigosAccesos;
		
		String encriptarDatos = encriptador.encrypt(datosAccesos, ConstantesConfig.claveEncripacionAES);
		
		String token = session+";" +encriptarDatos;

		System.out.println(token);
	}

}
