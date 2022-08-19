package com.lollipop.androidSonar.Objects

import kotlin.concurrent.thread

object Threading {
    fun generateThread(threadID: Int){
        when (threadID) {
            0 ->
            {
                thread(start = true, name = "Utils Thread") {
                    println("running from thread(): ${Thread.currentThread()}")
                }
            }

            1 ->
            {
                thread(start = true, name = "Timer Thread") {
                    println("running from thread(): ${Thread.currentThread()}")
                }
            }

            2 ->
            {
                thread(start = true, name = "Count Thread") {
                    println("running from thread(): ${Thread.currentThread()}")
                }
            }
        }
    }

    fun generateNetworkThread(){
        thread(start = true, name = "Scan Thread") {
            println("running from thread(): ${Thread.currentThread()}")
        }
    }
}