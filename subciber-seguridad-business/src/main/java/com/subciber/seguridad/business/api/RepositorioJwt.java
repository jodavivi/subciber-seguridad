/**
 * 
 */
package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dto.EstructuraTokensDto;
import com.subciber.seguridad.dto.FiltroTokensDto;
import com.subciber.seguridad.dto.InfoJwt;
import com.subciber.seguridad.dto.UsuariosLoginDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para almacenar los usuarios logueados
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public interface RepositorioJwt {

	/**
	 * @param metodopara almacenar los usuarios logueados
	 * @return devuelve informacion del usuario logueado
	 * @throws BusinessException
	 */
	public abstract void registrarUsuario(String token, InfoJwt informacion) throws BusinessException;
	
	/**
	 * @param metodopara almacenar los usuarios logueados
	 * @return devuelve informacion del usuario logueado
	 * @throws BusinessException
	 */
	public abstract InfoJwt recuperarUsuario(String request) throws BusinessException;
	
	/**
	 * @param metodo para obtener los usuarios logueados
	 * @return devuelve informacion de los usuario logueado
	 * @throws BusinessException
	 */
	public abstract UsuariosLoginDto usuarioLogueados() throws BusinessException;
	
	/**
	 * @param metodo para actualizar el token
	 * @return devuelve el nuevo token generado
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<EstructuraTokensDto> validarTokens(RequestGenericDto<FiltroTokensDto> tokens) throws BusinessException;
	
	/**
	 * @param metodo para eliminar session
	 * @return  parametro de AuditResponseDto
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarSession(RequestGenericDto<FiltroTokensDto> tokens) throws BusinessException;

	/**
	 * @param metodo para generar Tokens
	 * @return devuelve el nuevo token generado
	 * @throws 
	 */
	public String generarToken(String session, Integer usuarioId, String usuario, String email, String codigodeaccesos, String rol);
	
	/**
	 * @param metodo para depurar sesiones 
	 * @return  
	 * @throws 
	 */
	public void depurarSesiones();
}
