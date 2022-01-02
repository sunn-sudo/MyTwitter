package com.example.mytwitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub);
        val returnButton: Button = findViewById<View>(R.id.returnButton) as Button;
        returnButton.setOnClickListener{finish()};
    }
}