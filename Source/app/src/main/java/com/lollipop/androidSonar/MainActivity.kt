package com.lollipop.androidSonar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lollipop.androidSonar.Objects.Main
import com.lollipop.androidSonar.adapters.ViewPagerAdapter
import com.lollipop.androidSonar.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        Main.activityBinding = activityBinding
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

    //Button functions go below

    fun onScanButtonClick(view: View) {
        Toast.makeText(this, "Scan", Toast.LENGTH_SHORT).show()
    }

    fun onAboutButtonClick(view: View) {
        Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
    }
}