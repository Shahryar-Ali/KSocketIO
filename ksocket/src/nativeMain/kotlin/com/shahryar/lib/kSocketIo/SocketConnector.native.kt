package com.shahryar.lib.kSocketIo


actual class SocketConnector actual constructor() {
    actual fun connect(url: String, nameSpace: String, config: SocketConfig) {
        val mConfig = mapOf(
            "headers" to config.headers,
            "connectParams" to config.connectParams,
            "reconnects" to true,
            "reconnectAttempts" to config.reconnectionAttempts,
            "reconnectWait" to config.reconnectionDelay,
            "forceNew" to config.forceNew,
            "timeout" to config.timeout
        )
        socketNativeWrapper?.connect(url,nameSpace, mConfig)
    }

    actual fun disconnect() {
        socketNativeWrapper?.disconnect()
    }

    actual fun emit(event: String, data: String) {
        socketNativeWrapper?.emit(event,data)
    }

    actual fun on(event: String, callback: (String?) -> Unit) {
        socketNativeWrapper?.on(event,callback)
    }

    actual fun off(event: String) {
        socketNativeWrapper?.off(event)
    }

    actual fun isConnected(): Boolean? = socketNativeWrapper?.isConnected()
}