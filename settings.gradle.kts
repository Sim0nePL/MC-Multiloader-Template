pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven ( "https://maven.neoforged.net/releases" )
    }
}

rootProject.name = "ExampleMod"

include("common")