/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.subciber.seguridad.dao.api.UsuarioTxDao;
import com.subciber.seguridad.dao.base.GenericaJPADaoImpl;
import com.subciber.seguridad.entity.Usuario;

/**
 * @author josep
 *
 */
@Stateless
public class UsuarioTxDaoImpl extends GenericaJPADaoImpl<Usuario>  implements UsuarioTxDao  , Serializable {

	private static final long serialVersionUID = 1L;


}

