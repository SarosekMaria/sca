package com.example.scaspringv2.analyzer.collectors;

import com.example.scaspringv2.analyzer.ComplexityCounter;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.scaspringv2.analyzer.collectors.AnalyzeResult.createAnalyzeResult;
import static com.example.scaspringv2.analyzer.collectors.ParamType.*;

/**
 * Collect all stats to hashmap
 */
@Data
public class HashMapCollector implements Collector {
    private static HashMap<String, List<String>> warnings;
    private static HashMap<String, Integer> stats;
    private static HashMap<String, ComplexityCounter> complexity;
//    private static HashMap<String, AnalyzeResult<?>> analyzeParameterByName;
//    private static HashMap<AnalyzeResult<?>, List<String>> warningsByAnalyzeParams;
    private static HashMap<String, AnalyzeResult<?>> analyzeResultByName;

    public HashMapCollector() {
        warnings = new HashMap<>();
        stats = new HashMap<>();
        complexity = new HashMap<>();
//        analyzeParameterByName = initAnalyzeParams();
//        warningsByAnalyzeParams = init();
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
    
//    private HashMap<String, AnalyzeResult<?>> initAnalyzeParams() {
//        HashMap<String, AnalyzeResult<?>> analyzeParametersMap = new HashMap<>();
//
//        analyzeParametersMap.put(MAX_CLASS_LENGTH.toString(), createAnalyzeResult(MAX_CLASS_LENGTH));
//        analyzeParametersMap.put(MAX_BODY_LENGTH.toString(), createAnalyzeResult(MAX_BODY_LENGTH));
//        analyzeParametersMap.put(MAX_METHOD_NAME_LENGTH.toString(), createAnalyzeResult(MAX_METHOD_NAME_LENGTH));
//        analyzeParametersMap.put(MAX_PARAM_COUNT.toString(), createAnalyzeResult(MAX_PARAM_COUNT));
//        analyzeParametersMap.put(MAX_VARIABLE_LENGTH.toString(), createAnalyzeResult(MAX_VARIABLE_LENGTH));
//        analyzeParametersMap.put(MAX_VARIABLE_COUNT.toString(), createAnalyzeResult(MAX_VARIABLE_COUNT));
//        analyzeParametersMap.put(MAX_METHODS_COUNT.toString(), createAnalyzeResult(MAX_METHODS_COUNT));
//        analyzeParametersMap.put(MIN_VARIABLE_LENGTH.toString(), createAnalyzeResult(MIN_VARIABLE_LENGTH));
//        analyzeParametersMap.put(BOOLEAN_STARTS_WITH_IS_HAS.toString(), createAnalyzeResult(BOOLEAN_STARTS_WITH_IS_HAS));
//        analyzeParametersMap.put(NAMES_IN_CAMEL_CASE.toString(), createAnalyzeResult(NAMES_IN_CAMEL_CASE));
//        analyzeParametersMap.put(MAX_SYMBOLS_COUNT_PER_LINE.toString(), createAnalyzeResult(MAX_SYMBOLS_COUNT_PER_LINE));
//        analyzeParametersMap.put(MAX_NUMBER_OF_ACTIONS_PER_LINE.toString(), createAnalyzeResult(MAX_NUMBER_OF_ACTIONS_PER_LINE));
//        analyzeParametersMap.put(MAX_EMPTY_LINES_COUNT_PER_METHOD.toString(), createAnalyzeResult(MAX_EMPTY_LINES_COUNT_PER_METHOD));
//
//        return analyzeParametersMap;
//    }

//    private HashMap<AnalyzeResult<?>, List<String>> init() {
//        HashMap<AnalyzeResult<?>, List<String>> warningsByAnalyzeParams = new HashMap<>();
//
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_CLASS_LENGTH.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_BODY_LENGTH.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_METHOD_NAME_LENGTH.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_PARAM_COUNT.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_VARIABLE_LENGTH.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_VARIABLE_COUNT.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_METHODS_COUNT.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MIN_VARIABLE_LENGTH.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(BOOLEAN_STARTS_WITH_IS_HAS.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(NAMES_IN_CAMEL_CASE.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_SYMBOLS_COUNT_PER_LINE.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_NUMBER_OF_ACTIONS_PER_LINE.toString()), new ArrayList<>());
//        warningsByAnalyzeParams.put(analyzeParameterByName.get(MAX_EMPTY_LINES_COUNT_PER_METHOD.toString()), new ArrayList<>());
//
//        return warningsByAnalyzeParams;
//    }

    public static HashMap<String, List<String>> getWarnings() {
        return warnings;
    }

    public static HashMap<String, Integer> getStats() {
        return stats;
    }

    public static HashMap<String, ComplexityCounter> getComplexity() {
        return complexity;
    }

    public HashMap<String, AnalyzeResult<?>> getAnalyzeResultByName() {
        return analyzeResultByName;
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

    public void addWarningToAnalyzeResults(ParamType param, List<String> warnings) {
        analyzeResultByName.get(param.toString()).getWarnings().addAll(warnings);
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
