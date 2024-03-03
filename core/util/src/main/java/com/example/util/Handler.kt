package com.example.util

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext


object Handler {

    private const val COROUTINE_TAG = "coroutineHandled"

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(COROUTINE_TAG, "handled ${throwable.message} in $coroutineContext")
    }

    fun context(dispatcher: CoroutineDispatcher): CoroutineContext {
        return dispatcher + coroutineExceptionHandler
    }
}