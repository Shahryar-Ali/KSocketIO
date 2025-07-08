package com.shahryar.lib.kSocketIo

actual class SocketConnector actual constructor(){
    actual fun connect(url: String, config: SocketConfig) {
    }

    actual fun disconnect() {
    }

    actual fun emit(event: String, data: Any) {
    }

    actual fun on(event: String, callback: (Any) -> Unit) {
    }

    actual fun off(event: String) {
    }
}