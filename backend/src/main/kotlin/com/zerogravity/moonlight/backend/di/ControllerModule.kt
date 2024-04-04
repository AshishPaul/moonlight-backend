package com.zerogravity.moonlight.backend.di

import com.zerogravity.moonlight.backend.domain.modules.registration.RegistrationController
import com.zerogravity.moonlight.backend.domain.modules.registration.RegistrationControllerImpl
import com.zerogravity.moonlight.backend.domain.modules.services.ServiceController
import com.zerogravity.moonlight.backend.domain.modules.services.ServiceControllerImpl
import com.zerogravity.moonlight.backend.domain.modules.user.UserController
import com.zerogravity.moonlight.backend.domain.modules.user.UserControllerImpl
import org.koin.dsl.module

fun controllerModule() = module {
    single<UserController> { UserControllerImpl(get()) }
    single<RegistrationController> { RegistrationControllerImpl(get(), get(), get()) }
    single<ServiceController> { ServiceControllerImpl(get(), get()) }
}