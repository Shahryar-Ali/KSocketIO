package com.shahryar.lib.kSocketIo

expect class SocketConnector() {
    fun connect(url: String, config: SocketConfig)
    fun disconnect()
    fun emit(event: String, data: Any)
    fun on(event: String, callback: (Any) -> Unit)
    fun off(event: String)
}

data class SocketConfig(
    val reconnect: Boolean = true,
    val reconnectionAttempts: Int = 5,
    val reconnectionDelay: Long = 1000,
    val auth: Map<String, String> = emptyMap()
)