package dev.kata.template_engine

data class Template(val parsedTemplate: String) {
    fun showLogs(): List<String> {
        return listOf("The variable var1 could not be replaced because it was not found")
    }
}
