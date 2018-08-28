package com.smile.myhome.main.repo.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.smile.myhome.main.model.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM Article")
    fun getReviewedArticles(): LiveData<List<Article>>

    @Insert(onConflict = REPLACE)
    fun add(model: Article)

    @Query("DELETE FROM Article")
    fun nukeTable()
}