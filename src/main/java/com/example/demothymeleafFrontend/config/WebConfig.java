package com.example.demothymeleafFrontend.config;

import com.example.demothymeleafFrontend.support.APIBaseURI;
import com.example.demothymeleafFrontend.support.Constant;
import com.example.demothymeleafFrontend.support.WebLinkFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public APIBaseURI apiBaseURI() {
        return new APIBaseURI();
    }

    @Bean(name = "webLinkFactory")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public WebLinkFactory webLinkFactory() {
        return new WebLinkFactory();
    }

    // internationalization config.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public HandlerInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor =new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(Constant.LANGUAGE_PARAM);
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver =new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("bn", "BN"));
        return sessionLocaleResolver;
    }

//    TODO: need to study what is the purpose of MessageSource
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(Constant.LANGUAGE_BASENAME);
        messageSource.setDefaultEncoding(Constant.UTF8_ENCODING);
//        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
    // internationalization config.
}
