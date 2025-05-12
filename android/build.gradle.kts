plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.plugin.fcm"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.google.firebase:firebase-installations:18.0.0")
    implementation("com.google.firebase:firebase-messaging:24.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation(project(":tauri-android"))
}

val generatedDir = File(rootProject.layout.buildDirectory.get().asFile, "generated/kotlin")

tasks.register("generateConstants") {
    doLast {
        val applicationId = System.getenv("FCM_APPLICATION_ID")?.takeIf { it.isNotBlank() }
        val projectId = System.getenv("FCM_PROJECT_ID")?.takeIf { it.isNotBlank() }
        val apiKey = System.getenv("FCM_API_KEY")?.takeIf { it.isNotBlank() }

        if (applicationId == null || projectId == null || apiKey == null) {
            throw GradleException("Missing required environment variables: FCM_APPLICATION_ID, FCM_PROJECT_ID, FCM_API_KEY")
        }

        generatedDir.mkdirs()
        File(generatedDir, "Constants.kt").writeText("""
            package com.plugin.fcm
            const val FCM_APPLICATION_ID = "$applicationId"
            const val FCM_PROJECT_ID = "$projectId"
            const val FCM_API_KEY = "$apiKey"
        """.trimIndent())
    }
}

afterEvaluate {
    tasks.named("compileDebugKotlin") {
        dependsOn("generateConstants")
    }
    tasks.named("compileReleaseKotlin") {
        dependsOn("generateConstants")
    }
}

android.sourceSets {
    getByName("main").java.srcDirs(generatedDir)
}
