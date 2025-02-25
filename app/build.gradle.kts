plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

android {
    compileSdkVersion 34
    defaultConfig {
        applicationId "br.com.fiap.clarimail"
        minSdkVersion 21
        targetSdkVersion 34
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
                targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.4.0")
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation ("com.google.android.material:material:1.5.0")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}
