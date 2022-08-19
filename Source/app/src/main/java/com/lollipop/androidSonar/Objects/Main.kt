package com.lollipop.androidSonar.Objects

import android.content.Context
import com.lollipop.androidSonar.MainActivity
import com.lollipop.androidSonar.fragments.HomeFragment
import com.lollipop.androidSonar.fragments.OptionsFragment

object Main {
    var mainFragment: MainActivity? = null
    val homeFragment: HomeFragment = HomeFragment()
    val optionsFragment: OptionsFragment = OptionsFragment()

    var ip: String = ""
    var maxThreads: Int = 0
    var timeoutTime: Int = 0

    fun populateSpinners() { mainFragment!!.PopulateSpinners() }
}