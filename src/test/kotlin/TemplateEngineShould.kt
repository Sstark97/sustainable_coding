package dev.kata.stringcalculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * TODO:
 *  null, null -> ???
 *  "", [] -> ???
 *  "hola", [] -> "hola"
 *  "{$var1}", [] -> ???
 *  "{$var1}", [$var2: hola] -> ???
 *  "hola {$placeholder}", [$placeholder: mundo] -> "hola mundo"
 *  "hola {$a1} {$b2} {$a1}", [$a1: foo, $b2: bar] -> "hola foo bar foo"
 *  "hola {$a1} {$b2}", [$a1: foo] -> ???
 */
internal class TemplateEngineShould {
    @Test
    fun `parse a template in blank without variables`() {
        assertThat(TemplateEngine.parse("", mapOf<String, String>())).isEqualTo("")
    }

    @Test
    fun `parse a template without variables`() {
        assertThat(TemplateEngine.parse("hola", mapOf<String, String>())).isEqualTo("hola")
    }

    @Test
    fun `parse a template with one variable`() {
        assertThat(TemplateEngine.parse(
            "hola {\$placeholder}",
            mapOf<String, String>(Pair("placeholder", "mundo"))
        )).isEqualTo("hola mundo")
        assertThat(TemplateEngine.parse(
            "hello {\$placeholder}",
            mapOf<String, String>(Pair("placeholder", "world!"))
        )).isEqualTo("hello world!")
        assertThat(TemplateEngine.parse(
            "hello {\$var1}",
            mapOf<String, String>(Pair("var1", "world!"))
        )).isEqualTo("hello world!")
    }

    /**
     * Opciones:
     * 1. Que no remplace y lo deje igual -> {$var1}
     * 2. Que lance una excepción -> La variable var1 no se ha encontrado
     * 3. Que no lo remplace y guarde el fallo en algún sistema de logs -> "{$var1}" | Logs[La variable var1 no se ha encontrado]
     */
//    @Test
//    fun `with a variable in the template but variables are empty register the event in a log list`() {
//        assertThat(TemplateEngine.parse("{\$var1}", mapOf<String, String>())).isEqualTo("{\$var1}")
//        assertThat(TemplateEngine.showLogs()).isEqualTo(listOf("The variable var1 could not be replaced because it was not found"))
//    }
}
