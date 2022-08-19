package com.lollipop.androidSonar.Objects

import com.lollipop.androidSonar.classes.Networking
import kotlin.concurrent.thread

object Threading {
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
                    println("Started thread: ${Thread.currentThread().name}")
                }
            }

            2 ->
            {
                thread(start = true, name = "Count Thread") {
                    println("Started thread: ${Thread.currentThread().name}")
                }
            }
        }
    }

    fun generateNetworkThread(networkInstance: Networking){
        thread(start = true, name = "Scan Thread") {
            networkInstance.scanPort()
        }
    }
}