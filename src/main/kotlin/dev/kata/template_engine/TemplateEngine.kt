package dev.kata.template_engine

class TemplateEngine {
    companion object {
        fun parse(template: String, templateVariables: Map<String, String>): Template {
            var parsedTemplate = template
            templateVariables.forEach { (key, value) ->
                parsedTemplate = parsedTemplate.replace("{\$" + key + "}", value)
            }
            return Template(parsedTemplate)
        }
    }
}
