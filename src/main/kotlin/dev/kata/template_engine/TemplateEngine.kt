package dev.kata.template_engine

class TemplateEngine {
    companion object {
        fun parse(template: String, templateVariables: Map<String, String>): Template {
            val variableValue = templateVariables.values.toTypedArray().firstOrNull() ?: ""
            val keyToReplace = templateVariables.keys.toTypedArray().firstOrNull() ?: ""
            val parsedTemplate = template.replace("{\$" + keyToReplace + "}", variableValue)
            return Template(parsedTemplate)
        }
    }
}
