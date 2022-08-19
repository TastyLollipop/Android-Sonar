package com.lollipop.androidSonar.Objects

import android.content.Context
import com.lollipop.androidSonar.MainActivity
import com.lollipop.androidSonar.fragments.HomeFragment
import com.lollipop.androidSonar.fragments.OptionsFragment

object Main {
    var mainFragment: MainActivity? = null
    val homeFragment: HomeFragment = HomeFragment()
    val optionsFragment: OptionsFragment = OptionsFragment()

    var maxThreads: Int? = 64

    fun populateSpinners() { mainFragment!!.PopulateSpinners() }

    fun setupButtons() { mainFragment!!.setupButtons() }
}