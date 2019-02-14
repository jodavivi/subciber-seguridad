/**
 * 
 */
package com.subciber.seguridad.exception;

/**
 * @author josep
 *
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param errorCode The error code
     * @param message The error message
     */
    public BusinessException(Integer codigo, String mensaje) {
        super(codigo, mensaje);
    }

    /**
     * Constructor
     * @param errorCode The error code
     * @param errorMessage The error message
     * @param exception The exception object
     */
    public BusinessException(Exception exception, Integer errorCode, String errorMessage) {
        super(exception, errorCode, errorMessage);
    }

}
