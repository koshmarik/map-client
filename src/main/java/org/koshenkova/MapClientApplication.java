package org.koshenkova;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.koshenkova.converter.MapPointConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Config for Spring Boot application
 */
@SpringBootApplication
public class MapClientApplication {

    private static Log logger = LogFactory.getLog(MapClientApplication.class);

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("ServletContext initialized");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                logger.info("ServletContext destroyed");
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MapClientApplication.class, args);
    }
}
