package com.shahryar.lib.kSocketIo.utils

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

internal class ApplicationContextInitializer : Initializer<Context> {
    override fun create(context: Context): Context {
        AppContext.setUp(context.applicationContext)
        return AppContext.get()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}

internal object AppContext {
    private lateinit var application: Application

    fun setUp(context: Context) {
        application = context as Application
    }

    fun get(): Context {
        if (!AppContext::application.isInitialized) {
            throw Exception("Context is not initialized.")
        }
        return application.applicationContext
    }
}