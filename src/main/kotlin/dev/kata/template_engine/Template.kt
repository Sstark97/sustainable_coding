package dev.kata.template_engine

data class Template(val parsedTemplate: String, val warnings: List<Warning>) {
    fun showLogs(): List<Warning> {
        return warnings
    }
}
