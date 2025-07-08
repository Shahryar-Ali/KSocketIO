package com.shahryar.lib.kSocketIo

interface SocketNativeWrapper {
    fun connect()
}

private var socketNativeWrapper: SocketNativeWrapper? = null

fun setSocketNativeWrapper(wrapper: SocketNativeWrapper){
    socketNativeWrapper = wrapper
}

fun getSocketNativeWrapper(): SocketNativeWrapper? = socketNativeWrapper