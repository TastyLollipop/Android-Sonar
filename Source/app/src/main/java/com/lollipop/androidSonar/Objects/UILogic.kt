package com.lollipop.androidSonar.Objects

object UILogic {
    fun toggleUI(){
        Main.mainFragment!!.scanButton.isEnabled = !Main.mainFragment!!.scanButton.isEnabled
        Main.mainFragment!!.ipHostBox.isEnabled = !Main.mainFragment!!.ipHostBox.isEnabled
        Main.mainFragment!!.startingPortBox.isEnabled = !Main.mainFragment!!.startingPortBox.isEnabled
        Main.mainFragment!!.endingPortBox.isEnabled = !Main.mainFragment!!.endingPortBox.isEnabled
        Main.mainFragment!!.threadCountSpinner.isEnabled = !Main.mainFragment!!.threadCountSpinner.isEnabled
        Main.mainFragment!!.timeoutTimeSpinner.isEnabled = !Main.mainFragment!!.timeoutTimeSpinner.isEnabled
    }

    fun setStatusLabel(newLabel: String){
        Main.mainFragment!!.statusLabel.text = "Status: $newLabel"
    }

    fun setProgressBarProgress(value: Int){
        Main.mainFragment!!.progressBar.progress = value
    }

    fun setProgressBarMax(value: Int){
        Main.mainFragment!!.progressBar.max = value
    }

    fun addPortToOpenPortsBox(value: Int){
        if (value == -1) Main.mainFragment!!.openPortsBox.text = ""
        else
        {
            val previousText: String = Main.mainFragment!!.openPortsBox.text.toString()
            Main.mainFragment!!.openPortsBox.text = "$previousText - $value \n"
        }
    }
}