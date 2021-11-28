package com.example.scaspringv2.analyzer.collectors;

import com.example.scaspringv2.analyzer.ComplexityCounter;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Collect all stats to hashmap
 */
@Data
public class HashMapCollector implements Collector {
    private static HashMap<String, List<String>> warnings;

    private static HashMap<String, Integer> stats;

    private static HashMap<String, ComplexityCounter> complexity;

    private HashMap<String, AnalyzeResult<?>> analyzeResultsByStatNames;

    public HashMapCollector() {
        warnings = new HashMap<>();
        stats = new HashMap<>();
        complexity = new HashMap<>();
        analyzeResultsByStatNames = new HashMap<>();
    }

    public static HashMap<String, List<String>> getWarnings() {
        return warnings;
    }

    public static HashMap<String, Integer> getStats() {
        return stats;
    }

    public static HashMap<String, ComplexityCounter> getComplexity() {
        return complexity;
    }

    public HashMap<String, AnalyzeResult<?>> getAnalyzeResultsByStatNames() {
        return analyzeResultsByStatNames;
    }

    /**
     * {@inheritDoc}
     */
    public void addWarning(String className, String warning) {

        if (warnings.containsKey(className)) {
            warnings.get(className).add(warning);
        } else {

            warnings.put(className, new ArrayList<String>() {{
                add(warning);
            }});
        }

    }

    /**
     * {@inheritDoc}
     */
    public void addComplexityResults(String className, ComplexityCounter counter) {
        complexity.put(className, counter);
    }

    public <T> void addAnalyzeResult(String paramName, AnalyzeResult<T> analyzeResult) {
        analyzeResultsByStatNames.put(paramName, analyzeResult);
    }

    /**
     * {@inheritDoc}
     */
    public void incrementMetric(String metricName) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + 1);
        } else {
            stats.put(metricName, 1);
        }
    }


    /**
     * {@inheritDoc}
     */
    public void incrementMetric(String metricName, int count) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + count);
        } else {
            stats.put(metricName, count);
        }
    }


}
