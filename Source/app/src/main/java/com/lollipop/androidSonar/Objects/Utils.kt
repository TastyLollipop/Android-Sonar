package com.lollipop.androidSonar.Objects

import android.widget.Toast

object Utils {
    var threadFinalizing: Boolean = false
    var threadAddingProgress: Boolean = false
    var threadAddingOpenPort: Boolean = false

    //Checks the variables before starting the operation
    fun checkVariables() : Boolean{
        val startingPortValue: Int = Integer.parseInt(Main.homeFragment.homeBinding.startingPortTextbox.text.toString())
        val endingPortValue: Int = Integer.parseInt(Main.homeFragment.homeBinding.endingPortTextbox.text.toString())
        val totalPorts: Int = endingPortValue - startingPortValue
        var isWrong = false as Boolean

        if (startingPortValue > endingPortValue) isWrong = true;
        if (startingPortValue < 0 || endingPortValue < 0) isWrong = true;
        if (totalPorts < Main.maxThreads!!) isWrong = true;
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
            println(totalPorts)
            return true;
        }
    }
}