plugins {
    id("com.android.library")
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


    implementation(project(Modules.data))
    implementation(project(Modules.utils))

}