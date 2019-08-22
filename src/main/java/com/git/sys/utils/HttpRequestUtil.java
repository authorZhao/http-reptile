
package com.git.sys.utils;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类主要是进行一些http请求的测试类
 */
public class HttpRequestUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public void getHtmlPost(){
        // 创建默认的httpClient实例.
        String url = "https://www.jianshu.com/p/f9ebd23e8da4";
        url = "https://search.17k.com/search.xhtml";
        url = "http://www.baidu.com";
        Map<String, Object> map = new HashMap<>();
        map.put("c.st",0);
        map.put("c.q","近战狂兵");
        //url = setDoGetUrl(url,map);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(60000)
                .setConnectionRequestTimeout(60000).build();

        CloseableHttpClient httpclient;
        HttpClientContext httpClientContext = HttpClientContext.create();
        CookieStore cookieStore = null;
        cookieStore = new BasicCookieStore();
        httpclient = HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore).build();
        //String resGet = doGet(url,map,httpclient,httpClientContext);

    }

    public static String doPost(String url, Map map, CloseableHttpClient httpclient, HttpClientContext httpClientContext) {
        setDoGetUrl(url,map);
        String web="";
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Connection", "close");
        httppost.setHeader("Content-Encoding", "gzip");
        httppost.setHeader("Server", "openresty");
        httppost.setHeader("Transfer-Encoding", "chunked");
        httppost.setHeader("Vary", "Accept-Encoding");
        httppost.setHeader("Content-Type","text/html;charset=UTF-8");
        setDoPostParam(httppost,map,null);
        try {
            CloseableHttpResponse response = httpclient.execute(httppost,httpClientContext);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    web = EntityUtils.toString(entity,"utf-8");
                    CookieStore cookieStore22 = httpClientContext.getCookieStore();
                    List<Cookie> listCookie = cookieStore22.getCookies();
                    httpClientContext.getCookieStore().getCookies().forEach(System.out::println);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return web;
    }


    public static String setDoGetUrl(String url, Map<String, Object> map) {
        if(StringUtils.isBlank(url))return url;
        if(MapUtils.isEmpty(map))return url;
        if(!url.endsWith("?")) url += "?";
        for (Map.Entry<String, Object> entry:map.entrySet()) {
            if(StringUtils.isBlank(entry.getKey()))continue;
            if(!url.endsWith("?")){
                url += "&" + entry.getKey() + "=" + entry.getValue().toString();
            }else{
                url += entry.getKey() + "=" + entry.getValue().toString();
            }
        }
        return url;
    }

    public static void setDoPostParam(HttpPost post, Map<String, Object> map, String charset) {
        if(StringUtils.isBlank(charset))charset="UTF-8";
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if(map==null)return;
        map.entrySet().stream().forEach(m->{
            formparams.add(new BasicNameValuePair(m.getKey(), m.getValue().toString()));

        });
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, charset);
            post.setEntity(uefEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


}

