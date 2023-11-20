package dev.kata.template_engine

class TemplateEngine {
    companion object {
        fun parse(template: String, templateVariables: Map<String, String>): Template {
            val warnings = mutableListOf<String>()
            val variableRegex = Regex("\\{\\$\\w+\\}")
            val parsedTemplate = template.replace(variableRegex) { match ->
                val matchValue = match.value
                val key = matchValue.substring(2, matchValue.length - 1)
                val variableValue = templateVariables.get(key)

                if (variableValue.isNullOrBlank()) {
                    warnings.add("The variable $key could not be replaced because it was not found")
                    matchValue
                } else {
                    variableValue
                }
            }
            return Template(parsedTemplate, warnings)
        }
    }
}