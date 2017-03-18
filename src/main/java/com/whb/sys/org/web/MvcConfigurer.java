package com.whb.sys.org.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.whb.sys.org.web.context.BasePathInterceptor;

@Configuration  
public class MvcConfigurer extends WebMvcConfigurerAdapter {

    @Override  
    public void addViewControllers(ViewControllerRegistry registry) {  
        registry.addViewController("/error").setViewName("error.html");  
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);  
    }
    
    @Override  
    public void configurePathMatch(PathMatchConfigurer configurer) {  
        super.configurePathMatch(configurer);  
        configurer.setUseSuffixPatternMatch(false);  
    }
    
    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截

        registry.addInterceptor(new BasePathInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);

    }
    //静态资源访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
    	  registry.addResourceHandler("/templates/**")
    	  .addResourceLocations("classpath:/templates/")
    	  .addResourceLocations("POST")
    	  .setCachePeriod(31556926);
    	  super.addResourceHandlers(registry);
    }
}
