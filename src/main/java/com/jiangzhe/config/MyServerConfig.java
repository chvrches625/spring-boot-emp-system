package com.jiangzhe.config;

import com.jiangzhe.filter.MyFilter;
import com.jiangzhe.listener.MyListener;
import com.jiangzhe.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

@Configuration
public class MyServerConfig {

    //注册三大组件
    //1.注册servlet
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(new MyServlet(),"/myServlet");
        return registrationBean;
    }

    //2.注册filter
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    //3.注册listener
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return registrationBean;
    }


    //配置嵌入式的Servlet容器的
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8081);
            }
        };
    }
}
