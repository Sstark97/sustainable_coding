package dev.kata.template_engine

class TemplateEngine {
    companion object {
        fun parse(template: String, templateVariables: Map<String, String>): Template {
            val parsedTemplate = templateVariables.entries.fold(template) { parsedTemplate, (key, value) ->
                parsedTemplate.replace("{\$" + key + "}", value)
            }
            return Template(parsedTemplate)
        }
    }
}
