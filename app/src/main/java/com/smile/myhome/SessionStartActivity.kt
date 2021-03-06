package com.smile.myhome

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.smile.myhome.main.ui.activity.SelectionActivity
import kotlinx.android.synthetic.main.activity_session_start.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SessionStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_session_start)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        startButton.setOnClickListener {
            startActivity(Intent(this, SelectionActivity::class.java))
        }

    }
}
