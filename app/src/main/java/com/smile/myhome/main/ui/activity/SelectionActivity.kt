package com.smile.myhome.main.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.smile.myhome.R
import com.smile.myhome.main.ui.fragment.SelectionFragment

class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection_activity)
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, SelectionFragment())
                .commit()
    }
}
