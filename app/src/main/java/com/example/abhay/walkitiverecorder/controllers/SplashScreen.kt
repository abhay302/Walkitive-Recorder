package com.example.abhay.walkitiverecorder.controllers

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.abhay.walkitiverecorder.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        login_fields_container.visibility = View.GONE
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(mainLooper).post {
                    login_fields_container.visibility = View.VISIBLE
                }
            }
        }, 1000)
    }
}