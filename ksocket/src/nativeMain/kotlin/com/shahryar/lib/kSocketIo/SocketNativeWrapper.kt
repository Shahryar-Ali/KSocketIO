package com.shahryar.lib.kSocketIo

interface SocketNativeWrapper {
    fun connect(url: String,nameSpace: String, config: Map<String, Any?>)
    fun disconnect()
    fun isConnected(): Boolean
    fun on(event: String, callback: ((String?) -> Unit))
    fun emit(event: String, payload: String)
    fun off(event: String)
    fun offAllEvents()
}

var socketNativeWrapper: SocketNativeWrapper? = null
