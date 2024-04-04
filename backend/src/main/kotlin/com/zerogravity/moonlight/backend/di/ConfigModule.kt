package com.zerogravity.moonlight.backend.di

import com.zerogravity.moonlight.backend.domain.config.Config
import com.zerogravity.moonlight.backend.domain.modules.auth.JwtTokenProvider
import com.zerogravity.moonlight.backend.domain.modules.auth.TokenProvider
import com.zerogravity.moonlight.backend.domain.util.BCryptPasswordManager
import com.zerogravity.moonlight.backend.domain.util.PasswordManager
import org.koin.dsl.module

fun configModule(config: Config) = module {
    single { config }
    single<PasswordManager> { BCryptPasswordManager() }
    single<TokenProvider> { JwtTokenProvider() }
//    single { PrometheusMeterRegistry(PrometheusConfig.DEFAULT) }
}