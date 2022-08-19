package com.lollipop.androidSonar

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.lollipop.androidSonar.Objects.Main
import com.lollipop.androidSonar.Objects.Threading
import com.lollipop.androidSonar.Objects.Utils
import com.lollipop.androidSonar.adapters.ViewPagerAdapter
import com.lollipop.androidSonar.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        Main.mainFragment = this
        setContentView(activityBinding.root)
        setupTabs()
    }

    //Setup all the tabs behaviour to be shown on screen
    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Main.homeFragment, "Main")
        adapter.addFragment(Main.optionsFragment, "Options")

        activityBinding.viewPager.adapter = adapter
        activityBinding.tabLayout.setupWithViewPager(activityBinding.viewPager)
    }

    //Population functions go below

    fun PopulateSpinners(){
        val threadCount = resources.getStringArray(R.array.threadCount)
        val threadSpinner = findViewById<Spinner>(R.id.threadCountSpinner)
        val threadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, threadCount)
        threadSpinner.adapter = threadAdapter
        threadSpinner.setSelection(threadSpinner.count - 1)

        val timeoutCount = resources.getStringArray(R.array.timeoutTime)
        val timeoutSpinner = findViewById<Spinner>(R.id.timeoutTimeSpinner)
        val timeoutAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeoutCount)
        timeoutSpinner.adapter = timeoutAdapter
        timeoutSpinner.setSelection(0)
    }
}