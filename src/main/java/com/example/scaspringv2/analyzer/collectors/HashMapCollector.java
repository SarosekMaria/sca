package com.example.scaspringv2.analyzer.collectors;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

import static com.example.scaspringv2.analyzer.collectors.AnalyzeResult.createAnalyzeResult;
import static com.example.scaspringv2.analyzer.collectors.ParamType.*;

/**
 * Collect all stats to hashmap
 */
@Data
public class HashMapCollector implements Collector {
    private static HashMap<String, AnalyzeResult<?>> analyzeResultByName;

    public HashMapCollector() {
        analyzeResultByName = init();
    }

    private HashMap<String, AnalyzeResult<?>> init() {
        HashMap<String, AnalyzeResult<?>> analyzeResultByName = new HashMap<>();

        analyzeResultByName.put(MAX_CLASS_LENGTH.toString(), createAnalyzeResult(MAX_CLASS_LENGTH));
        analyzeResultByName.put(MAX_BODY_LENGTH.toString(), createAnalyzeResult(MAX_BODY_LENGTH));
        analyzeResultByName.put(MAX_METHOD_NAME_LENGTH.toString(), createAnalyzeResult(MAX_METHOD_NAME_LENGTH));
        analyzeResultByName.put(MAX_PARAM_COUNT.toString(), createAnalyzeResult(MAX_PARAM_COUNT));
        analyzeResultByName.put(MAX_VARIABLE_LENGTH.toString(), createAnalyzeResult(MAX_VARIABLE_LENGTH));
        analyzeResultByName.put(MAX_VARIABLE_COUNT.toString(), createAnalyzeResult(MAX_VARIABLE_COUNT));
        analyzeResultByName.put(MAX_METHODS_COUNT.toString(), createAnalyzeResult(MAX_METHODS_COUNT));
        analyzeResultByName.put(MIN_VARIABLE_LENGTH.toString(), createAnalyzeResult(MIN_VARIABLE_LENGTH));
        analyzeResultByName.put(BOOLEAN_STARTS_WITH_IS_HAS.toString(), createAnalyzeResult(BOOLEAN_STARTS_WITH_IS_HAS));
        analyzeResultByName.put(NAMES_IN_CAMEL_CASE.toString(), createAnalyzeResult(NAMES_IN_CAMEL_CASE));
        analyzeResultByName.put(MAX_SYMBOLS_COUNT_PER_LINE.toString(), createAnalyzeResult(MAX_SYMBOLS_COUNT_PER_LINE));
        analyzeResultByName.put(MAX_NUMBER_OF_ACTIONS_PER_LINE.toString(), createAnalyzeResult(MAX_NUMBER_OF_ACTIONS_PER_LINE));
        analyzeResultByName.put(MAX_EMPTY_LINES_COUNT_PER_METHOD.toString(), createAnalyzeResult(MAX_EMPTY_LINES_COUNT_PER_METHOD));

        return analyzeResultByName;
    }

    public HashMap<String, AnalyzeResult<?>> getAnalyzeResultByName() {
        return analyzeResultByName;
    }

    public void addWarningToAnalyzeResults(ParamType param, List<String> warnings) {
        analyzeResultByName.get(param.toString()).getWarnings().addAll(warnings);
    }
}
