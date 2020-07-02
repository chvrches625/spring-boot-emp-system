package com.jiangzhe.config;


import com.jiangzhe.component.LoginHandlerInterceptor;
import com.jiangzhe.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryCustomizer;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //相当于视图解析器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /jiang 请求来到 success
        registry.addViewController("/jiang").setViewName("success");
    }


    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean
    public WebMvcConfigurer WebMvcConfigurer(){
      return new WebMvcConfigurer(){
           @Override
           public void addViewControllers(ViewControllerRegistry registry) {
               //输入为空或者index.html都会跳转到login.html
               registry.addViewController("/").setViewName("login");
               registry.addViewController("/index.html").setViewName("login");

               registry.addViewController("/main.html").setViewName("dashboard");
           }

           @Override
           public void addInterceptors(InterceptorRegistry registry) {
               //过滤器，判断有没有登录的作用，也就是查看有没有session
               registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                       .excludePathPatterns("/index.html","/","/user/login","/webjars/**");
           }


       };

   }

    //处理国际化的字符显示
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }




}
