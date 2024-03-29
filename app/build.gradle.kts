
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.vipaso"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vipaso"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.vipaso.HiltAndroidRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        //TODO update the authorization solution for the release mode
        buildConfigField("String", "token", "\"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\"")
        buildConfigField("String", "api_version", "\"2022-11-28\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.activity:activity-compose:1.8.0")

    /**
     * Compose UI
     */
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("io.coil-kt:coil-compose:2.4.0")

    /**
     * Paging library
     */
    val pagingVersion = "3.2.1"
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")





    /**
     * dagger
     */
    implementation("com.google.dagger:hilt-android:2.48.1")
    testImplementation("org.testng:testng:6.9.6")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48.1")

    /**
     * Retrofit
     * */
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    /**
     * Coroutines
     */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

    /**
     * test dependency
     */
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.2")

    debugImplementation("androidx.arch.core:core-testing:2.2.0")
    debugImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
    testImplementation("androidx.paging:paging-testing:$pagingVersion")


    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}