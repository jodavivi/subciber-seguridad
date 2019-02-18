/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

import com.subciber.seguridad.dao.api.UsuarioDao;
import com.subciber.seguridad.dao.base.GenericaJPADaoImpl;
import com.subciber.seguridad.entity.Usuario;

/**
 * @author josep
 *
 */
@Stateless
public class UsuarioDaoImpl extends GenericaJPADaoImpl<Usuario>  implements UsuarioDao  , Serializable {

	private static final long serialVersionUID = 1L;


}

