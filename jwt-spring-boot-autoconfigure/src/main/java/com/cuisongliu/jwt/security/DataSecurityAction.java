package com.cuisongliu.jwt.security;
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

/**
 * 信息传递的保护措施(传递的数据为json)
 * 说明:
 * 可以根据实际开发时的需要,编写自己的数据加密方案,只需实现此类,并在WebConfig下配置您所编写的实现类即可
 * @author cuisongliu [cuisongliu@qq.com]
 * @since 2018-02-28 22:31
 */
public interface DataSecurityAction {
    /**
     * 执行数据的保护措施
     *
     * @author cuisongliu [cuisongliu@qq.com]
     * @since 2018-02-28 22:31
     */
    String doAction(String beProtected);

    /**
     * 解除保护
     *
     * @author cuisongliu [cuisongliu@qq.com]
     * @since 2018-02-28 22:31
     */
    String unlock(String securityCode);
}
