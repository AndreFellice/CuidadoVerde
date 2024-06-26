plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.plantcuidadoverdeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.plantcuidadoverdeapp"
        minSdk = 27
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation(libs.androidx.ui.desktop)

    // testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // ViewModel  Lifecycle LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.databinding:databinding-runtime:8.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Retrofit with Scalar Converter
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.13.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

    // ROOM
    implementation("androidx.room:room-runtime:2.6.0")
    testImplementation("androidx.room:room-testing:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0")

    // recyclerview
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")

    // Optional -- Mockito framework
    testImplementation("org.mockito:mockito-core:3.12.4")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    // OkHttp MockWebServer (for testing)
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
    configurations.all {
        exclude(module = "okhttp-ws")
    }

    // For Robolectric tests
    testImplementation("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin
    kaptTest("com.google.dagger:hilt-android-compiler:2.44")
    // ...with Java
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.44")

    // For instrumented tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")
    // ...with Java
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.44")
}