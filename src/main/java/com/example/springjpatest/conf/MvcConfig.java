package com.example.springjpatest.conf;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public static final Locale defaultLocale = Locale.ENGLISH;
    private static final Logger log = LoggerFactory.getLogger(MvcConfig.class);

    public void addViewControllers(@NotNull ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/jquery/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/3.6.0/");
        registry.addResourceHandler("/webjars/popper/**").addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/2.5.4/");
        registry.addResourceHandler("/webjars/bootstrap/**").addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/4.6.0/");
        registry.addResourceHandler("/webjars/font-awesome/**").addResourceLocations("classpath:/META-INF/resources/webjars/font-awesome/5.15.4/");
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(defaultLocale);
        resolver.setCookieMaxAge(((60 * 60) * 24) * 30);
        return resolver;
    }

    @Bean(name = "messageSource")
    @Description("Strategy interface for resolving messages, with support for the parameterization and internationalization of such messages.")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean(name = "localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addFormatters(@NotNull FormatterRegistry registry) {
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.setStyle(DateFormat.MEDIUM);
        registry.addFormatter(dateFormatter);
    }

    @Override
    public void addArgumentResolvers(
            @NotNull List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        resolver.setMaxPageSize(50);
        PageRequest pageRequest = PageRequest.of(0, 10);
        resolver.setFallbackPageable(pageRequest);
        argumentResolvers.add(resolver);
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
    }

}
