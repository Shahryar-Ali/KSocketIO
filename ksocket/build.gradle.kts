import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.vanniktech.mavenPublish)
}

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "kSocket"
            isStatic = true
        }
    }

    cocoapods {
        summary = "Kotlin Multiplatform wrapper for Socket.IO client"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "15.6"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "kSocket"
            isStatic = true
        }
        pod("Socket.IO-Client-Swift"){
            version = libs.versions.socketIOPod.get()
            moduleName = "SocketIO"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation (libs.socket.io.client)
            api(libs.androidx.startup.runtime)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            //implementation(compose.material3)
            //implementation(compose.materialIconsExtended)
            //implementation(compose.ui)
            implementation(compose.components.resources)
            //implementation(compose.components.uiToolingPreview)
        }
    }
}

android {
    namespace = "com.shahryar.lib.kSocketIo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }

        // For debug variant, we exclude Javadoc and sources to prevent conflicts
        singleVariant("debug") {
            // Exclude Javadoc and sources JARs for debug variant
        }
    }
}
mavenPublishing {
    coordinates(
        "io.github.shahryar-ali-km",
        "library",
        "0.0.1"
    )

    pom {
        name = "KSocketIO"
        description = "Library to implement Socket.IO for Kotlin Multiplatform project"
        inceptionYear = "2024"
        url = "https://github.com/kotlin/multiplatform-library-template/"
        licenses {
            license {
                name = "XXX"
                url = "YYY"
                distribution = "ZZZ"
            }
        }
        developers {
            developer {
                id = "XXX"
                name = "YYY"
                url = "ZZZ"
            }
        }
        scm {
            url = "XXX"
            connection = "YYY"
            developerConnection = "ZZZ"
        }
    }
}