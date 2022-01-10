package com.example.mytwitter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import kotlin.collections.ArrayList

class TwitterListAdapter<T>(val context: Context, val ItemList: ArrayList<TwitterUser> ) : BaseAdapter() {

    // DAO準備
    var mUserDao = TwitterUserDatabase.getInstance(context as MainActivity).TwitterUserDao()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.twitter_row, null)
        val name = view.findViewById<TextView>(R.id.name)
        val deleteButton = view.findViewById<TextView>(R.id.delete_button)
        val Item = ItemList[position]
        // リスト表示内容
        name.text = Item.accountName
        // リスト削除ボタン設定
        deleteButton.setOnClickListener{
            deleteItem(Item.id,Item.accountName)
        }
        name.setOnClickListener{
            val twitterAccount = Item.accountName
            val intent = Intent(this.context.applicationContext, TwitterActivity::class.java)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("twitterAccount", twitterAccount)
            startActivity(this.context.applicationContext,intent,null)
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return ItemList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return ItemList.size
    }

    // リスト削除処理
    private fun deleteItem(id: Int, accountName: String) {
        val deleteList = TwitterUser(id, accountName)
        mUserDao.delete(deleteList)
        ItemList.remove(deleteList)
        this.notifyDataSetChanged()
    }
}