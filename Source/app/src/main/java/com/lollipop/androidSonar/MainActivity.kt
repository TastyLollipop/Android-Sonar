package com.lollipop.androidSonar

import android.R
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lollipop.androidSonar.adapters.ViewPagerAdapter
import com.lollipop.androidSonar.databinding.ActivityMainBinding
import com.lollipop.androidSonar.databinding.FragmentOptionsBinding
import com.lollipop.androidSonar.fragments.HomeFragment
import com.lollipop.androidSonar.fragments.OptionsFragment
import java.text.FieldPosition
import com.lollipop.androidSonar.R as Resources

class MainActivity : AppCompatActivity() {

    private lateinit var activityBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Main")
        adapter.addFragment(OptionsFragment(), "Options")

        activityBinding.viewPager.adapter = adapter
        activityBinding.tabLayout.setupWithViewPager(activityBinding.viewPager)
    }
}