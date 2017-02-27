package com.codehub.webapp.initializer;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.codehub.webapp.config.CORSFilter;
import com.codehub.webapp.config.HibernateConfig;
import com.codehub.webapp.config.MvcConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	 @Override
     protected Class<?>[] getRootConfigClasses() {
         return new Class[] {HibernateConfig.class};
     }

     @Override
     protected Class<?>[] getServletConfigClasses() {
         return new Class[] {MvcConfig.class};
     }

     @Override
     protected String[] getServletMappings() {
         return new String[] {"/"};
     }

     @Override
     protected Filter[] getServletFilters() {
    	 Filter [] singleton = { new CORSFilter() };
    	 return singleton;
     }
}
