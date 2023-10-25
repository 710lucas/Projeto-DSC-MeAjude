package com.si.meAjude.config.interceptores.config;

import com.si.meAjude.config.interceptores.DonationInterceptorFilter;
import com.si.meAjude.config.interceptores.UserInterceptorFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DonationInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DonationInterceptorFilter()).addPathPatterns("/donations").addPathPatterns("/donations/{id}");
    }
}
