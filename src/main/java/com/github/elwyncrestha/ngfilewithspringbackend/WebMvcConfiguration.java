package com.github.elwyncrestha.ngfilewithspringbackend;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * @author Elvin Shrestha on 8/22/2020
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations(String.format(Locale.ENGLISH, "file:///%s", uploadPath))
            .setCachePeriod(3600)
            .resourceChain(true)
            .addResolver(new PathResourceResolver());

    }
}
