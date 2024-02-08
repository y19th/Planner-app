package com.example.util.extension

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

fun NavDestination.contains(destinationRoute: String): Boolean {
    return this.hierarchy.any {
        it.route == destinationRoute
    }
}