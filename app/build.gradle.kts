plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)


}

android {
    namespace = "com.behnamuix.appointment"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.behnamuix.appointment"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.lint)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.navigation.compose)
    //Datastore
// Preferences DataStore (SharedPreferences like APIs)
    implementation(libs.androidx.datastore.preferences)
    // Alternatively - without an Android dependency.
    implementation(libs.androidx.datastore.preferences.core)
    // Typed DataStore for custom data objects (for example, using Proto or JSON).
    implementation(libs.androidx.datastore)
    // Alternatively - without an Android dependency.
    implementation(libs.androidx.datastore.core)


    //ROOM ORM
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    //KSP
    ksp(libs.androidx.room.compiler)


    // KTOR
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)


    //Dagger Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //Koin
    implementation(libs.koin)
    implementation(libs.koin.compose)

    //Koil
    implementation(libs.coil.compose)
    implementation(libs.coil.http)



}