package com.smile.myhome.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.smile.myhome.SingletonHolder
import com.smile.myhome.main.model.Article
import com.smile.myhome.main.repo.dao.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object : SingletonHolder<AppDataBase, Context>({
        Room.databaseBuilder(it.applicationContext,
                AppDataBase::class.java, "MyHome_db")
                .build()
    })
}