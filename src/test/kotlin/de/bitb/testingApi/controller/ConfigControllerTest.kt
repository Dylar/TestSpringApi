package de.bitb.testingApi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import de.bitb.testingApi.models.COLOR
import de.bitb.testingApi.models.ConfigData
import de.bitb.testingApi.models.ConfigType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@SpringBootTest
@AutoConfigureMockMvc
internal class ConfigControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    val objMapper = ObjectMapper().registerKotlinModule()

    @Test
    fun `return color config`() {
        val type = ConfigType.COLOR.name
        val response: ConfigData = mockMvc.get("/api/v1/config/$type")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn().response.contentAsByteArray
            .let { objMapper.readValue(it, ConfigData::class.java) }

        assertThat(response.type).isEqualTo(ConfigType.COLOR)
        assertThat(COLOR.valueOf(response.value as String)).isEqualTo(COLOR.TEAL_GREEN)
    }
}