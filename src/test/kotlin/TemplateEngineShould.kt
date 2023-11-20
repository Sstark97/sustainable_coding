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
}
