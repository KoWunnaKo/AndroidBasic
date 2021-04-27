package com.exciter.androidbasic.http;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionTest {

    public void postData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userHttpUrlConnectionPost("http://ip.taobao.com/service/getIpInfo.php");
            }
        }).start();
    }

    private void userHttpUrlConnectionPost(String url) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = UrlConnManager.getHttpUrlConnection(url);
        try {
            List<NameValuePair> postParams = new ArrayList<>();
            postParams.add(new BasicNameValuePair("ip", "59.108.54.37"));
            UrlConnManager.postParams(httpURLConnection.getOutputStream(), postParams);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            int code = httpURLConnection.getResponseCode();
            String response = convertStreamToString(inputStream);
            Log.d("exciter", "userHttpUrlConnectionPost: " + code + " " + response);
            inputStream.close();
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
