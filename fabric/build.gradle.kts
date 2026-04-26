plugins {
    id("fabric-loom")
}

group = "${project.group}.fabric" // change com.group -> com.group.neoforge
val modId = project.findProperty("id") as String // id from gradle.properties

base {
    archivesName = "${rootProject.name}-fabric"
}

repositories {
    maven( "https://maven.parchmentmc.org" )
    maven( "https://cursemaven.com" )
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${project.findProperty("minecraft_version") as String}")
    mappings( loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${project.findProperty("parchment_minecraft_version")}:${project.findProperty("parchment_mappings_version")}@zip")
    })
    modImplementation("net.fabricmc:fabric-loader:${project.findProperty("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.findProperty("fabric_api_version")}")

    modImplementation("curse.maven:modmenu-308702:7808443")

    implementation(project(":common"))
}

loom {
    runs.configureEach {
        ideConfigGenerated(true)
    }

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
            sourceSet(project(":common").sourceSets.main.get())
        }
    }
}

tasks.named<ProcessResources>("processResources") {
    // You can manually map properties using this map
    val replaceProperties = mapOf(
        "mod_id" to modId,
        "mod_name" to rootProject.name,
        "group" to project.group,
        "version" to project.findProperty("version"),
        "license" to project.findProperty("license"),
        "authors" to project.findProperty("authors"),
        "description" to project.findProperty("description"),
        "minecraft_version" to project.findProperty("minecraft_version"),
        "fabric_api_version" to project.findProperty("fabric_api_version"),
        "fabric_loader_version" to project.findProperty("fabric_loader_version"),
    )
    // Or automaticaly map properties from gradle.properties
    // And manually change/remap something if u need to
//    var replaceProperties = project.properties.filter { it.value is String }

    inputs.properties(replaceProperties)

    filesMatching(listOf("fabric.mod.json", "${modId}.fabric.mixins.json")) {
        expand(replaceProperties)
    }
}

tasks.named<Jar>("jar") {
    from(project(":common").sourceSets.main.get().output)

    // Copy the LICENSE file from rootProject to root of your .jar file
    from(rootProject.file("LICENSE")).into("/")

    // If both projects have the same file (like a license or icon),
    // this keeps the one from the :fabric project.
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}