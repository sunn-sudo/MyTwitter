package com.example.mytwitter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TwitterUserDao {
    @Query("SELECT * FROM TwitterUser")
    fun getAll(): List<TwitterUser>

    @Insert
    fun insert(TwitterUser: TwitterUser)

    @Delete
    fun delete(TwitterUser: TwitterUser)

}