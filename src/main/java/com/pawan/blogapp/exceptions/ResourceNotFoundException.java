package com.pawan.blogapp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Resourcename;
	String fieldname;
	long fieldvalue;
	
	public ResourceNotFoundException(String resourcename, String fieldname, long fieldvalue) {
		
		super(String.format("%s not found with %s : %s",resourcename,fieldname,fieldvalue));
		
		this.Resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
		
	}

}
