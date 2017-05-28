package com.twismart.wallpapershd.ui.main

import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView

import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        setUp()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

    }

    override fun setUp() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val tabLayout = findViewById(R.id.tab_layout) as TabLayout
        tabLayout.tabMode = TabLayout.MODE_FIXED

        val viewPager = findViewById(R.id.view_pager) as ViewPager
        viewPager.adapter = MainPagerAdapter(supportFragmentManager, this@MainActivity)
        tabLayout.setupWithViewPager(viewPager)

        val txt = findViewById(R.id.action0) as TextView
        txt.text = ""
    }
}
