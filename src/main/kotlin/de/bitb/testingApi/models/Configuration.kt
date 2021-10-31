package de.bitb.testingApi.models

data class ConfigData(
    val type: ConfigType,
    val value: Any,
)

data class Configuration(val values: List<ConfigData>)

enum class ConfigType {
    COLOR
}

enum class COLOR(val value: String) {
    TEAL_GREEN("#2cbcc1"),
    TEAL_BLUE("#1ea6d4"),
}