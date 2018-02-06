package com.yy.bjtours.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String get(String url) {
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(url);
            logger.info("HttpClientUtil get 请求 url:{}", httpGet.getURI());
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            logger.error("HttpClientUtil get 系统异常:", e);
        } finally {
            try {
                closeHttpClient(httpClient, httpGet, httpResponse);
            } catch (IOException e) {
                logger.error("HttpClientUtil get 释放资源系统异常：", e);
            }
        }
        return null;
    }

    public static String post(String url, Map<String, String> paramsMap) {
        logger.info("HttpClient post请求路径：{}|请求参数：{}", url, JSONObject.toJSONString(paramsMap));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setConnectionRequestTimeout(10000).setSocketTimeout(10000).build();
            HttpPost post = new HttpPost(url.trim());
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            Set<Map.Entry<String, String>> entrySet = paramsMap.entrySet();
            for (Iterator<Map.Entry<String, String>> its = entrySet.iterator(); its.hasNext(); ) {
                Map.Entry<String, String> entry = its.next();
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
            post.setEntity(entity);
            post.setConfig(config);
            CloseableHttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("##HttpClient post请求路径HTTP响应码：" + statusCode);
            if (statusCode == 200) {
                HttpEntity resEntity = response.getEntity();
                return EntityUtils.toString(resEntity);
            }
        } catch (IOException e) {
            logger.error("##HttpClientUtil post 系统异常：{}", e);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("##HttpClientUtil post 释放资源系统异常：{}", e);
                }
            }
        }
        return null;
    }

    public static void closeHttpClient(CloseableHttpClient httpClient, HttpRequestBase httpRequest, CloseableHttpResponse httpResponse) throws IOException {
        if (httpResponse != null) {
            httpResponse.close();
        }
        if (httpRequest != null) {
            httpRequest.releaseConnection();
        }
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
