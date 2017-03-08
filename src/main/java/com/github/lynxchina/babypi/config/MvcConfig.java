package com.github.lynxchina.babypi.config;

import com.github.lynxchina.babypi.BabyPiWebApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.context.embedded.MultipartConfigFactory;
/**
 * @author chris
 * @version 1/8/16-10:51 AM
 */
@Configuration
@ComponentScan(basePackageClasses = BabyPiWebApp.class, includeFilters = @Filter(Controller.class),
        useDefaultFilters = false)
public class MvcConfig extends WebMvcConfigurationSupport {

    private static final String MSG_SOURCE = "/resources/messages";
    private static final String VIEWS = "/WEB-INF/templates/";

    private static final String RESOUCES_HANDLER = "/resouces/";
    private static final String RESOUCES_LOCATION = RESOUCES_HANDLER + "**";

//    @Override
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping reqMappingHandlerMapping = super.requestMappingHandlerMapping();
//        reqMappingHandlerMapping.setUseSuffixPatternMatch(false);
//        reqMappingHandlerMapping.setUseTrailingSlashMatch(false);
//        return reqMappingHandlerMapping;
//    }

//    @Bean(name = "msgSource")
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource msgSource = new ReloadableResourceBundleMessageSource();
//        msgSource.setBasename(MSG_SOURCE);
//        msgSource.setCacheSeconds(5);
//        return msgSource;
//    }

    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix(VIEWS);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

//    @Bean
//    public UrlTemplateResolver urlTemplateResolver() {
//        return new UrlTemplateResolver();
//    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver());
//        templateEngine.addTemplateResolver(urlTemplateResolver());
//        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return viewResolver;
    }

//    @Override
//    public Validator getValidator() {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setValidationMessageSource(messageSource());
//        return validator;
//    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOUCES_HANDLER).addResourceLocations(RESOUCES_LOCATION);
    }

    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }

}
