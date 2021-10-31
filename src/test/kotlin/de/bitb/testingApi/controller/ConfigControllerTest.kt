package de.bitb.testingApi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import de.bitb.testingApi.getAndParse
import de.bitb.testingApi.models.COLORS
import de.bitb.testingApi.models.ConfigData
import de.bitb.testingApi.models.ConfigType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class ConfigControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val mapper: ObjectMapper,
) {

    @Nested
    @DisplayName("GET Config")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetConfig {

        @Test
        fun `return color config`() {
            val type = ConfigType.COLOR.name
            val response: ConfigData = mockMvc.getAndParse("$CONFIG_URL/$type", ConfigData::class.java)

            assertThat(response.type).isEqualTo(ConfigType.COLOR)
            assertThat(COLORS.valueOf(response.value as String)).isEqualTo(COLORS.TEAL_GREEN)
        }

        @Test
        fun `return color NOT FOUND`() {
            val type = ConfigType.UNKNOWN.name
            mockMvc.get("$CONFIG_URL/$type")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST Config")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class SetConfig {

        @Test
        fun `set color config`() {
            //given
            val newConfig = ConfigData(ConfigType.COLOR, COLORS.TEAL_BLUE)

            //when
            val call = mockMvc.post(CONFIG_URL) {
                contentType = MediaType.APPLICATION_JSON
                content = mapper.writeValueAsString(newConfig)
            }

            //then
            val response = call
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
                .andReturn().response.contentAsByteArray
                .let { mapper.readValue(it, ConfigData::class.java) }

            assertThat(response.type).isEqualTo(newConfig.type)
            assertThat(response.value).isEqualTo((newConfig.value as COLORS).name)
        }

        @Test
        fun `set config return BAD REQUEST when value type is not valid type`() {
            //given
            val newConfig = ConfigData(ConfigType.COLOR, "Tja das is keine Color")

            //when //then
            mockMvc.post(CONFIG_URL) {
                contentType = MediaType.APPLICATION_JSON
                content = mapper.writeValueAsString(newConfig)
            }.andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }

    }

}