/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htm.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Manax
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.htm.controllers"})
class WebApplicationContextConfig implements WebMvcConfigurer{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    } 

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("resources/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/image/");
    }
    
    
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
         
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        
        return  resolver;
    }   
    
    @Bean
     public MessageSource messageSource() {
        ResourceBundleMessageSource msg

                    = new ResourceBundleMessageSource();

        msg.setBasename("messages");
        // resource.setBasenames("messages1", "messages2");
        return msg;
}
     @Bean
      public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver

                    = new CommonsMultipartResolver();

        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
      
      @Bean
        public Cloudinary cloudinary() {
            Cloudinary c

                    = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "di6xnbru0",
                    "api_key", "334138387424576",
                    "api_secret", "Vwah4J-9zxWbyA0XnyevJCTrw8c",
                    "secure", true));
        return c;
}
}
