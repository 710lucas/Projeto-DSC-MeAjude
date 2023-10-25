package com.si.meAjude.config.interceptores.campaign;

import com.si.meAjude.config.interceptores.donation.DonationInterceptorFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CampaignInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DonationInterceptorFilter()).addPathPatterns("/campaigns").addPathPatterns("/campaigns/{id}");
    }
}
