package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.dto.ResponseRolComponenteFaltanteDto;
import com.subciber.seguridad.dto.RolComponenteFiltroDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para la consulta de componentes faltantes por rol
 * @author David Villanueva
 * @version 0.1, 04/08/2019
 * @update
 */
public interface RolComponenteFaltanteRxBusiness {

	/**
	 * @param metodo para consultar los componentes faltantes por rol
	 * @return devuelve los componentes faltantes
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<ResponseRolComponenteFaltanteDto> consultarRolComponenteFaltante(RequestGenericDto<RolComponenteFiltroDto> request) throws BusinessException;

	
}
