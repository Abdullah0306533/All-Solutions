plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.projects.solutionpack"
    compileSdk = 34
    buildFeatures{
        viewBinding=true
    }

    defaultConfig {
        applicationId = "com.projects.solutionpack"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures{
        dataBinding=true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


    //Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    //google analytics
    implementation("com.google.firebase:firebase-analytics")
    //FireBase Authentication
    implementation("com.google.firebase:firebase-auth")
    //material designs
    implementation ("com.google.android.material:material:1.9.0")


    /**
     * for network management
     */
    val lifecycle_version = "2.8.4"

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")

    //retrofit & Gson
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.google.code.gson:gson:2.11.0")

    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.11.0")


}