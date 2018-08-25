package com.example.abhay.walkitiverecorder.controllers.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.abhay.walkitiverecorder.R
import com.example.abhay.walkitiverecorder.controllers.home.HomeDrawerActivity
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
        startActivity(Intent(this@SplashScreen, HomeDrawerActivity::class.java))
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(mainLooper).post {
                    login_fields_container.visibility = View.VISIBLE

                    login_button.setOnClickListener {
                        startActivity(Intent(this@SplashScreen, HomeDrawerActivity::class.java))
                    }
                }
            }
        }, 1000)
    }
}