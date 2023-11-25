// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

}

val appcompat_version = "1.5.1"
val constraintlayout_version = "2.1.4"
val core_ktx_version = "1.9.0"
var kotlin_version = "1.7.10"
val material_version = "1.7.0-alpha2"
val nav_version = "2.5.2"


buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.2")
    }
}

