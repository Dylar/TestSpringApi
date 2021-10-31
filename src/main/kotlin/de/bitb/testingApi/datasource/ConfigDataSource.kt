package de.bitb.testingApi.datasource

import de.bitb.testingApi.models.Configuration

interface ConfigDataSource {
    fun retrieveConfig() : Configuration
}