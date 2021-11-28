package com.example.scaspringv2.analyzer.collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnalyzeResult<T> {
    private String className;
    private T actualValue;
    private T thresholdValue;
    private List<String> warnings;
}
