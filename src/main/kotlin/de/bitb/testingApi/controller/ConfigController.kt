package de.bitb.testingApi.controller

import de.bitb.testingApi.models.ConfigData
import de.bitb.testingApi.models.ConfigType
import de.bitb.testingApi.service.ConfigService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/config")
class ConfigController(private val service: ConfigService) {

    @GetMapping("/{name}")
    fun getConfig(@PathVariable name: String): ConfigData? {
        val type = ConfigType.valueOf(name)
        return service.getConfig(type)
    }
}