package com.codehub.webapp.initializer;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.codehub.webapp.config.CORSFilter;
import com.codehub.webapp.config.EmailConfig;
import com.codehub.webapp.config.HibernateConfig;
import com.codehub.webapp.config.MvcConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB

	 @Override
     protected Class<?>[] getRootConfigClasses() {
         return new Class[] {HibernateConfig.class, EmailConfig.class};
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
     
     
     @Override
 	protected void customizeRegistration(Dynamic registration) {
 		 // upload temp file will put here
         File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

         // register a MultipartConfigElement
         MultipartConfigElement multipartConfigElement =
                 new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                         maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
 				
 		registration.setMultipartConfig(multipartConfigElement);
 		super.customizeRegistration(registration);
 	}
}
