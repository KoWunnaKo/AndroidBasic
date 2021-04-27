package com.exciter.androidbasic.http;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    public void getData() {
        new Thread(() -> useHttpClientGet("https://www.baidu.com")).start();
    }

    public void postData() {
        new Thread(() -> useHttpClientPost("http://ip.taobao.com/service/getIpInfo.php")).start();
    }

    private HttpClient createHttpClient() {
        HttpParams defaultHttpParams = new BasicHttpParams();
        //设置链接超时
        HttpConnectionParams.setConnectionTimeout(defaultHttpParams, 15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(defaultHttpParams, 15000);
        HttpConnectionParams.setTcpNoDelay(defaultHttpParams, true);
        HttpProtocolParams.setVersion(defaultHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(defaultHttpParams, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(defaultHttpParams, true);
        return new DefaultHttpClient(defaultHttpParams);
    }

    private void useHttpClientGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Connection", "Keep-Alive");
        try {
            HttpClient httpClient = createHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            int code = httpResponse.getStatusLine().getStatusCode();
            if (null != httpEntity) {
                InputStream inputStream = httpEntity.getContent();
                String response = convertStreamToString(inputStream);
                Log.d("exciter", "useHttpClientGet: " + code + " " + response);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void useHttpClientPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Connection", "Keep-Alive");
        try {
            HttpClient httpClient = createHttpClient();
            List<NameValuePair> postParams = new ArrayList<>();
            //要传递的参数
            postParams.add(new BasicNameValuePair("ip", "59.108.54.37"));
            httpPost.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            int code = httpResponse.getStatusLine().getStatusCode();
            if (null != httpEntity) {
                InputStream inputStream = httpEntity.getContent();
                String response = convertStreamToString(inputStream);
                Log.d("exciter", "useHttpClientPost: " + code + " " + response);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
