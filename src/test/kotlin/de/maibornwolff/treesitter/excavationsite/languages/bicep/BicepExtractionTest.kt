package de.maibornwolff.treesitter.excavationsite.languages.bicep

import de.maibornwolff.treesitter.excavationsite.api.Language
import de.maibornwolff.treesitter.excavationsite.api.TreeSitterExtraction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BicepExtractionTest {
    @Nested
    inner class Identifiers {
        @Test
        fun `should extract declaration and function parameter identifiers`() {
            // Arrange
            val code = """
                param location string = 'westeurope'
                var suffix = 'dev'
                resource storage 'Microsoft.Storage/storageAccounts@2023-05-01' = {}
                module networking './network.bicep' = {}
                output storageName string = storage.name
                type storageConfig = object
                func buildName(prefix string) string => '${'$'}{prefix}-${'$'}{suffix}'
            """.trimIndent()

            // Act
            val result = TreeSitterExtraction.extract(code, Language.BICEP)

            // Assert
            assertThat(result.identifiers).containsExactlyInAnyOrder(
                "location",
                "suffix",
                "storage",
                "networking",
                "storageName",
                "storageConfig",
                "buildName",
                "prefix"
            )
        }
    }

    @Nested
    inner class CommentsAndStrings {
        @Test
        fun `should extract comments and strings`() {
            // Arrange
            val code = """
                // Deployment location
                /* Environment suffix */
                param location string = 'westeurope'
                var greeting = 'hello ${'$'}{location}'
            """.trimIndent()

            // Act
            val result = TreeSitterExtraction.extract(code, Language.BICEP)

            // Assert
            assertThat(result.comments).containsExactlyInAnyOrder("Deployment location", "Environment suffix")
            assertThat(result.strings).containsExactlyInAnyOrder("westeurope", "hello ")
        }
    }
}
