plugins {
    id("net.neoforged.moddev")
}

neoForge {
    neoFormVersion = project.findProperty("neoform_version") as String

    parchment {
        minecraftVersion = project.findProperty("parchment_minecraft_version") as String
        mappingsVersion = project.findProperty("parchment_mappings_version") as String
    }
}

dependencies {
    compileOnly("org.spongepowered:mixin:0.8.7")
    annotationProcessor("org.spongepowered:mixin:0.8.7:processor")
}