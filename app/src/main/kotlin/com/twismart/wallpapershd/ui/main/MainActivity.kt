package com.twismart.wallpapershd.ui.main

import android.support.design.widget.TabLayout
import android.os.Bundle

import com.twismart.wallpapershd.R
import com.twismart.wallpapershd.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        setUp()
    }

    override fun setUp() {
        setSupportActionBar(toolbar)

        tabLayout.tabMode = TabLayout.MODE_FIXED

        viewPager.adapter = MainPagerAdapter(supportFragmentManager, this@MainActivity)
        tabLayout.setupWithViewPager(viewPager)
    }
}