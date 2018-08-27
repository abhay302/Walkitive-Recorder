package com.example.abhay.walkitiverecorder.controllers.home

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import com.example.abhay.walkitiverecorder.R
import com.example.abhay.walkitiverecorder.controllers.spot.MapFragment
import com.example.abhay.walkitiverecorder.controllers.trips.ShowTripsFragment
import kotlinx.android.synthetic.main.activity_home_drawer.*
import kotlinx.android.synthetic.main.toolbar_main.*
import kotlinx.android.synthetic.main.toolbar_main.view.*

class HomeDrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_drawer)

        initializeNavigationDrawer()
    }

    private fun initializeNavigationDrawer() {
        main_toolbar.main_toolbar_menu_icon.setOnClickListener {
            if (drawer_layout.isDrawerOpen(GravityCompat.START))
                drawer_layout.closeDrawer(GravityCompat.START)
            else
                drawer_layout.openDrawer(GravityCompat.START)
        }

        with(supportFragmentManager.beginTransaction()) {
            //add(R.id.fragment_container, MapFragment())
            main_toolbar_title.text = "Trips"
            main_toolbar_icon.setImageResource(R.drawable.trip_icon_white)
            add(R.id.fragment_container, ShowTripsFragment())
            commit()
        }

        navigation_menu.setNavigationItemSelectedListener { menuItem ->
            drawer_layout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.item_trips -> {
                    with(supportFragmentManager.beginTransaction()) {
                        main_toolbar_title.text = "Trips"
                        main_toolbar_icon.setImageResource(R.drawable.trip_icon_white)
                        replace(R.id.fragment_container, ShowTripsFragment())
                        commit()
                    }
                    true
                }
                R.id.item_spots -> {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    with(supportFragmentManager.beginTransaction()) {
                        main_toolbar_title.text = getString(R.string.app_name)
                        main_toolbar_icon.setImageResource(R.drawable.recorder_icon)
                        replace(R.id.fragment_container, MapFragment())
                        commit()
                    }
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