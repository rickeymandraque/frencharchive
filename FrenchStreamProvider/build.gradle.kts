// use an integer for version numbers
version = 3
dependencies {
    implementation("androidx.core:core:1.7.0")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
}

android {
    // Configure only for each module that uses Java 8
    // language features (either in its source code or
    // through dependencies).
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    // For Kotlin projects
    kotlinOptions {
        jvmTarget = "1.8"
    }
    defaultConfig {
        minSdkVersion(26)
    }
}

cloudstream {
    language = "fr"
    // All of these properties are optional, you can safely remove them

     description = "FRENCH STREAM en plus d'être un site efficace et plaisant dispose d'un contenu visuel diversifié"
     authors = listOf("Sarlay", "Eddy976", "AirbnbEcoPlus")

    /**
     * Status int as the following:
     * 0: Down
     * 1: Ok
     * 2: Slow
     * 3: Beta only
     * */
    status = 1 // will be 3 if unspecified
    tvTypes = listOf(
        "TvSeries",
        "Movie",
    )

    iconUrl = "https://www.google.com/s2/favicons?domain=french-stream.ac&sz=%size%"
}
