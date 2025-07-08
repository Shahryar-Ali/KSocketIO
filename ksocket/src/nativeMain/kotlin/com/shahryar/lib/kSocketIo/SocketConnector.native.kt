package com.shahryar.lib.kSocketIo

import cocoapods.Socket.IO_Client_Swift.SocketIOClient
import cocoapods.Socket.IO_Client_Swift.SocketManager
import cocoapods.Socket.IO_Client_Swift.SocketManagerMeta
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.posix.socket


actual class SocketConnector actual constructor() {
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