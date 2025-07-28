package com.shahryar.lib.kSocketIo

import io.socket.client.IO.Options
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import java.net.URI

actual class SocketConnector actual constructor(){
    private var socket: Socket? = null
    private var manager: Manager? = null
    actual fun connect(url: String, nameSpace: String, config: SocketConfig) {
        val options = Options().apply {
            transports = arrayOf(WebSocket.NAME)
            config.reconnect?.let {
                reconnection = it
            }
            config.reconnectionDelay?.let {
                reconnectionDelay = it
            }
            config.reconnectionAttempts?.let {
                reconnectionAttempts = it
            }
            config.timeout?.let {
                timeout = it
            }
            config.forceNew?.let {
                forceNew = it
            }
            config.headers?.let {
                extraHeaders = it
            }
            config.connectParams?.let {params ->
                if (params.isNotEmpty()) {
                    query = params.map { (key, value) -> "$key=$value" }.joinToString("&")
                }
            }
        }
        manager = Manager(URI(url), options)
        socket = manager?.socket("/$nameSpace")
        socket?.connect()
    }

    actual fun disconnect() {
       socket?.disconnect()
        socket = null
        manager = null
    }

    actual fun emit(event: String, data: String) {
        socket?.emit(event, data)
    }

    actual fun on(event: String, callback: (String?) -> Unit) {
        socket?.on(event){
            callback(rawDataToString(it))
        }
    }

    actual fun off(event: String) {
        socket?.off(event)
    }

    actual fun isConnected(): Boolean? = socket?.connected()

    private fun rawDataToString(data: Array<out Any>): String {
        return data.lastOrNull()?.toString() ?: ""
    }
}