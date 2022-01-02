package com.example.mytwitter

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TwitterUser(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "account_name") val accountName: String
)