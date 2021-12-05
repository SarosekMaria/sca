package com.example.scaspringv2.analyzer.collectors;

import java.util.List;

public interface Collector {
    void addWarningToAnalyzeResults(ParamType param, List<String> warnings);
}