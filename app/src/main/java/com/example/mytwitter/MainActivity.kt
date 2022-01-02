package com.example.mytwitter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ボタン制御
        val sendButton: Button = findViewById<View>(R.id.sendButton) as Button
        sendButton.setOnClickListener {
            val intent = Intent(application, SubActivity::class.java)
            startActivity(intent)
        }

        // ListViewに表示するリスト項目をArrayListで準備する
        val data = ArrayList<Any>()
        data.add("国語")
        data.add("社会")
        data.add("算数")
        data.add("理科")
        data.add("生活")
        data.add("音楽")
        data.add("図画工作")
        data.add("家庭")
        data.add("体育")

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        val adapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.select_dialog_item, data)

        // ListViewにArrayAdapterを設定する
        val listView: ListView = findViewById<View>(R.id.listView) as ListView
        listView.setAdapter(adapter)
        //onWebView()
    }

    private fun setScreenSub() {

    }

    fun onWebView(){
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
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://twitter.com/search?q=vitaone&src=typed_query&f=image")
    }
}