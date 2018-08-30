package com.smile.myhome.main.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smile.myhome.R
import com.smile.myhome.main.ui.fragment.ReviewFragment
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, ReviewFragment())
                .commit()
    }
}
