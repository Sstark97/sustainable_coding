package dev.kata.template_engine

class TemplateEngine {
    companion object {
        // private var logs: List<String> = listOf("La variable var1 no se ha podido sustituir porque no fue encontrada")

        fun parse(template: String, templateVariables: Map<String, String>): Template {
            val variableValue = templateVariables.values.toTypedArray().firstOrNull() ?: ""
            val keyToReplace = templateVariables.keys.toTypedArray().firstOrNull() ?: ""
            val parsedTemplate = template.replace("{\$" + keyToReplace + "}", variableValue)
            return Template(parsedTemplate)
        }

//        fun showLogs(): List<String> {
//            return logs;
//        }
    }
}
