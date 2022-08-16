plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildToolsVersion = Config.build_tool_version

    defaultConfig {
        compileSdk
        applicationId = Config.application_id
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Kotlin.stdlib)
    implementation(Kotlin.core)
    implementation(Design.appcompat)
    implementation(Design.material)
    implementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)

    //Hilt
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)
    kapt(Hilt.hiltCompiler2)

    // Room
    kapt(Room.sqlite_jdbc)
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.room_ktx)

    //Coroutines
    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    //ViewBinding
    implementation(ViewBindingPropertyDelegate.viewBindingPropertyDelegate)

    //Cicerone
    implementation(Cicerone.cicerone)

    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.utils))
}