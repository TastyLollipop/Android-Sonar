package com.lollipop.androidSonar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.lollipop.androidSonar.Objects.Main
import com.lollipop.androidSonar.Objects.Threading
import com.lollipop.androidSonar.Objects.Utils
import com.lollipop.androidSonar.adapters.ViewPagerAdapter
import com.lollipop.androidSonar.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding : ActivityMainBinding

    lateinit var progressBar: ProgressBar
    lateinit var scanButton: Button
    lateinit var ipHostBox: EditText
    lateinit var startingPortBox: EditText
    lateinit var endingPortBox: EditText
    lateinit var threadCountSpinner: Spinner
    lateinit var timeoutTimeSpinner: Spinner
    lateinit var statusLabel: TextView
    lateinit var openPortsBox: TextView
    lateinit var timerLabel: TextView

    private val aboutURL: String = "https://github.com/TastyLollipop/Android-Sonar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        Main.mainFragment = this
        Main.mainHandler = Handler(this.mainLooper)
        setContentView(activityBinding.root)
        setupTabs()
    }

    //Setup all the tabs behaviour to be shown on screen
    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Main.homeFragment, "Main")
        adapter.addFragment(Main.optionsFragment, "Settings")

        activityBinding.viewPager.adapter = adapter
        activityBinding.tabLayout.setupWithViewPager(activityBinding.viewPager)
    }

    //Population functions go below

    fun populateSpinners(){
        val threadCount = resources.getStringArray(R.array.threadCount)
        val threadSpinner = findViewById<Spinner>(R.id.threadCountSpinner)
        val threadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, threadCount)
        threadSpinner.adapter = threadAdapter
        threadSpinner.setSelection(threadSpinner.count - 1)

        val timeoutCount = resources.getStringArray(R.array.timeoutTime)
        val timeoutSpinner = findViewById<Spinner>(R.id.timeoutTimeSpinner)
        val timeoutAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, timeoutCount)
        timeoutSpinner.adapter = timeoutAdapter
        timeoutSpinner.setSelection(0)
    }

    fun setupVariables(){
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        scanButton = findViewById<Button>(R.id.scanButton)
        ipHostBox = findViewById<EditText>(R.id.ipTextbox)
        startingPortBox = findViewById<EditText>(R.id.startingPortTextbox)
        endingPortBox = findViewById<EditText>(R.id.endingPortTextbox)
        threadCountSpinner = findViewById<Spinner>(R.id.threadCountSpinner)
        timeoutTimeSpinner = findViewById<Spinner>(R.id.timeoutTimeSpinner)
        statusLabel = findViewById<TextView>(R.id.statusLabel)
        timerLabel = findViewById<TextView>(R.id.timerLabel)

        openPortsBox = findViewById<TextView>(R.id.openPortsBox)
        openPortsBox.movementMethod = ScrollingMovementMethod()
    }

    fun setupButtons(){
        val scanButton = findViewById<Button>(R.id.scanButton)
        val aboutButton = findViewById<Button>(R.id.aboutButton)

        scanButton.setOnClickListener{
            if(Utils.checkVariables()) Threading.generateThread(0)
        }

        aboutButton.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(aboutURL)

            try { startActivity(openURL) }
            catch(e: Exception) { }
        }
    }
}