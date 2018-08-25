package com.example.abhay.walkitiverecorder.controllers.home

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import com.example.abhay.walkitiverecorder.R
import com.example.abhay.walkitiverecorder.controllers.spot.MapFragment
import kotlinx.android.synthetic.main.activity_home_drawer.*
import kotlinx.android.synthetic.main.toolbar_main.view.*

class HomeDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_drawer)

        initializeNavigationDrawer()
    }

    private fun initializeNavigationDrawer() {
        main_toolbar.menu_icon.setOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START))
                drawer_layout.closeDrawer(GravityCompat.START)
            else
                drawer_layout.openDrawer(GravityCompat.START)
        }

        with(supportFragmentManager.beginTransaction()) {
            add(R.id.fragment_container, MapFragment())
            commit()
        }

        navigation_menu.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_trips -> {
                    with(supportFragmentManager.beginTransaction()) {
                        add(R.id.fragment_container, MapFragment())
                        commit()
                    }
                    true
                }
                R.id.item_spots -> {
                    true
                }
                R.id.item_email_json -> {
                    true
                }
                R.id.item_clear_data -> {
                    true
                }
                R.id.item_logout -> {
                    true
                }
                else -> {
                    true
                }
            }
        }
    }
}