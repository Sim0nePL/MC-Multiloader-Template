plugins {
    id("net.neoforged.moddev")
}

val modId = project.findProperty("mod_id") as String // id from gradle.properties
val group = "${project.group}.neoforge" // change group -> group.neoforge

base {
    archivesName = "${rootProject.name}-neoforge-${version}"
}

neoForge {
    version = project.findProperty("neoforge_version") as String

    parchment {
        minecraftVersion = project.findProperty("parchment_minecraft_version") as String
        mappingsVersion = project.findProperty("parchment_mappings_version") as String
    }

    runs {
        create("client") {
            client()

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        create("server") {
            server()
            programArgument("--nogui")

            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }

        configureEach {
            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            systemProperty("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
            sourceSet(project(":common").sourceSets.main.get())
        }
    }
}

dependencies {
    // Example mod dependency with JEI
    // The JEI API is declared for compile time use, while the full JEI artifact is used at runtime
    // compileOnly "mezz.jei:jei-${mc_version}-common-api:${jei_version}"
    // compileOnly "mezz.jei:jei-${mc_version}-forge-api:${jei_version}"
    // runtimeOnly "mezz.jei:jei-${mc_version}-forge:${jei_version}"

    // Example mod dependency using a mod jar from ./libs with a flat dir repository
    // This maps to ./libs/coolmod-${mc_version}-${coolmod_version}.jar
    // The group id is ignored when searching -- in this case, it is "blank"
    // implementation "blank:coolmod-${mc_version}:${coolmod_version}"

    // Example mod dependency using a file as dependency
    // implementation files("libs/coolmod-${mc_version}-${coolmod_version}.jar")

    // For more info:
    // http://www.gradle.org/docs/current/userguide/artifact_dependencies_tutorial.html
    // http://www.gradle.org/docs/current/userguide/dependency_management.html

    // Implementation of :common module
    implementation(project(":common"))
}

//tasks.withType<JavaCompile>().configureEach {
//    sourceSets.main.get().java.srcDirs += project(":common").sourceSets.main.get().java.srcDirs
//    sourceSets.main.get().resources.srcDirs += project(":common").sourceSets.main.get().resources.srcDirs
//}

tasks.named<ProcessResources>("processResources") {
    // You can manually map properties using this map
//    var replaceProperties = [
//            minecraft_version      : minecraft_version,
//            minecraft_version_range: minecraft_version_range,
//            neo_version            : neo_version,
//            neo_version_range      : neo_version_range,
//            neo_loader_version     : neo_loader_version,
//            mod_id                 : mod_id,
//            mod_name               : rootProject.name,
//            mod_license            : mod_license,
//            mod_version            : mod_version,
//            mod_authors            : mod_authors,
//            mod_description        : mod_description
//    ]
    // Or automaticaly map properties from gradle.properties
    // And manually change/remap something if u need to
    var replaceProperties = project.properties.filter { it.value is String }

    inputs.properties(replaceProperties)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(replaceProperties)
    }

}