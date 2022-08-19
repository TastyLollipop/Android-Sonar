package com.lollipop.androidSonar.classes

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.InetAddress
import java.net.Socket


class Networking {
    var ip: String = ""
    var portArray: Array<Int>? = null

    fun scanPort(){
        //println(portArray!!.count())
        var openPortsList: MutableList<Int> = mutableListOf(0)
        var closedPortsList: MutableList<Int> = mutableListOf(0)

        for (i in portArray!!)
        {
            val port: Int = i

            val client = Socket(ip, port)
            val output = PrintWriter(client.getOutputStream(), true)
            val input = BufferedReader(InputStreamReader(client.inputStream))

            println("Client sending [Hello]")
            output.println("Hello")
            println("Client receiving [${input.readLine()}]")
            client.close()

            println(port)

            try
            {

            }

            catch(e: Exception) { closedPortsList.add(port) }
        }
    }
}