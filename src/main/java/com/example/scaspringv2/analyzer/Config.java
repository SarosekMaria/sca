package com.example.scaspringv2.analyzer;

public final class Config {


    /**
     * Max count for calculating "Cyclomatic Complexity"
     */
    public static final int MAX_CYCLOMATIC_COMPLEXITY = 50;

    /**
     * Max count for calculating "Weighted Method Count (WMC)"
     */
    public static final int MAX_WEIGHTED_COUNT = 50;

    /**
     * Maximum length of class file
     */
    public static final int MAX_CLASS_LENGTH = 1065; //1

    /**
     * Maximum number of lines per method
     */
    public static final int MAX_BODY_LENGTH = 25; //2

    /**
     * Maximum length per method name
     */
    public static final int MAX_METHOD_NAME_LENGTH = 25; //3

    /**
     * Maximum number of parameters per method
     */
    public static final int MAX_PARAM_COUNT = 5; //4

    /**
     * Maximum length of field/parameter
     */
    public static final int MAX_VARIABLE_LENGTH = 20; //5

    /**
     * Maximum number of variables declared per class
     */
    public static final int MAX_VARIABLE_COUNT = 25; //6

    /**
     * Maximum number of methods per class
     */
    public static final int MAX_METHODS_COUNT = 40; //7

    /**
     * Minimum length of variable
     */
    public static final int MIN_VARIABLE_LENGTH = 3; //8

    /**
     * Minimum length of method name
     */
    public static final int MIN_METHOD_NAME = 3;

    /**
     * Boolean method must start with is (e.g. isSomething()) or has (hasParent()) when there are no parameters
     */
    public static final boolean BOOLEAN_STARTS_WITH_IS_HAS = true; // 9

    /**
     * Class name must be in CamelCase
     */
    public static final boolean CAMEL_CASE_CLASS_NAME = true; //10

    /**
     * Method must be in camelCase
     */
    public static final boolean METHOD_IN_CAMEL_CASE = true; // 10
    /**
     * Fields/parameters must be in camelCase
     */
    public static final boolean PARAM_IN_CAMEL_CASE = true; //10
    /**
     * Maximum count of symbols in code line
     */
    public static final int MAX_SYMBOLS_COUNT_PER_LINE = 80; //11
    /**
     * Maximum count of actions in code line
     */
    public static final int MAX_NUMBER_OF_ACTIONS_PER_LINE = 1; //12
    /**
     * Maximum count of empty lines inside the methods
     */
    public static final int MAX_EMPTY_LINES_COUNT_PER_METHOD = 2; //13
}
