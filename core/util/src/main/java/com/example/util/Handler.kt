package com.example.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler


object Handler {

    private const val COROUTINE_TAG = "coroutineHandled"

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(COROUTINE_TAG, "handled ${throwable.message} in $coroutineContext")
    }
}