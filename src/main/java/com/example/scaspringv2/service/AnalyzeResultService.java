package com.example.scaspringv2.service;

import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;

import java.util.Map;

public interface AnalyzeResultService {
    Map<String, AnalyzeResult<?>> getAnalysis(String path);
}
