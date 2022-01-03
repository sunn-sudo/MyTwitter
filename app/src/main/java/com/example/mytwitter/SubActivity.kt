package com.example.mytwitter

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class SubActivity : AppCompatActivity() {

    lateinit var mUserDao: TwitterUserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub);

        // DAO準備
        mUserDao = TwitterUserDatabase.getInstance(this).TwitterUserDao()

        val returnButton: Button = findViewById<View>(R.id.returnButton) as Button;
        returnButton.setOnClickListener{
            finish()
        };
        val insertButton: Button = findViewById<View>(R.id.insertButton) as Button;
        insertButton.setOnClickListener{
            insertUser()
        };
    }

    private fun insertUser() {
        val editText: EditText = findViewById<View>(R.id.editText) as EditText;
        val accountName: String = editText.text.toString()
        if (accountName.isNotBlank()){
            val newUser = TwitterUser(0, accountName)
            AlertDialog.Builder(this)
                    .setTitle("確認")
                    .setMessage("「$accountName」を登録しますか")
                    .setPositiveButton("OK") { dialog, which ->
                        mUserDao.insert(newUser)
                        editText.text = null
                    }
                    .setNegativeButton("No") { dialog, which -> }
                    .show()
        } else if (accountName.isBlank()){
            AlertDialog.Builder(this)
                .setTitle("確認")
                .setMessage("アカウント名を入力してください")
                .setPositiveButton("OK") { dialog, which ->
                }
                .show()
        }
    }
}