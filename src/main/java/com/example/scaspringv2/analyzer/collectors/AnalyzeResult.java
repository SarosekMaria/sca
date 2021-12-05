package com.example.scaspringv2.analyzer.collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import static java.util.List.of;

@Data
@AllArgsConstructor
public class AnalyzeResult<T> {
    private String className;
    private T thresholdValue;
    private List<String> warnings;

    public static <V> AnalyzeResult<?> empty(V threshold) {
        return new AnalyzeResult<>("", threshold, of());
    }
}
