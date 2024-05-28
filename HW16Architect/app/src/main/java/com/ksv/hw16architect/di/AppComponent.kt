package com.ksv.hw16architect.di

import com.ksv.hw16architect.presentation.MainViewModelFactory
import dagger.Component

@Component()

interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory
}