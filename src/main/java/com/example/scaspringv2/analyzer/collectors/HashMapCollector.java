package com.example.scaspringv2.analyzer.collectors;

import com.example.scaspringv2.analyzer.ComplexityCounter;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.*;
import static com.example.scaspringv2.analyzer.collectors.AnalyzeResult.empty;

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
        analyzeResultsByStatNames = init();
    }

    private HashMap<String, AnalyzeResult<?>> init() {
        HashMap<String, AnalyzeResult<?>> analyzeResultMap = new HashMap<>();
        analyzeResultMap.put("MAX_CLASS_LENGTH", empty(MAX_CLASS_LENGTH));
        analyzeResultMap.put("MAX_BODY_LENGTH", empty(MAX_BODY_LENGTH));
        analyzeResultMap.put("MAX_METHOD_NAME_LENGTH", empty(MAX_METHOD_NAME_LENGTH));
        analyzeResultMap.put("MAX_PARAM_COUNT", empty(MAX_PARAM_COUNT));
        analyzeResultMap.put("MAX_VARIABLE_LENGTH", empty(MAX_VARIABLE_LENGTH));
        analyzeResultMap.put("MAX_VARIABLE_COUNT", empty(MAX_VARIABLE_COUNT));
        analyzeResultMap.put("MAX_METHODS_COUNT", empty(MAX_METHODS_COUNT));
        analyzeResultMap.put("MIN_VARIABLE_LENGTH", empty(MIN_VARIABLE_LENGTH));
        analyzeResultMap.put("BOOLEAN_STARTS_WITH_IS_HAS", empty(BOOLEAN_STARTS_WITH_IS_HAS));
        analyzeResultMap.put("CAMEL_CASE_CLASS_NAME", empty(CAMEL_CASE_CLASS_NAME));
        analyzeResultMap.put("METHOD_IN_CAMEL_CASE", empty(METHOD_IN_CAMEL_CASE));
        analyzeResultMap.put("PARAM_IN_CAMEL_CASE", empty(PARAM_IN_CAMEL_CASE));
        analyzeResultMap.put("MAX_SYMBOLS_COUNT_PER_LINE", empty(MAX_SYMBOLS_COUNT_PER_LINE));
        analyzeResultMap.put("MAX_NUMBER_OF_ACTIONS_PER_LINE", empty(MAX_NUMBER_OF_ACTIONS_PER_LINE));
        analyzeResultMap.put("MAX_EMPTY_LINES_COUNT_PER_METHOD", empty(MAX_EMPTY_LINES_COUNT_PER_METHOD));
        return analyzeResultMap;
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

            warnings.put(className, new ArrayList<>() {{
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
