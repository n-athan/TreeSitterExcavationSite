package de.maibornwolff.treesitter.excavationsite.languages.bicep

import de.maibornwolff.treesitter.excavationsite.shared.domain.Metric
import de.maibornwolff.treesitter.excavationsite.shared.domain.MetricMapping

/**
 * MVP metric mappings for Bicep declarations and expressions.
 */
object BicepMetricMapping : MetricMapping {
    override val nodeMetrics: Map<String, Set<Metric>> = buildMap {
        listOf("if_statement", "for_statement", "ternary_expression")
            .forEach { put(it, setOf(Metric.LogicComplexity)) }

        put("user_defined_function", setOf(Metric.FunctionComplexity, Metric.Function))
        put("parameter", setOf(Metric.Parameter))
        put("call_expression", setOf(Metric.MessageChain, Metric.MessageChainCall))
        put("member_expression", setOf(Metric.MessageChain))
        put("comment", setOf(Metric.CommentLine))
    }
}
