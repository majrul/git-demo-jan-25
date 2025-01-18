package com.training.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class AppConfig extends Application {

	// Check why HelloResource still got loaded?
	/*@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>();
	}*/
}
