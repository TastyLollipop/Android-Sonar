package com.lollipop.androidSonar.Objects

import android.os.Handler
import com.lollipop.androidSonar.MainActivity
import com.lollipop.androidSonar.fragments.HomeFragment
import com.lollipop.androidSonar.fragments.OptionsFragment

object Main {
    var mainHandler: Handler? = null
    var mainFragment: MainActivity? = null
    val homeFragment: HomeFragment = HomeFragment()
    val optionsFragment: OptionsFragment = OptionsFragment()

    var openPortsList: MutableList<Int> = mutableListOf()
    var closedPortsList: MutableList<Int> = mutableListOf()

    var ip: String = ""
    var maxThreads: Int = 0
    var timeoutTime: Int = 0
    var currentProgressValue: Int = 0
    var maxProgressValue: Int = 0

    fun setupVariables(){
        mainFragment!!.setupVariables()
        mainFragment!!.setupButtons()
        mainFragment!!.populateSpinners()
    }
}