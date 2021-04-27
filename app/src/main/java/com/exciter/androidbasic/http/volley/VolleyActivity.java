package com.exciter.androidbasic.http.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.exciter.androidbasic.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    private static final String TAG = "VolleyActivity";
    private RequestQueue mQueue;
    private ImageView mIvPicture;
    private NetworkImageView mNetworkPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_string_request).setOnClickListener(v -> createStringRequest());
        findViewById(R.id.btn_json_object_request).setOnClickListener(v -> createJsonObjectRequest());
        findViewById(R.id.btn_image_request).setOnClickListener(v -> createImageRequest());
        findViewById(R.id.btn_image_loader).setOnClickListener(v -> loadImageByImageLoader());
        findViewById(R.id.btn_network_image_view).setOnClickListener(v -> loadImageByNetworkImageView());
        mIvPicture = findViewById(R.id.iv_picture);
        mNetworkPicture = findViewById(R.id.iv_network_picture);
        mQueue = Volley.newRequestQueue(this);
    }

    /**
     * 用StringRequest进行网络请求
     */
    private void createStringRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.baidu.com",
                response -> Log.d(TAG, "onResponse: " + response),
                error -> Log.d(TAG, "onErrorResponse: " + error.getMessage()));
        mQueue.add(stringRequest);
    }

    /**
     * 用JsonObjectRequest进行网络请求
     */
    private void createJsonObjectRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://wanandroid.com/wxarticle/chapters/json",
                response -> {
                    ArticleBean bean = new Gson().fromJson(String.valueOf(response), ArticleBean.class);
                    Log.d(TAG, "createJsonObjectRequest: " + bean.getData().get(0).getName());
                    Log.d(TAG, "createJsonObjectRequest: " + response);
                },
                error -> Log.d(TAG, "createJsonObjectRequest: " + error.getMessage()));
        mQueue.add(jsonObjectRequest);
    }

    /**
     * 用ImageRequest加载图片
     */
    private void createImageRequest() {
        ImageRequest imageRequest = new ImageRequest("https://www.wanandroid.com/resources/image/pc/logo.png",
                response -> {
                    mIvPicture.setImageBitmap(response);
                },
                0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565,
                error -> Log.d(TAG, "createImageRequest: " + error.getMessage()));
        mQueue.add(imageRequest);
    }

    /**
     * 用ImageLoader加载图片
     */
    private void loadImageByImageLoader() {
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(mIvPicture, R.drawable.ic_android, R.drawable.ic_android);
        imageLoader.get("https://www.wanandroid.com/resources/image/pc/logo.png", imageListener, 300, 300);
    }

    private void loadImageByNetworkImageView() {
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        mNetworkPicture.setDefaultImageResId(R.drawable.ic_android);
        mNetworkPicture.setErrorImageResId(R.drawable.ic_android);
        mNetworkPicture.setImageUrl("https://www.wanandroid.com/resources/image/pc/logo.png", imageLoader);
    }
}