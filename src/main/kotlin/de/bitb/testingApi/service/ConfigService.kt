package de.bitb.testingApi.service

import de.bitb.testingApi.datasource.ConfigDataSource
import de.bitb.testingApi.models.ConfigData
import de.bitb.testingApi.models.ConfigType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ConfigService(@Autowired val dataSource: ConfigDataSource) {
    fun getConfig(type: ConfigType): ConfigData? = dataSource.retrieveConfig().values.firstOrNull { it.type == type }
}