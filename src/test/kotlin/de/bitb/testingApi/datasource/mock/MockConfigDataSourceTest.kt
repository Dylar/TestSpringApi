package de.bitb.testingApi.datasource.mock

import de.bitb.testingApi.models.ConfigType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockConfigDataSourceTest {

    private val configDataSource = MockConfigDataSource()

    @Test
    fun `test get config from data source`() {
        //when
        val config = configDataSource.retrieveConfig()
        //then
        assertThat(config.values).isNotEmpty
    }

    @Test
    fun `has color config`() {
        //when
        val config = configDataSource.retrieveConfig()
        //then
        assertThat(config).satisfies {
            assertThat(it.values.contains(ConfigType.COLOR))
        }
    }
}