package dev.kata.template_engine

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * TODO:
 *  null, null -> ???
 *  "", [] -> ???
 *  "hola", [] -> "hola"
 *  "hola {$placeholder}", [$placeholder: mundo] -> "hola mundo"
 *  "hello {$placeholder}", [$placeholder: world!] -> "hello world!"
 *  "hello {$var1}", [$var1: world!] -> "hello world!"
 *  "{$var1}", [] -> ???
 *  "hello {$var1} ${var2}", [$var1: my, $var2: friend!] -> "hello my friend!"
 *  "{$var1}", [$var2: hola] -> ???
 *  "hola {$a1} {$b2} {$a1}", [$a1: foo, $b2: bar] -> "hola foo bar foo"
 *  "hola {$a1} {$b2}", [$a1: foo] -> ???
 */
internal class TemplateEngineShould {
    @Test
    fun `parse a template in blank without variables`() {
        assertThat(TemplateEngine.parse("", mapOf<String, String>()))
            .isEqualTo(Template(""))
    }

    @Test
    fun `parse a template without variables`() {
        assertThat(TemplateEngine.parse("hola", mapOf<String, String>()))
            .isEqualTo(Template("hola"))
    }

    @Test
    fun `parse a template with one variable`() {
        assertThat(TemplateEngine.parse(
            "hola {\$placeholder}",
            mapOf<String, String>(Pair("placeholder", "mundo"))
        )).isEqualTo(Template("hola mundo"))

        assertThat(TemplateEngine.parse(
            "hello {\$placeholder}",
            mapOf<String, String>(Pair("placeholder", "world!"))
        )).isEqualTo(Template("hello world!"))

        assertThat(TemplateEngine.parse(
            "hello {\$var1}",
            mapOf<String, String>(Pair("var1", "world!"))
        )).isEqualTo(Template("hello world!"))
    }

    @Test
    fun `register a warning in a log list when a variable in the template it's not provided`() {
        val parsedTemplate = TemplateEngine.parse("{\$var1}", mapOf<String, String>())

        assertThat(parsedTemplate).isEqualTo(Template("{\$var1}"))
        assertThat(parsedTemplate.showLogs())
            .isEqualTo(listOf("The variable var1 could not be replaced because it was not found"))
    }
}
