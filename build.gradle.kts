plugins {
    java
}
dependencies {
    compile("com.google.guava:guava:26.0-jre")
}
repositories {
    mavenCentral()
}

version = "1.1"

tasks {
    // Use the native JUnit support of Gradle.
    "appdist"(Zip::class) {
        baseName = "AppDist"
        val baseDir = "$baseName-$version"

        into("lib").from(project.configurations.runtime)

        into(baseDir)
                .from(project.file("src/dist/otherFiles"))
                .from(project.file("src/dist/scriptsWin"))
                .from(file("src/dist/scriptsUnix")) { fileMode = 755 }
        
    }.dependsOn("jar")
}

