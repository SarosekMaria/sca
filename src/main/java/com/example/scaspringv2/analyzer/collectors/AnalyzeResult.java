package com.example.scaspringv2.analyzer.collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AnalyzeResult<V> {
    private final String paramNameRus;
    private final V thresholdValue;
    private List<String> warnings;

    public static AnalyzeResult<?> createAnalyzeResult(ParamType param) {
        return new AnalyzeResult<>(param.getName(), param.getThresholdValue(), new ArrayList<>());
    }
}
