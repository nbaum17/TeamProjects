package com;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class Config extends Application{
	
	/**
	 * constructor. 
	 * This is where making the initial connection with the DB will take place.
	 * also I think this might be where the socket is started.  
	 */
	public Config() {
		
	}

	@Override
    public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();
	    // register root resource
	    //classes.add(this.class);
        return classes;
    }
}
