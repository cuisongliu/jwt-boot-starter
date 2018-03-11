package com.cuisongliu.jwt.filter;
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
import com.cuisongliu.jwt.tools.JwtTokenTool;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对客户端请求的jwt token验证过滤器
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-02-28 22:11
 */
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenTool jwtTokenTool;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        for (String prefix:jwtProperties.getAuthPath()){
            //如果是接口程序进入filter
            if (request.getServletPath().startsWith(prefix)){
                final String requestHeader = request.getHeader(jwtProperties.getHeader());
                String authToken = null;
                if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                    authToken = requestHeader.substring(7);

                    //验证token是否过期,包含了验证jwt是否正确
                    try {
                        boolean flag = jwtTokenTool.isTokenExpired(authToken);
                        if (flag) {
                            WebUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
                            return;
                        }
                    } catch (JwtException e) {
                        //有异常就是token解析失败
                        WebUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                        return;
                    }
                } else {
                    //header没有带Bearer字段
                    WebUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                    return;
                }
                filterChain.doFilter(request, response);
            }else {
                filterChain.doFilter(request, response);
                return;
            }
        }
    }
}
