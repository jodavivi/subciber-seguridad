/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.subciber.seguridad.dao.api.UsuarioDetalleTxDao;
import com.subciber.seguridad.dao.base.GenericaJPADaoImpl;
import com.subciber.seguridad.entity.UsuarioDetalle;

/**
 * @author josep
 *
 */
@Stateless
public class UsuarioDetalleTxDaoImpl extends GenericaJPADaoImpl<UsuarioDetalle>  implements UsuarioDetalleTxDao  , Serializable {

	private static final long serialVersionUID = 1L;


}

