package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.dto.ResponseGrupoComponenteFaltanteDto;
import com.subciber.seguridad.dto.GrupoComponenteFiltroDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para la consulta de componentes faltantes por grupo
 * @author David Villanueva
 * @version 0.1, 04/08/2019
 * @update
 */
public interface GrupoComponenteFaltanteRxBusiness {

	/**
	 * @param metodo para consultar los componentes faltantes por grupo
	 * @return devuelve los componentes faltantes
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<ResponseGrupoComponenteFaltanteDto> consultarGrupoComponenteFaltante(RequestGenericDto<GrupoComponenteFiltroDto> request) throws BusinessException;

	
}
