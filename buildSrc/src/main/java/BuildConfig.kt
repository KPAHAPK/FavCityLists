import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.favcitylist"
    const val compile_sdk = 31
    const val min_sdk = 24
    const val target_sdk = 31
    const val build_tool_version = "30.0.3"
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val data = ":data"
    const val utils = ":utils"
    const val domain = ":domain"
}

object Versions {

    //Design
    const val appcompat = "1.4.0"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.4"
    const val viewPager2 = "1.0.0"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.21"
    const val coroutinesCore = "1.4.3"
    const val coroutinesAndroid = "1.4.3"
    const val activityKTX = "1.5.1"
    const val fragmentKTX = "1.5.2"
    const val viewModelKTX = "2.5.1"
    const val liveDataKTX = "2.5.1"

    //Room
    const val sqlite_jdbc = "3.34.0"
    const val roomKtx = "2.4.2"
    const val runtime = "2.4.2"
    const val roomCompiler = "2.4.2"

    //Test
    const val jUnit = "4.13"
    const val runner = "1.2.0"
    const val espressoCore = "3.4.0"

    //Hilt
    const val hiltAndroid = "2.42"
    const val hiltCompiler = "2.42"
    const val hiltViewModel = "1.0.0"
    const val hiltCompiler2 = "1.0.0"

    //ViewBinding
    const val viewBinding = "1.5.6"

    //Cicerone
    const val cicerone = "7.1"

    //Gson
    const val gson = "2.9.1"


}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val activityKTX = "androidx.activity:activity-ktx:${Versions.activityKTX}"
    const val fragmentKTX = "androidx.fragment:fragment-ktx:${Versions.fragmentKTX}"
    const val viewModelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKTX}"
    const val liveDataKTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKTX}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Hilt {
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val hiltCompiler2 = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler2}"
}

object ViewBindingPropertyDelegate {
    const val viewBindingPropertyDelegate =
        "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBinding}"
}

object Cicerone{
    const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
}

object Gson{
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}
