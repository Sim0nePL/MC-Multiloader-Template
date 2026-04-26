pluginManagement {
    repositories {
        mavenLocal()
        maven ( "https://maven.neoforged.net/releases" )
        maven ( "https://maven.fabricmc.net/" )
        gradlePluginPortal()
    }
}

rootProject.name = "ExampleMod"

include("common")
include("neoforge")
include("fabric")