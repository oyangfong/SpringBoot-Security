package com.mysecurity.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置Spring Security的Filter链
    //过滤静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    /*
     * 设定用户访问权限
     * 用户身份可以访问 订单相关API
     * */
    //自定义登录，和登录拦截等操作
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/signup","/about").permitAll()  //无需认证
                .antMatchers("/orders/**").hasRole("USER")    //用户权限
                .antMatchers("/admin/**").hasRole("ADMIN")    //管理员权限
                .anyRequest().authenticated()  //需认证
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login").permitAll()
                .and().logout().permitAll()
                .and().csrf().disable();  //暂时禁用CSRF，否则无法提交表单
    }

    /*
    * 在内存中验证登录
    * In-Memory Authentication  基于内存的身份认证功能。也就是说身份信息是保存到内存中。这种方式了解为主，在实际开发中使用较少。
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加自定义用户
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("ADMIN","USER")
                .and()
                .withUser("haha").password("123456").roles("USER");
    }


    /*
    *   1 问题描述：在写基于Spring cloud微服务的OAuth2认证服务时，因为Spring-Security从4+升级到5+，
    *               导致There is no PasswordEncoder mapped for the id “null”错误。
    *               这个错主要发生在Spring-Sercurity5.X版本上，例如SpringBoot2.x。
    *               导致这个错误发生主要原因就是在之前版本中的NoOpPasswordEncoder被DelegatingPasswordEncoder取代了，
    *               而保存在数据库中的密码没有指定加密方式
    *   2、解决方案：
    *               可在密码验证类中添加如下代码
    *
    * */
   @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


}
