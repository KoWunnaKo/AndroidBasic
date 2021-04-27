package com.exciter.androidbasic.http;

import android.text.TextUtils;

import org.apache.http.NameValuePair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UrlConnManager {

    public static HttpURLConnection getHttpUrlConnection(String url) {
        HttpURLConnection connection = null;
        try {
            URL mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            //设置连接超时时间
            connection.setConnectTimeout(15000);
            //设置读取超时时间
            connection.setReadTimeout(15000);
            //设置请求参数
            connection.setRequestMethod("POST");
            //添加header
            connection.setRequestProperty("Connection", "Keep-Alive");
            //接入输入流
            connection.setDoInput(true);
            //传递参数时需要开启
            connection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void postParams(OutputStream output, List<NameValuePair> paramsList) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (NameValuePair pair : paramsList) {
            if (!TextUtils.isEmpty(sb)) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        writer.write(sb.toString());
        writer.close();
    }
}
