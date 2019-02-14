/**
 * 
 */
package com.subciber.seguridad.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import com.subciber.seguridad.util.ConstantesConfig;

/**
 * @author josep
 *s
 */
@Dependent
public abstract class BaseJPADao<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = ConstantesConfig.persistenceUnit)
	protected EntityManager entityManager;
	protected Class<T> entityClass;
	protected String tableName;

	/**
	 * Empty constructor
	 */
	@SuppressWarnings("unchecked")
	public BaseJPADao() {
		super();
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Table table = entityClass.getAnnotation(Table.class);
		this.tableName = table.name();
	}
	
}

