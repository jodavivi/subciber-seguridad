package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dao.dto.ResponseRolComponenteDto;
import com.subciber.seguridad.dto.RolComponenteFiltroDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para la consulta de componentes por rol
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
public interface RolComponenteRxBusiness {

	/**
	 * @param metodo para consultar los componentes por rol
	 * @return devuelve los componentes por rol
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<ResponseRolComponenteDto> consultarRolComponente(RequestGenericDto<RolComponenteFiltroDto> request) throws BusinessException;

	
}
