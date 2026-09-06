package de.maibornwolff.treesitter.excavationsite.languages.bicep

import de.maibornwolff.treesitter.excavationsite.languages.bicep.extractors.extractBicepStringContent
import de.maibornwolff.treesitter.excavationsite.shared.domain.CommentFormats
import de.maibornwolff.treesitter.excavationsite.shared.domain.Extract
import de.maibornwolff.treesitter.excavationsite.shared.domain.ExtractionMapping
import de.maibornwolff.treesitter.excavationsite.shared.domain.ExtractionStrategy

/**
 * MVP text extraction mappings for Bicep declarations, comments, and strings.
 */
object BicepExtractionMapping : ExtractionMapping {
    private const val IDENTIFIER = "identifier"

    override val nodeExtractions: Map<String, Extract> = buildMap {
        listOf(
            "parameter_declaration",
            "variable_declaration",
            "resource_declaration",
            "module_declaration",
            "output_declaration",
            "type_declaration",
            "user_defined_function",
            "parameter"
        ).forEach { put(it, Extract.Identifier(single = ExtractionStrategy.FirstChildByType(IDENTIFIER))) }

        put("comment", Extract.Comment(CommentFormats.AutoDetect))
        put("string", Extract.StringLiteral(custom = ::extractBicepStringContent))
    }
}
