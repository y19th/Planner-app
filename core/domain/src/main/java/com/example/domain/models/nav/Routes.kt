package com.example.domain.models.nav

enum class Routes {
    HOME,ADD,SETTINGS,SPLASH,PIN;

    fun routeWith(addition: String): String {
        return "${this.name}/$addition"
    }
}

