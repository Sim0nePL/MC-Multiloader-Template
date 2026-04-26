plugins {
    id("java")
    id("net.neoforged.moddev") version "2.0.141" apply false
}

subprojects {
    // Apply plugins
    apply(plugin = "java")

    // Set java version
    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    // Set encoding to UTF-8
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    repositories {
        maven("https://repo.spongepowered.org/repository/maven-public/")
    }
}

