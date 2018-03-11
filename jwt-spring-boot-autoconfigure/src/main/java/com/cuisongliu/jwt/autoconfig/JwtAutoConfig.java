package com.cuisongliu.jwt.autoconfig;
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 cuisongliu@qq.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import com.cuisongliu.jwt.autoconfig.properties.JwtProperties;
import com.cuisongliu.jwt.converter.SignMessageConverter;
import com.cuisongliu.jwt.filter.AuthFilter;
import com.cuisongliu.jwt.security.Base64SecurityAction;
import com.cuisongliu.jwt.security.DataSecurityAction;
import com.cuisongliu.jwt.tools.JwtTokenTool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jwt springboot 集成
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-02-28 22:05
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfig {

    @Bean
    public SignMessageConverter withSignMessageConverter() {
        SignMessageConverter withSignMessageConverter = new SignMessageConverter();
        withSignMessageConverter.setFastJsonConfig(FastJsonBuilder.INSTANCE.fastjsonConfig());
        withSignMessageConverter.setSupportedMediaTypes(FastJsonBuilder.INSTANCE.supportedMediaType());
        return withSignMessageConverter;
    }

    @Bean
    public AuthFilter jwtAuthenticationTokenFilter() {
        return new AuthFilter();
    }

    @Bean
    public DataSecurityAction dataSecurityAction() {
        return new Base64SecurityAction();
    }


    @Bean
    public JwtTokenTool jwtTokenTool(JwtProperties jwtProperties){
        return new JwtTokenTool(jwtProperties);
    }
}
