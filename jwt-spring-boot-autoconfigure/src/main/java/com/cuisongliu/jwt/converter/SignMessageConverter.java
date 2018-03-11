package com.cuisongliu.jwt.converter;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cuisongliu.jwt.autoconfig.properties.JwtProperties;
import com.cuisongliu.jwt.exception.JwtServiceException;
import com.cuisongliu.jwt.pojo.TransferPOJO;
import com.cuisongliu.jwt.security.DataSecurityAction;
import com.cuisongliu.jwt.tools.JwtTokenTool;
import com.cuisongliu.jwt.tools.MD5Tool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 带签名的http信息转化器
 *
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-02-28 22:25
 */
public class SignMessageConverter  extends FastJsonHttpMessageConverter {
    private static final Logger logger = Logger.getLogger(SignMessageConverter.class);
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenTool jwtTokenTool;

    @Autowired
    private DataSecurityAction dataSecurityAction;

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        InputStream in = inputMessage.getBody();
        Object o = JSON.parseObject(in, super.getFastJsonConfig().getCharset(), TransferPOJO.class, super.getFastJsonConfig().getFeatures());

        //先转化成原始的对象
        TransferPOJO baseTransferEntity = (TransferPOJO) o;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //校验签名
        String token = request.getHeader(jwtProperties.getHeader()).substring(7);
        String md5KeyFromToken = jwtTokenTool.getMd5KeyFromToken(token);

        String object = baseTransferEntity.getObject();
        String json = dataSecurityAction.unlock(object);
        String encrypt = MD5Tool.getMD5Str(object + md5KeyFromToken);

        if (Objects.requireNonNull(encrypt).equals(baseTransferEntity.getSign())) {
            logger.info("签名校验成功!");
        } else {
            logger.error("签名校验失败,数据被改动过!");
            throw new JwtServiceException(700,"签名验证失败");
        }
        //校验签名后再转化成应该的对象
        return JSON.parseObject(json, type);
    }
}
