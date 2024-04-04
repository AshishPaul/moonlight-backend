plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
    application
}

group = "com.zerogravity.moonlight.backend"
version = "1.0.0"

application {
    mainClass.set("com.zerogravity.moonlight.backend.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.metrics.micrometer)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.logback)
    implementation(libs.koin.ktor)
    implementation(libs.bcrypt)

    implementation(libs.google.api.client)
    implementation(libs.mongodb.driver)
    implementation(libs.mongodb.bson.kotlinx)

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
}

ktor {
    docker {
        externalRegistry.set(
            io.ktor.plugin.features.DockerImageRegistry.dockerHub(
                appName = provider { project.name },
                username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
            )
        )

        jib {
            from {
                image = "openjdk:17-jdk-alpine"
            }
            to {
                image = "{zerogravitydesk/moonlight}"
                tags = setOf("${project.version}")
            }
        }
    }
}