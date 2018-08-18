package com.example.abhay.walkitiverecorder.controllers

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.abhay.walkitiverecorder.R
import kotlinx.android.synthetic.main.activity_welcome_screen.*
import java.util.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class WelcomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_welcome_screen)

        login_fields_container.visibility = View.GONE
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(mainLooper).post {
                    login_fields_container.visibility = View.VISIBLE
                }
            }
        }, 1000)
    }

    override fun onResume() {
        super.onResume()
        hide()
    }

    private fun hide() {
        window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}