package com.mahendra.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mahendra.services.FindEmployeeService;
import com.mahendra.services.InterestService;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    public Set<Class<?>> getClasses() {
    	//Add / Register all your REST services
        return new HashSet<Class<?>>(Arrays.asList(InterestService.class,FindEmployeeService.class));
    }
}
