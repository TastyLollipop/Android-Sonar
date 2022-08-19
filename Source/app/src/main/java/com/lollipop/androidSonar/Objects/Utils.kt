package com.lollipop.androidSonar.Objects

import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.lollipop.androidSonar.R
import com.lollipop.androidSonar.classes.Networking
import kotlin.math.roundToInt


object Utils {
    var threadFinalizing: Boolean = false
    var threadAddingProgress: Boolean = false
    var threadAddingOpenPort: Boolean = false

    //Checks the variables before starting the operation
    fun checkVariables() : Boolean{
        Main.ip = getIPValue()
        Main.maxThreads = getMaxThreadsValue()
        Main.timeoutTime = getTimeoutTimeValue()

        val startingPortValue: Int = getPortRangeValues(0)
        val endingPortValue: Int = getPortRangeValues(1)
        val totalPorts: Int = endingPortValue - startingPortValue + 1
        var isWrong = false as Boolean

        if (startingPortValue > endingPortValue) isWrong = true;
        if (startingPortValue < 0 || endingPortValue < 0) isWrong = true;
        if (totalPorts < Main.maxThreads) isWrong = true;
        if (startingPortValue < 1) isWrong = true;
        if (endingPortValue > 65535) isWrong = true;

        if (isWrong)
        {
            Toast.makeText(Main.mainFragment, "Error!", Toast.LENGTH_SHORT).show()
            return false;
        }

        else
        {
            Toast.makeText(Main.mainFragment, "Success!", Toast.LENGTH_SHORT).show()
            return true;
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
                //Threading.generateThread(1);
                //Threading.generateThread(2);
            }
        }
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