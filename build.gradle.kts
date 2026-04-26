plugins {
    id("java")
    id("net.neoforged.moddev") version "2.0.141" apply false
    id("fabric-loom") version "1.15.1" apply false
}

// Set java version
java.toolchain.languageVersion = JavaLanguageVersion.of(21)

subprojects {
    // Apply plugins
    apply(plugin = "java")

    // Set encoding to UTF-8
    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    repositories {
        // SpongePowered mixins support in :common
        maven("https://repo.spongepowered.org/repository/maven-public/")
    }
}

