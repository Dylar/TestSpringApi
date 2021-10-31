package de.bitb.testingApi.datasource

import de.bitb.testingApi.models.ConfigData
import de.bitb.testingApi.models.Configuration

interface ConfigDataSource {
    fun retrieveConfig() : Configuration

    fun setConfig(config: ConfigData): ConfigData
}