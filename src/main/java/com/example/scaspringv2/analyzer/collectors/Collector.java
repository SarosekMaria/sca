package com.example.scaspringv2.analyzer.collectors;

import com.example.scaspringv2.analyzer.ComplexityCounter;

public interface Collector {

    /**
     * Add warnings to queue
     *
     * @param className
     * @param warning
     */
    void addWarning(String className, String warning);

    /**
     * Add complexity results for the class
     *
     * @param className
     * @param counter
     */
    void addComplexityResults(String className, ComplexityCounter counter);

    /**
     * Add analyze results for the class
     *
     * @param paramName
     * @param analyzeResult
     */
    <T> void addAnalyzeResult(String paramName, AnalyzeResult<T> analyzeResult);

    /**
     * Increment some metric by +1
     *
     * @param metricName
     */
    void incrementMetric(String metricName);

    /**
     * Increment some metric by specified count
     *
     * @param metricName
     * @param count
     */
     void incrementMetric(String metricName, int count);
}
