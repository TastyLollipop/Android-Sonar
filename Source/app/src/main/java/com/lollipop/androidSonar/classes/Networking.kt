package com.lollipop.androidSonar.classes

import android.R.attr
import com.lollipop.androidSonar.Objects.Main
import com.lollipop.androidSonar.Objects.UILogic
import com.lollipop.androidSonar.Objects.Utils
import java.net.InetSocketAddress
import java.net.Socket


class Networking {
    var ip: String = ""
    var portArray: Array<Int>? = null

    fun scanPort(){
        var openPortsList: MutableList<Int> = mutableListOf()
        var closedPortsList: MutableList<Int> = mutableListOf()

        for (i in portArray!!)
        {
            val port: Int = i

            try
            {
                val socket = Socket()
                socket.connect(InetSocketAddress(ip, port), Main.timeoutTime)
                socket.close()

                openPortsList.add(port)
                Utils.addPortToBox(port)
            }

            catch(e: Exception) { closedPortsList.add(port) }

            Utils.addProgressToUI()
        }

        Utils.finalizeNetworkThread(openPortsList, closedPortsList)
    }
}