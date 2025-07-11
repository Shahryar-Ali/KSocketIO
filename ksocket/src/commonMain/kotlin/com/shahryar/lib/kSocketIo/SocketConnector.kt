package com.shahryar.lib.kSocketIo

expect class SocketConnector() {
    fun connect(url: String,nameSpace: String, config: SocketConfig)
    fun disconnect()
    fun isConnected(): Boolean?
    fun emit(event: String, data: String)
    fun on(event: String, callback: (String?) -> Unit)
    fun off(event: String)
}

data class SocketConfig(
    val reconnect: Boolean? = null,
    val reconnectionAttempts: Int? = null,
    val reconnectionDelay: Long? = null,
    val timeout: Long? = null,
    val forceNew: Boolean? = null,
    val connectParams: Map<String, String>? = null,
    val headers: Map<String, List<String>?>? = null
)