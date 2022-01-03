package com.example.mytwitter

import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class TwitterActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter);
        val intent = getIntent()
        val temp : String = intent.getStringExtra("twitterAccount").toString()
//      val tempList = temp.split(":")
//      val twitterAccount = tempList[1]
        onWebView(temp)
    }

    fun onWebView(twitterAccount: String){
        val myWebView = WebView(this)
        setContentView(myWebView);
        // キャッシュサイズ（byte）
        myWebView.getSettings().setAppCacheMaxSize(32 * 1024 * 1024);
        // キャッシュ格納場所のパス
        myWebView.getSettings().setAppCachePath("/data/data/" + getPackageName() + "/cache");
        // ファイルアクセスを許可
        myWebView.getSettings().setAllowFileAccess(true);
        // JavaScriptを有効化(JavaScript インジェクションに注意)
        myWebView.getSettings().setJavaScriptEnabled(true);
        // Web Storage を有効化
        myWebView.getSettings().setDomStorageEnabled(true);

        // Hardware Acceleration ON
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        );

        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://twitter.com/search?q=" + twitterAccount + "&src=typed_query&f=image")
    }
}