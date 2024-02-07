package com.example.util.extension

fun <T> T.or(statement: Boolean, another: T): T {
    return if(statement) this else another
}