import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(27)
    defaultConfig {
        applicationId = "com.petersommerhoff.kudoofinal"
        minSdkVersion(19)
        targetSdkVersion(27)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    val kotlin_version: String by rootProject.extra
//    implementation(fileTree(dir: 'libs', include: ['*.jar']))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    implementation("com.android.support:appcompat-v7:27.1.1")
    implementation("com.android.support.constraint:constraint-layout:1.1.2")
    implementation("com.android.support:design:27.1.1")

    val room_version = "1.1.1"  // Adjust to newest version if you want to
    implementation("android.arch.persistence.room:runtime:$room_version")
    kapt("android.arch.persistence.room:compiler:$room_version")

    val coroutines_version = "0.24.0"
    // Use newest version if you want (might differ)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    val lifecycle_version = "1.1.1"
    implementation("android.arch.lifecycle:extensions:$lifecycle_version")
    kapt("android.arch.lifecycle:compiler:$lifecycle_version")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}

androidExtensions {
  configure(delegateClosureOf<AndroidExtensionsExtension> {  // Injects Groovy code
    isExperimental = true
  })
}
