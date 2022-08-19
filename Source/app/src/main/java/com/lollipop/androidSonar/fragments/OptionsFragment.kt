package com.lollipop.androidSonar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.lollipop.androidSonar.Objects.Main
import com.lollipop.androidSonar.R
import com.lollipop.androidSonar.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {
    private lateinit var optionsBinding : FragmentOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        optionsBinding = FragmentOptionsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Main.populateSpinners()
    }
}