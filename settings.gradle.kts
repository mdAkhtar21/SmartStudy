pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        // Add the JitPack repository for plugins
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven("https://jitpack.io")
    }
}

rootProject.name = "SmartStudy"
include(":app")