package de.bitb.testingApi.datasource.mock

import de.bitb.testingApi.models.COLOR
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
        assertThat(config.values).anySatisfy {
            assertThat(it.type).isEqualTo(ConfigType.COLOR)
            assertThat(it.value).isNotNull
            assertThat(it.value).isInstanceOf(COLOR::class.java)
        }
    }
}