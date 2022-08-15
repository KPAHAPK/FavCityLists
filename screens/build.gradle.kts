plugins {
    id("com.android.application")
    kotlin("android")
}


dependencies {

    implementation(Kotlin.stdlib)
    implementation(Kotlin.core)
    implementation(Design.appcompat)
    implementation(Design.material)
    implementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)
}