package com.startup.ecoapp.data.repository

import com.startup.ecoapp.data.storage.FirstStartDataStorage
import com.startup.ecoapp.domain.repository.FirstStartRepository

class FirstStartRepositoryImpl(private val dataStorage: FirstStartDataStorage) : FirstStartRepository {

	override fun isFirstStart(): Boolean = dataStorage.isFirstStart()

}