package com.shahryar.lib.kSocketIo

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class SocketConnector actual constructor() {
    private var socketIOWrapper: SocketIOWrapper? = null
    actual fun connect(url: String, nameSpace: String, config: SocketConfig) {
        println("IosMain -> connect")
        val mConfig: Map<Any?, *> = mapOf(
            "headers" to config.headers,
            "connectParams" to config.connectParams,
            "reconnects" to true,
            "reconnectAttempts" to config.reconnectionAttempts,
            "reconnectWait" to config.reconnectionDelay,
            "forceNew" to config.forceNew,
            "timeout" to config.timeout
        )
        socketIOWrapper = SocketIOWrapper()
        socketIOWrapper?.connect(url,nameSpace, mConfig) ?: println("IosMain -> socketNativeWrapper is null")
    }

    actual fun disconnect() {
        socketIOWrapper?.disconnect()
    }

    actual fun emit(event: String, data: String) {
        socketIOWrapper?.emit(event,data)
    }

    actual fun on(event: String, callback: (String?) -> Unit) {
        socketIOWrapper?.on(event,callback)
    }

    actual fun off(event: String) {
        socketIOWrapper?.off(event)
    }

    actual fun isConnected(): Boolean? = socketIOWrapper?.isConnected()
}