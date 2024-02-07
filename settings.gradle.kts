pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Planner-app"
include(":app")
include(":core:ui")
include(":core:components")
include(":core:domain")
include(":core:data")
include(":features:home")
include(":features:splash")
include(":features:add")
include(":core:util")
include(":features:settings")
