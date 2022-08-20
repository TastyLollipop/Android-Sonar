package com.lollipop.androidSonar.Objects

import com.lollipop.androidSonar.classes.Networking
import kotlin.concurrent.thread

object Threading {
    var networkThreadCount: Int = 0

    fun generateThread(threadID: Int){
        when (threadID) {
            0 ->
            {
                thread(start = true, name = "Utils Thread") {
                    Utils.generateNetworkInstances()
                }
            }

            1 ->
            {
                thread(start = true, name = "Timer Thread") {
                    Utils.startTimer()
                }
            }

            2 ->
            {
                thread(start = true, name = "Count Thread") {
                    Utils.startCounter()
                }
            }
        }
    }

    fun generateNetworkThread(networkInstance: Networking){
        networkThreadCount++

        thread(start = true, name = "Scan Thread") {
            networkInstance.scanPort()
        }
    }
}