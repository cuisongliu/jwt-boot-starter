package com.cuisongliu.jwt.autoconfig.properties;
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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * jwt 参数属性
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-02-28 22:05
 */
@Configuration
@ConfigurationProperties(prefix = JwtProperties.PREFIX)
public class JwtProperties {
    public static final String PREFIX = "spring.jwt";

    /**
     * http请求头所需要的字段
     */
    private String header = "Authorization";

    /**
     * jwt秘钥
     */
    private String secret = "defaultSecret";

    /**
     * 7天 单位:秒
     */
    private Long expiration = 604800L;

    /**
     * 是否为需要认证jwt程序
     */
    private List<String> authPath = new ArrayList<>();

    /**
     * md5加密混淆key
     */
    private String md5Key = "randomKey";

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public List<String> getAuthPath() {
        return authPath;
    }

    public void setAuthPath(List<String> authPath) {
        this.authPath = authPath;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }
}
