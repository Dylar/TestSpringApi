package de.bitb.testingApi.datasource.mock

import de.bitb.testingApi.datasource.ConfigDataSource
import de.bitb.testingApi.models.COLOR
import de.bitb.testingApi.models.ConfigData
import de.bitb.testingApi.models.ConfigType
import de.bitb.testingApi.models.Configuration
import org.springframework.stereotype.Repository

@Repository
class MockConfigDataSource : ConfigDataSource {

    private val config = Configuration(listOf(ConfigData(ConfigType.COLOR, COLOR.TEAL_GREEN)))

    override fun retrieveConfig(): Configuration = config
}