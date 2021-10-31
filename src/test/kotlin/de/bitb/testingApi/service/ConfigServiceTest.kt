package de.bitb.testingApi.service

import de.bitb.testingApi.datasource.ConfigDataSource
import de.bitb.testingApi.models.ConfigType
import de.bitb.testingApi.models.Configuration
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ConfigServiceTest {

    private val dataSource: ConfigDataSource = mockk(relaxed = true);
    private val configService: ConfigService = ConfigService(dataSource)

    @Test
    fun `get config from service`() {
        //when
        configService.getConfig(ConfigType.COLOR);
        //then
        verify(exactly = 1) { dataSource.retrieveConfig() }
//        assertThat(config).isNotNull

    }
}