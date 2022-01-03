package com.example.mytwitter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var mUserDao: TwitterUserDao
    lateinit var mAdapter: ArrayAdapter<String>
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
        mAdapter  = ArrayAdapter(this, android.R.layout.select_dialog_item, arrayListOf())

        // ListViewにArrayAdapterを設定する
        val listView: ListView = findViewById<View>(R.id.listView) as ListView
        listView.setAdapter(mAdapter)

        listView.setOnItemClickListener{ mAdapter, _, position, _ ->
            val twitterAccount = mAdapter.getItemAtPosition(position) as String
            val intent = Intent(application, TwitterActivity::class.java)
            intent.putExtra("twitterAccount", twitterAccount)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        getUser()
    }

    private fun getUser() {
        mUserList = mUserDao.getAll()
        mAdapter.clear()
        mUserList.forEach{ item ->
            val temp : String = item.id.toString() + ":" + item.accountName.toString()
            mAdapter.add(temp)
        }
    }

    private fun deleteUser(twitterAccount : String) {
        val tempList = twitterAccount.split(":")
        val Id = tempList[0]
        val deleteAccount = tempList[1]
        val deleteUser = TwitterUser(Id.toInt(), deleteAccount)
        mUserDao.delete(deleteUser)
        // データ再表示
        mUserList = mUserDao.getAll()
        mAdapter.clear()
        mUserList.forEach{ item ->
            val temp : String = item.id.toString() + ":" + item.accountName.toString()
            mAdapter.add(temp)
        }
    }
}

