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
    public static final int MAX_CLASS_LENGTH = 1000; //1

    /**
     * Maximum number of lines per method
     */
    public static final int MAX_BODY_LENGTH = 50; //2

    /**
     * Maximum length per method name
     */
    public static final int MAX_METHOD_NAME_LENGTH = 50; //3

    /**
     * Maximum number of parameters per method
     */
    public static final int MAX_PARAM_COUNT = 8; //4

    /**
     * Maximum length of field/parameter
     */
    public static final int MAX_VARIABLE_LENGTH = 20; //5

    /**
     * Maximum number of variables declared per class
     */
    public static final int MAX_VARIABLE_COUNT = 15; //6

    /**
     * Maximum number of methods per class
     */
    public static final int MAX_METHODS_COUNT = 25; //7

    /**
     * Minimum length of variable
     */
    public static final int MIN_VARIABLE_LENGTH = 3; //8

    /**
     * Minimum length of method name
     */
    public static final int MIN_METHOD_NAME = 3; //9

    /**
     * Boolean method must start with is (e.g. isSomething()) when there are no parameters
     */
    public static final boolean BOOLEAN_STARTS_WITH_IS = true; // 10 // yes

    /**
     * Class name must be in CamelCase
     */
    public static final boolean CAMEL_CASE_CLASS_NAME = true; //11

    /**
     * Method must be in camelCase
     */
    public static final boolean METHOD_IN_CAMEL_CASE = true; // 12
    /**
     * Fields/parameters must be in camelCase
     */
    public static final boolean PARAM_IN_CAMEL_CASE = true; //13
}
