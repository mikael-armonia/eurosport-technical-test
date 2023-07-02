package com.mikaelarmonia.ui.di

import com.mikaelarmonia.ui.screen.repository.DestinationRepository
import com.mikaelarmonia.ui.screen.repository.NavigatorRepository
import com.mikaelarmonia.ui.screen.repository.ScreenRepository
import org.koin.dsl.module

val uiModule = module {
    factory<DestinationRepository> { get<ScreenRepository>() }
    factory<NavigatorRepository> { get<ScreenRepository>() }

    single { ScreenRepository() }
}