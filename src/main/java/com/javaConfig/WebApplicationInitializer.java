package com.javaConfig;

import com.javaConfig.context.RootContextConfig;
import com.javaConfig.context.ServletContextConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by lsylsy289.
 * Since 2017-05-29
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                RootContextConfig.class
        };
    }

    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {

        WebApplicationContext servletAppContext = createServletApplicationContext();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", dispatcherServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping(getServletMappings());
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "/"
        };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");

        return  new Filter[] {
          characterEncodingFilter, new HiddenHttpMethodFilter()
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                ServletContextConfig.class
        };
    }
}
