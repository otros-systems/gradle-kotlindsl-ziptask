plugins {
    java
    `build-scan`

}
dependencies {
    compile("com.google.guava:guava:26.0-jre")
}
repositories {
    mavenCentral()
}

buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
}

//build-scan {
// server("http://a.bc/")
//}

version = "1.1"

tasks {
    // Use the native JUnit support of Gradle.
    "appdist"(Zip::class) {
        baseName = "AppDist"
        val baseDir = "$baseName-$version"

        from(project.configurations.runtime){
            into( "$baseDir/lib")
        }

        from(project.file("src/dist/otherFiles"))
                .from(project.file("src/dist/scriptsWin"))
                .from(file("src/dist/scriptsUnix")) {
                    into(baseDir)
                    fileMode = 755
                }

    }.dependsOn("jar")
}

