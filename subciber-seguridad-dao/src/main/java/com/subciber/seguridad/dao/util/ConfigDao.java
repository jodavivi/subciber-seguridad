package com.subciber.seguridad.dao.util;

public class ConfigDao {

	public static final String persistenceUnit = "basePU";
	
	//Sequencias BD
	public static final String sequeciaTablaUsuario 	= "select nextval ('\"Seguridad\".\"Usuario_Id_seq\"')";
	public static final String sequeciaTablaUsuarioRol 	= "select nextval ('\"Seguridad\".\"UsuarioRol_Id_seq\"')";
		
}
