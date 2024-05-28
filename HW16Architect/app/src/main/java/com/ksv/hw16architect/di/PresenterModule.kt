package com.ksv.hw16architect.di

import com.ksv.hw16architect.domain.GetUsefulActivityUseCase
import com.ksv.hw16architect.presentation.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModel(
        getUsefulActivityUseCase: GetUsefulActivityUseCase
    ): MainViewModel {
        return MainViewModel(getUsefulActivityUseCase)
    }
}