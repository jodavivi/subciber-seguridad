/**
 * 
 */
package com.subciber.seguridad.rest.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author josep
 *
 */
@WebFilter("/*")
public class FiltroAuditoria extends HttpFilter{

	private static final long serialVersionUID = 1L;
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
		        Arrays.asList("", "/autenticacion")));
	 
	 @Override
	    protected void doFilter(HttpServletRequest req, HttpServletResponse res, 
	    FilterChain chain) throws IOException, ServletException {
		 String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", ""); 
		 String session = req.getHeader("tokens");
		 
		 boolean loggedIn = (session != null && session != "" && session.length() > 0);
	     boolean allowedPath = ALLOWED_PATHS.contains(path);
		 
	     if (loggedIn || allowedPath) {
	    	 	System.out.println("Logueado");
	    	 	res.addHeader("tokens", session);
	            chain.doFilter(req, res);
	        }
	        else {
	        	System.out.println("No Logeado");
	            //res.sendRedirect(req.getContextPath() + "/login");
	        	chain.doFilter(req, res);
	        }
	    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
