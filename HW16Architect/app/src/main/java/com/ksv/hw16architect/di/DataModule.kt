package com.ksv.hw16architect.di

import com.ksv.hw16architect.data.UsefulActivitiesRepository
import com.ksv.hw16architect.domain.GetUsefulActivityUseCase
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideUsefulActivitiesRepository(): UsefulActivitiesRepository {
        return UsefulActivitiesRepository()
    }

}