package com.example.util.extension

fun <T> T.or(statement: Boolean, another: T): T {
    return if(statement) this else another
}

fun <T> Collection<T>.contains(other: Collection<T>): Boolean {
    if(this.isEmpty()) return true
    for(elem in this) {
        if(other.contains(elem)) return true
    }
    return false
}

fun <T> Collection<T>.containsEmpty(element: T): Boolean {
    if(this.isEmpty()) return true
    for(elem in this) {
        if(elem == element) return true
    }
    return false
}