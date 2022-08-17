plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}


dependencies {
    // Room
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.room_ktx)

    //Hilt
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)
    kapt(Hilt.hiltCompiler2)

    //Cicerone
    implementation(Cicerone.cicerone)

    //Gson
    implementation(Gson.gson)

    //Coroutines
    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    implementation(Kotlin.stdlib)
    implementation(Kotlin.core)
    implementation(Design.appcompat)
    implementation(Design.material)
    implementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)

    implementation(project(Modules.utils))
    implementation(project(Modules.domain))

}