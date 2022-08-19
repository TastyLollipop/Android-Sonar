package com.lollipop.androidSonar.Objects

import android.os.Looper
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.lollipop.androidSonar.R
import com.lollipop.androidSonar.classes.Networking
import kotlin.math.roundToInt


object Utils {
    private var threadFinalizing: Boolean = false
    private var threadAddingProgress: Boolean = false
    private var threadAddingOpenPort: Boolean = false

    //Checks the variables before starting the operation
    fun checkVariables() : Boolean{
        Main.ip = getIPValue()
        Main.maxThreads = getMaxThreadsValue()
        Main.timeoutTime = getTimeoutTimeValue()

        val startingPortValue: Int = getPortRangeValues(0)
        val endingPortValue: Int = getPortRangeValues(1)
        val totalPorts: Int = endingPortValue - startingPortValue + 1
        var isWrong = false

        if (startingPortValue > endingPortValue) isWrong = true
        if (startingPortValue < 0 || endingPortValue < 0) isWrong = true
        if (totalPorts < Main.maxThreads) isWrong = true
        if (startingPortValue < 1) isWrong = true
        if (endingPortValue > 65535) isWrong = true

        if (isWrong)
        {
            Toast.makeText(Main.mainFragment, "Error! Check Parameters", Toast.LENGTH_LONG).show()
            return false
        }

        else
        {
            Toast.makeText(Main.mainFragment, "Process Started", Toast.LENGTH_LONG).show()

            UILogic.toggleUI()
            UILogic.addPortToOpenPortsBox(-1)

            Threading.networkThreadCount = 0

            Main.openPortsList.clear()
            Main.closedPortsList.clear()

            Main.currentProgressValue = 0
            Main.maxProgressValue = totalPorts
            UILogic.setProgressBarProgress(0)
            UILogic.setProgressBarMax(totalPorts)
            return true
        }
    }

    fun generateNetworkInstances(){
        val ip: String = Main.ip
        val startingPort: Int = getPortRangeValues(0)
        val endingPort: Int = getPortRangeValues(1)
        val totalPorts = endingPort - startingPort + 1

        var tempPortValue: Float = 0f
        var outOfBoundsPorts: Int = 0
        var extraStartingPort: Int = 0

        UILogic.setStatusLabel("Preparing")

        //Calculate ports that are out of bounds
        for (i in 0 until Main.maxThreads) {
            val numberOfPorts = (totalPorts.toFloat() / Main.maxThreads)
            tempPortValue += numberOfPorts % 1
        }

        outOfBoundsPorts = tempPortValue.roundToInt()

        for (i in 0 until Main.maxThreads)
        {
            Thread.sleep(100)

            val portSegment: Int = totalPorts / Main.maxThreads * i
            val startingPosition: Int = startingPort + portSegment + extraStartingPort

            var numberOfPorts: Float = totalPorts.toFloat() / Main.maxThreads

            //If there were extra ports, add one per thread until there's none left
            if (outOfBoundsPorts > 0) {
                numberOfPorts++
                extraStartingPort++
                outOfBoundsPorts--
            }

            val portsToCheck: Array<Int> = (startingPosition until startingPosition + numberOfPorts.toInt()).toList().toTypedArray()

            tempPortValue += numberOfPorts % 1

            val networkingInstance = Networking()
            networkingInstance.ip = ip
            networkingInstance.portArray = portsToCheck
            Threading.generateNetworkThread(networkingInstance)

            //Starts the timer and counter system on the first thread
            if (i == 0)
            {
                //Threading.generateThread(1)
                Threading.generateThread(2)
            }
        }

        while (Threading.networkThreadCount > 0) Thread.sleep(100)

        finalizeOperation()
    }

    //Start the counter system
    fun startCounter() {
        while (Threading.networkThreadCount > 0) {
            Thread.sleep(100)

            if (Main.currentProgressValue == 0) continue

            UILogic.setStatusLabel("${Main.currentProgressValue} / ${Main.maxProgressValue}")
            UILogic.setProgressBarProgress(Main.currentProgressValue)
        }
    }

    //Adds progress to the UI
    fun addProgressToUI(){
        while (threadAddingProgress) Thread.sleep(100)

        threadAddingProgress = true

        try { Main.currentProgressValue++ }
        catch (e: Exception) { }

        threadAddingProgress = false
    }

    //Add open ports to the box
    fun addPortToBox(value: Int){
        while (threadAddingOpenPort) Thread.sleep(100)

        threadAddingOpenPort = true

        try { UILogic.addPortToOpenPortsBox(value) }
        catch (e: Exception) { }

        threadAddingOpenPort = false
    }

    //Executes all the actions to close a certain thread
    fun finalizeNetworkThread(openPortsList: MutableList<Int>, closedPortsList: MutableList<Int>) {
        while (threadFinalizing) Thread.sleep(100)

        threadFinalizing = true

        Main.openPortsList.addAll(openPortsList)
        Main.closedPortsList.addAll(closedPortsList)

        Threading.networkThreadCount--
        threadFinalizing = false
    }

    //Finishes all operations and cleans the UI for next use
    private fun finalizeOperation() {
        UILogic.setStatusLabel("Idle")
        UILogic.setProgressBarProgress(Main.maxProgressValue)

        Looper.prepare()
        Toast.makeText(Main.mainFragment, "Open ports: ${Main.openPortsList.count()}", Toast.LENGTH_SHORT).show()

        val safeInvoke = Runnable { UILogic.toggleUI() }
        Main.mainHandler!!.post(safeInvoke)
    }

    //Get functions go below

    private fun getIPValue(): String {
        val editText = Main.homeFragment.requireView().findViewById(R.id.ipTextbox) as EditText
        return editText.text.toString()
    }

    private fun getPortRangeValues(id: Int): Int {
        var value: Int = 0

        when (id) {
            0 ->
            {
                val editText = Main.homeFragment.requireView().findViewById(R.id.startingPortTextbox) as EditText
                value  = Integer.parseInt(editText.text.toString())
            }

            1 ->
            {
                val editText = Main.homeFragment.requireView().findViewById(R.id.endingPortTextbox) as EditText
                value = Integer.parseInt(editText.text.toString())
            }
        }

        return value
    }

    private fun getMaxThreadsValue(): Int {
        val editText: Spinner = Main.optionsFragment.requireView().findViewById(R.id.threadCountSpinner) as Spinner
        return Integer.parseInt(editText.selectedItem.toString())
    }

    private fun getTimeoutTimeValue(): Int{
        val editText: Spinner = Main.optionsFragment.requireView().findViewById(R.id.timeoutTimeSpinner) as Spinner
        return Integer.parseInt(editText.selectedItem.toString())
    }
}