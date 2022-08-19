package com.lollipop.androidSonar.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.lollipop.androidSonar.Objects.Main
import com.lollipop.androidSonar.Objects.Threading
import com.lollipop.androidSonar.Objects.Utils
import com.lollipop.androidSonar.R
import com.lollipop.androidSonar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeBinding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons(){
        val scanButton = requireView().findViewById<Button>(R.id.scanButton)
        val aboutButton = requireView().findViewById<Button>(R.id.aboutButton)

        scanButton.setOnClickListener{
            if(Utils.checkVariables()) Threading.generateThread(0)
        }

        aboutButton.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.about_URL)))
            try { startActivity(browserIntent) }
            catch(e: ActivityNotFoundException) { }
        }
    }
}