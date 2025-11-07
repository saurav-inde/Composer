plugins {

    // hilt and dagger
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)


}

android {
    namespace = "com.example.compose"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.compose"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
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
    kotlinOptions {
        jvmTarget = "11"
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
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    ksp(libs.androidx.room.compiler){
        exclude( "com.intellij", "annotations")
    }

    implementation (libs.androidx.material.icons.core)
    implementation (libs.androidx.material.icons.extended)

    implementation(libs.androidx.room.common.jvm)
    implementation(libs.identity.jvm)
    implementation(libs.androidx.room.runtime.android)
//    implementation(libs.androidx.navigation.runtime.android)
//    implementation(libs.androidx.navigation.compose.jvmstubs)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Hilt and Dagger
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    // ✅ AndroidX Hilt extensions
    implementation(libs.androidx.hilt.navigation.compose)  // for Jetpack Compose
//    implementation(libs.androidx.hilt.work)               // if you use WorkManager + Hilt

    ksp("androidx.hilt:hilt-compiler:1.3.0"){
        exclude( "com.intellij", "annotations")
    }                     // required for AndroidX Hilt annotations

    // navigation in compose

    implementation(libs.androidx.navigation.compose)
}