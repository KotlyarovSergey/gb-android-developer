package com.ksv.hw16architect.di

import com.ksv.hw16architect.presentation.MainViewModel
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)

interface AppComponent {
    fun mainViewModel(): MainViewModel
}