package com.example.mytwitter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var mUserDao: TwitterUserDao
    lateinit var mAdapter: TwitterListAdapter<TwitterUser>
    private var mUserList: List<TwitterUser> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ボタン制御
        val sendButton: Button = findViewById<View>(R.id.sendButton) as Button
        sendButton.setOnClickListener {
            val intent = Intent(application, SubActivity::class.java)
            startActivity(intent)
        }

        // DAO準備
        mUserDao = TwitterUserDatabase.getInstance(this).TwitterUserDao()

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        mAdapter  = TwitterListAdapter(this,  arrayListOf<TwitterUser>())

        // ListViewにArrayAdapterを設定する
        val listView: ListView = findViewById<View>(R.id.listView) as ListView
        listView.setAdapter(mAdapter)
    }

    override fun onStart() {
        super.onStart()
        getUser()
        mAdapter.notifyDataSetChanged()
    }

    private fun getUser() {
        mUserList = mUserDao.getAll()
        mAdapter.ItemList.clear()
        mUserList.forEach{ item ->
            mAdapter.ItemList.add(TwitterUser(item.id,item.accountName))
        }
    }
}

