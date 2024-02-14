/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    alias(libs.plugins.nowinandroid.kmp.library)
    alias(libs.plugins.sqldelight.gradle.plugin)
    alias(libs.plugins.ksp)
}

android {
    defaultConfig {
        testInstrumentationRunner =
            "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
    }
    namespace = "com.google.samples.apps.nowinandroid.core.database"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.core.model)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinInject.runtime)
                implementation(libs.sqldelight.coroutines.extensions)
                implementation(libs.sqldelight.primitive.adapters)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.sqldelight.android.driver)
            }
        }
        val nativeMain by getting {
            dependencies {
                implementation(libs.sqldelight.native.driver)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.sqldelight.sqlite.driver)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(libs.sqldelight.sqljs.driver)
                implementation(libs.sqldelight.webworker.driver)
                implementation(npm("sql.js", "1.6.2"))
                implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

sqldelight {
    databases {
        create("NiaDatabase") {
            packageName.set("com.google.samples.apps.nowinandroid.core.database")
            generateAsync.set(true)
        }
    }
}

dependencies {
    // KSP will eventually have better multiplatform support and we'll be able to simply have
    // `ksp libs.kotlinInject.compiler` in the dependencies block of each source set
    // https://github.com/google/ksp/pull/1021
    add("kspIosX64", libs.kotlinInject.compiler)
    add("kspIosArm64", libs.kotlinInject.compiler)
    add("kspIosSimulatorArm64", libs.kotlinInject.compiler)
}
