package com.lollipop.androidSonar.Objects

import android.content.Context
import com.lollipop.androidSonar.MainActivity
import com.lollipop.androidSonar.databinding.ActivityMainBinding
import com.lollipop.androidSonar.databinding.FragmentHomeBinding
import com.lollipop.androidSonar.databinding.FragmentOptionsBinding
import com.lollipop.androidSonar.fragments.HomeFragment
import com.lollipop.androidSonar.fragments.OptionsFragment

object Main {
    val homeFragment: HomeFragment = HomeFragment()
    val optionsFragment: OptionsFragment = OptionsFragment()

    var activityBinding: ActivityMainBinding? = null
    var homeBinding: FragmentHomeBinding? = null
    var optionsBinding: FragmentOptionsBinding? = null

    fun tryMe() { println("Trying") }

    fun printInitializedFragments(fragment:String) { println(fragment) }
}