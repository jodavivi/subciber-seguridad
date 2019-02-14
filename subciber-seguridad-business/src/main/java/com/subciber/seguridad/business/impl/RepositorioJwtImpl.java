/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.subciber.seguridad.business.api.RepositorioJwt;
import com.subciber.seguridad.business.dto.InfoJwt;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description implementacion de la interface RepositorioJwt
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */

@Startup
@Singleton
public class RepositorioJwtImpl implements RepositorioJwt {

	private Map<String, InfoJwt> logueadaos;
	
	@PostConstruct
    public void afterCreate() {
        System.out.println("Soup created");
        logueadaos = new HashMap<String, InfoJwt>();
    }

	@Override
	public void registrarUsuario(String token, InfoJwt informacion) throws BusinessException {
		
		logueadaos.put(token, informacion);
		
	}

	@Override
	public InfoJwt recuperarUsuario(String request) throws BusinessException {
		
		System.out.println(logueadaos.get(request));
		InfoJwt user = logueadaos.get(request);
		
		return user;
	}
	
	
}
