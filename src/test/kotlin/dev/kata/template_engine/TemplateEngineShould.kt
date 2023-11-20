package dev.kata.template_engine.dev.kata.template_engine

import dev.kata.template_engine.Template
import dev.kata.template_engine.TemplateEngine
import dev.kata.template_engine.Warning
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
 *  "{$var1}", [] -> register in logs list
 *  "hello {$placeholder} {$placeholder}", [placeholder: hello] -> "hello hello hello"
 *  "hello {$var1} ${var2}", [$var1: my, $var2: friend!] -> "hello my friend!"
 *  "{$var1} {$var2}", [] -> register in logs list
 *  "{$var1}", [$var2: hola] -> ???
 *  "hola {$a1} {$b2} {$a1}", [$a1: foo, $b2: bar] -> "hola foo bar foo"
 *  "hola {$a1} {$b2}", [$a1: foo] -> ???
 */
internal class TemplateEngineShould {
    @Test
    fun `parse a template in blank without variables`() {
        assertThat(TemplateEngine.parse("", emptyMap()))
            .isEqualTo(Template("", emptyList()))
    }

    @Test
    fun `parse a template without variables`() {
        assertThat(TemplateEngine.parse("hola", emptyMap()))
            .isEqualTo(Template("hola", emptyList()))
    }

    @Test
    fun `parse a template with one variable`() {
        assertThat(
            TemplateEngine.parse(
                "hola {\$placeholder}",
                mapOf<String, String>(Pair("placeholder", "mundo"))
            )
        ).isEqualTo(Template("hola mundo", emptyList()))

        assertThat(
            TemplateEngine.parse(
                "hello {\$placeholder}",
                mapOf<String, String>(Pair("placeholder", "world!"))
            )
        ).isEqualTo(Template("hello world!", emptyList()))

        assertThat(
            TemplateEngine.parse(
                "hello {\$var1}",
                mapOf<String, String>(Pair("var1", "world!"))
            )
        ).isEqualTo(Template("hello world!", emptyList()))
    }
    @Test
    fun `register a warning in a log list when a variable in the template it's not provided`() {
        val parsedTemplate = TemplateEngine.parse("{\$var1}", emptyMap())
        val expectedWarnings = listOf(
            Warning("The variable var1 could not be replaced because it was not found")
        )

        assertThat(parsedTemplate).isEqualTo(Template("{\$var1}", expectedWarnings))
        assertThat(parsedTemplate.showLogs())
            .isEqualTo(expectedWarnings)
    }

    @Test
    fun `parse all occurrences in a template with one variable`() {
        assertThat(
            TemplateEngine.parse(
                "hello {\$placeholder} {\$placeholder}",
                mapOf<String, String>(Pair("placeholder", "hello"))
            )
        ).isEqualTo(Template("hello hello hello", emptyList()))
    }

    @Test
    fun `parse a template with two variables`() {
        assertThat(
            TemplateEngine.parse(
                "hello {\$var1} {\$var2}",
                mapOf<String, String>(Pair("var1", "my"), Pair("var2", "friend!"))
            )
        ).isEqualTo(Template("hello my friend!", emptyList()))
    }

    @Test
    fun `register warnings in a log list when two variables in the template are not provided`() {
        val parsedTemplate = TemplateEngine.parse("{\$var1} {\$var2}", emptyMap())
        val expectedWarnings = listOf(
            Warning("The variable var1 could not be replaced because it was not found"),
            Warning("The variable var2 could not be replaced because it was not found")
        )

        assertThat(parsedTemplate).isEqualTo(Template("{\$var1} {\$var2}", expectedWarnings))
        assertThat(parsedTemplate.showLogs()).isEqualTo(expectedWarnings)
    }
}
