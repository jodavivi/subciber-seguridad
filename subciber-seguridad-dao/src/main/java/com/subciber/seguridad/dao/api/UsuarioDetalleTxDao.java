/**
 * 
 */
package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.dao.base.GenericaJPADao;
import com.subciber.seguridad.entity.UsuarioDetalle;

/**
 * @description Interface para la creacion de usuairos
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Local
public interface UsuarioDetalleTxDao extends GenericaJPADao<UsuarioDetalle>{

}
