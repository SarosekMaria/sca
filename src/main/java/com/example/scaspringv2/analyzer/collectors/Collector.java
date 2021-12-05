package com.example.scaspringv2.analyzer.collectors;

import com.example.scaspringv2.analyzer.ComplexityCounter;

import java.util.List;

public interface Collector {
    void addWarning(String className, String warning);

    void addComplexityResults(String className, ComplexityCounter counter);

    void addWarningToAnalyzeResults(ParamType param, List<String> warnings);

    void incrementMetric(String metricName);

     void incrementMetric(String metricName, int count);
}
