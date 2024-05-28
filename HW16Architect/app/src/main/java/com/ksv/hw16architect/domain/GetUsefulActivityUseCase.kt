package com.ksv.hw16architect.domain

import com.ksv.hw16architect.data.UsefulActivitiesRepository
import com.ksv.hw16architect.entity.UsefulActivity

class GetUsefulActivityUseCase(
    private val repository: UsefulActivitiesRepository
) {

    suspend fun execute() : UsefulActivity{
        return repository.getUsefulActivity()
    }
}