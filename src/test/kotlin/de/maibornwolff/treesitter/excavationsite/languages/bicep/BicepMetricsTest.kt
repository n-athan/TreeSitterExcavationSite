package de.maibornwolff.treesitter.excavationsite.languages.bicep

import de.maibornwolff.treesitter.excavationsite.api.Language
import de.maibornwolff.treesitter.excavationsite.api.TreeSitterMetrics
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BicepMetricsTest {
    @Nested
    inner class Complexity {
        @Test
        fun `should count functions and control flow`() {
            // Arrange
            val code = """
                func selectName(name string) string => name == '' ? 'fallback' : name
                var enabledResources = [for item in items: if (item.enabled) {
                  name: item.name
                }]
            """.trimIndent()

            // Act
            val result = TreeSitterMetrics.parse(code, Language.BICEP)

            // Assert
            assertThat(result.numberOfFunctions).isEqualTo(1.0)
            assertThat(result.logicComplexity).isEqualTo(3.0)
            assertThat(result.complexity).isEqualTo(4.0)
        }
    }

    @Nested
    inner class ParametersAndComments {
        @Test
        fun `should count function parameters and comment lines`() {
            // Arrange
            val code = """
                // Select a display name
                func selectName(name string, fallback string) string => name == '' ? fallback : name
            """.trimIndent()

            // Act
            val result = TreeSitterMetrics.parse(code, Language.BICEP)

            // Assert
            assertThat(result.commentLines).isEqualTo(1.0)
            assertThat(result.perFunctionMetrics["max_parameters_per_function"]).isEqualTo(2.0)
        }
    }
}
