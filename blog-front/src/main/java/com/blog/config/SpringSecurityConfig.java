package com.blog.config;

import com.blog.filter.TokenValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * spring security配置
 *
 * @author a1387
 * @date 2023/02/20
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SpringSecurityConfig {

    @Resource
    TokenValidationFilter tokenValidationFilter;

    @Resource
    AccessDeniedHandler accessDeniedHandler;

    @Resource
    AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 安全过滤器链
     *
     * @param httpSecurity http安全性
     * @return {@link SecurityFilterChain}
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //关闭csrf
                .csrf().disable()
                //不在session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //方行/login接口用于登录
                .antMatchers("/users/login").anonymous()
                .antMatchers("/users/logOut").authenticated()
                .antMatchers("/comments","/links","/users/**","/files/**").authenticated()
                //除此之外所以接口均不需要权限验证,具体需要进行权限验证的接口使用注解进行
                .anyRequest().permitAll()
                .and()
                //开启跨域
                .cors();

        httpSecurity
                //间token验证过滤器加入Security过滤器链,位于UsernamePasswordAuthenticationFilter之前
                .addFilterBefore(tokenValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                //添加
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);

        //关闭默认登出接口
        httpSecurity.logout().disable();

        return httpSecurity.build();
    }

    /**
     * 身份验证管理器
     *
     * @param authenticationConfiguration 身份验证配置
     * @return {@link AuthenticationManager}
     * @throws Exception 异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 密码编码器
     *
     * @return {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
