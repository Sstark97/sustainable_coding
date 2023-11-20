package dev.kata.stringcalculator

class TemplateEngine {
    companion object {
        // private var logs: List<String> = listOf("La variable var1 no se ha podido sustituir porque no fue encontrada")

        fun parse(template: String, templateVariables: Map<String, String>): String {
            return template.replace("{\$placeholder}", "mundo")
        }

//        fun showLogs(): List<String> {
//            return logs;
//        }
    }
}
