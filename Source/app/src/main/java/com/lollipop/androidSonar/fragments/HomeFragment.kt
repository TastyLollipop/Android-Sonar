package com.lollipop.androidSonar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lollipop.androidSonar.Objects.Main
import com.lollipop.androidSonar.R
import com.lollipop.androidSonar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeBinding : FragmentHomeBinding
    var xd: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
        Main.homeBinding = homeBinding
        xd = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Main.printInitializedFragments("HomeFragment")
    }
}