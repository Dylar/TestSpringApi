package de.bitb.testingApi.models

data class ConfigData(
    val type: ConfigType,
    val value: Any,
)

data class Configuration(val values: MutableMap<ConfigType, Any>) {
    fun getConfigData(type: ConfigType): ConfigData {
        val value = values[type] ?: throw NoSuchElementException("No Config ($type) found")
        return ConfigData(type, value)
    }
}

enum class ConfigType {
    UNKNOWN, COLOR
}

enum class COLORS(val value: String) {
    TEAL_GREEN("#2cbcc1"),
    TEAL_BLUE("#1ea6d4"),
    PLAIN_RED("#FF0000"),
}