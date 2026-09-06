package de.maibornwolff.treesitter.excavationsite.languages.bicep.extractors

import de.maibornwolff.treesitter.excavationsite.shared.infrastructure.walker.TreeTraversal
import org.treesitter.TSNode

/**
 * Extracts Bicep string content while excluding the `string` primitive type token,
 * which shares its Tree-sitter node name with string literals.
 */
internal fun extractBicepStringContent(node: TSNode, sourceCode: String): String? {
    val nodeText = TreeTraversal.getNodeText(node, sourceCode)
    if (!nodeText.startsWith("'")) return null

    return TreeTraversal.findChildByType(node, "string_content", sourceCode) ?: ""
}
