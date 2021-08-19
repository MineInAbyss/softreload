plugins {
    idea
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.mineinabyss.conventions.publication")
}

repositories {
    mavenCentral()
    maven("https://repo.mineinabyss.com/releases")
    maven ("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT") // The Spigot API with no shadowing. Requires the OSS repo.
    implementation(project(":softreload-core"))
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand(mutableMapOf("plugin_version" to version))
        }
    }
}