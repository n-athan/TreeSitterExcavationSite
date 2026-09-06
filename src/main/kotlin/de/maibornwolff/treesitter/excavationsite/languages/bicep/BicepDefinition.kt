package de.maibornwolff.treesitter.excavationsite.languages.bicep

import de.maibornwolff.treesitter.excavationsite.shared.domain.Extract
import de.maibornwolff.treesitter.excavationsite.shared.domain.LanguageDefinition
import de.maibornwolff.treesitter.excavationsite.shared.domain.Metric

/**
 * Unified Bicep language definition for metrics and text extraction.
 */
object BicepDefinition : LanguageDefinition {
    override val nodeMetrics: Map<String, Set<Metric>> = BicepMetricMapping.nodeMetrics
    override val nodeExtractions: Map<String, Extract> = BicepExtractionMapping.nodeExtractions
}
