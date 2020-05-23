package com.milepost.exampleSingleBoot.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Ruifu Hua on 2020/3/5.
 * 配置那些不被认证保护资源，即可以不携带token直接访问的资源。<br>
 * 此处的配置与com.milepost.ui.config.auth.ResourceServerConfig的配置是合并的。
 */
@Configuration
@Order(102)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers( "/test/test1", "/test/test2");
        web.ignoring().antMatchers( "/login/login.html", //登录页面
                "/login/doLogin", //登录操作
                "/login/checkCode",//验证码
                "/home/home.html"//首页
        );
    }
}
