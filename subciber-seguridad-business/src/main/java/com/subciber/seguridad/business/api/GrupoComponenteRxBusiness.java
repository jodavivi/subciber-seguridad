package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dao.dto.ResponseGrupoComponenteDto;
import com.subciber.seguridad.dto.GrupoComponenteFiltroDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para la consulta de componentes por grupo
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
public interface GrupoComponenteRxBusiness {

	/**
	 * @param metodo para consultar los componentes por grupo
	 * @return devuelve los componentes
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<ResponseGrupoComponenteDto> consultarGrupoComponente(RequestGenericDto<GrupoComponenteFiltroDto> request) throws BusinessException;

	
}
