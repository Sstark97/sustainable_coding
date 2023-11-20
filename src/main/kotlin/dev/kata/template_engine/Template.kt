package dev.kata.template_engine

data class Template(val parsedTemplate: String, val warnings: List<String>) {
    fun showLogs(): List<String> {
        return warnings
    }
}
