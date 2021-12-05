package com.example.scaspringv2.analyzer;

public final class Config {
    /**
     * Maximum length of class file
     */
    public static final int MAX_CLASS_LENGTH = 1065;
    /**
     * Maximum number of lines per method
     */
    public static final int MAX_BODY_LENGTH = 25;
    /**
     * Maximum length per method name
     */
    public static final int MAX_METHOD_NAME_LENGTH = 25;
    /**
     * Maximum number of parameters per method
     */
    public static final int MAX_PARAM_COUNT = 5;
    /**
     * Maximum length of field/parameter
     */
    public static final int MAX_VARIABLE_LENGTH = 20;
    /**
     * Maximum number of variables declared per class
     */
    public static final int MAX_VARIABLE_COUNT = 25;
    /**
     * Maximum number of methods per class
     */
    public static final int MAX_METHODS_COUNT = 40;
    /**
     * Minimum length of variable
     */
    public static final int MIN_VARIABLE_LENGTH = 3;
    /**
     * Boolean method must start with is (e.g. isSomething()) or has (hasParent()) when there are no parameters
     */
    public static final boolean BOOLEAN_STARTS_WITH_IS_HAS = true;
    /**
     * Class name must be in CamelCase
     */
    public static final boolean CAMEL_CASE_CLASS_NAME_PARAMS_METHODS = true;
    /**
     * Maximum count of symbols in code line
     */
    public static final int MAX_SYMBOLS_COUNT_PER_LINE = 80;
    /**
     * Maximum count of actions in code line
     */
    public static final int MAX_NUMBER_OF_ACTIONS_PER_LINE = 1;
    /**
     * Maximum count of empty lines inside the methods
     */
    public static final int MAX_EMPTY_LINES_COUNT_PER_METHOD = 2;
}
