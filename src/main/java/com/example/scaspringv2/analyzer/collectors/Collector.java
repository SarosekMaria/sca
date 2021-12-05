package com.example.scaspringv2.analyzer.collectors;

import com.example.scaspringv2.analyzer.ComplexityCounter;

import java.util.List;

public interface Collector {
    /**
     * Add warnings to queue
     */
    void addWarning(String className, String warning);
    /**
     * Add complexity results for the class
     */
    void addComplexityResults(String className, ComplexityCounter counter);
    /**
     * Add analyze results for the class
     */
    void addWarningToAnalyzeResults(ParamType param, List<String> warnings);
    /**
     * Increment some metric by +1
     */
    void incrementMetric(String metricName);
    /**
     * Increment some metric by specified count
     */
     void incrementMetric(String metricName, int count);
}
