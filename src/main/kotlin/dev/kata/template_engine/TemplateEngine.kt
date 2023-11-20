package dev.kata.template_engine

class TemplateEngine {
    companion object {
        fun parse(template: String, templateVariables: Map<String, String>): Template {
            val warnings = mutableListOf<String>()
            val parsedTemplate = template.replace(Regex("\\{\\$\\w+\\}")) { matchResult ->
                val key = matchResult.value.substring(2, matchResult.value.length - 1)
                templateVariables[key] ?: run {
                    warnings.add("The variable $key could not be replaced because it was not found")
                    matchResult.value
                }
            }
            return Template(parsedTemplate, warnings)
        }
    }
}