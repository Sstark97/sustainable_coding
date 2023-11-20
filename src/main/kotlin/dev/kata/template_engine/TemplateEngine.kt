package dev.kata.template_engine

class TemplateEngine {
    companion object {
        fun parse(template: String, templateVariables: Map<String, String>): Template {
            var parsedTemplate = template
            val warnings = mutableListOf<String>()
            val variables = findAllVariablesIn(template)

            variables.forEach { match ->
                val key = match.substring(2, match.length - 1)
                val variableValue = templateVariables.get(key)

                if (variableValue.isNullOrBlank()) {
                    warnings.add("The variable $key could not be replaced because it was not found")
                } else {
                    parsedTemplate = parsedTemplate.replace("{\$" + key + "}", variableValue)
                }
            }

            return Template(parsedTemplate, warnings)
        }

        private fun findAllVariablesIn(template: String): List<String> {
            val variableRegex = Regex("\\{\\$\\w+\\}")
            val variables = variableRegex.findAll(template).map { it.value }.toList()
            return variables
        }
    }
}