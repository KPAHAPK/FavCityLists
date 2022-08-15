plugins {
    id("com.android.library")
    kotlin("android")
}


dependencies {
    implementation(project(Modules.utils))


    implementation(Kotlin.stdlib)
    implementation(Kotlin.core)
    implementation(Design.appcompat)
    implementation(Design.material)
    implementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)
}