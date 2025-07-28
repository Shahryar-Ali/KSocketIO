package com.shahryar.lib.kSocketIo

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class SocketConnector actual constructor() {
    init {
        KSocketUtil.initSocket()
    }
    actual fun connect(url: String, nameSpace: String, config: SocketConfig) {
        println("IosMain -> connect")
        val mConfig = mapOf(
            "headers" to config.headers,
            "connectParams" to config.connectParams,
            "reconnects" to true,
            "reconnectAttempts" to config.reconnectionAttempts,
            "reconnectWait" to config.reconnectionDelay,
            "forceNew" to config.forceNew,
            "timeout" to config.timeout
        )
        socketNativeWrapper?.connect(url,nameSpace, mConfig) ?: println("IosMain -> socketNativeWrapper is null")
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