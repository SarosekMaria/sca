package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.ArrayList;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.*;
import static java.util.Objects.nonNull;

public class TooLongVisitor extends AbstractVoidVisitorAdapter<Collector> {
    public static final String MORE_THAN_ONE_ACTION_PER_LINE_REGEX = ".*;.+;*";

    /**
     * Check for method count
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodCallExpr declaration, Collector collector) {
        int methodsCount = declaration.getArguments().size();
        List<String> warningMsgs = new ArrayList<>();
        if (methodsCount > MAX_METHODS_COUNT) {
            String warning = "В классе " + className + " " + methodsCount + " методов! Пороговое значение: " + MAX_METHODS_COUNT;
//            String warning = "Class  has more than " + MAX_METHODS_COUNT + " methods";
            collector.addWarning(className, warning);
            warningMsgs.add(warning);
        }
        collector.addAnalyzeResult(
                "MAX_METHODS_COUNT",
                new AnalyzeResult<>(className, MAX_METHODS_COUNT, warningMsgs)
        );
        super.visit(declaration, collector);
    }

    /**
     * Check for too long method names, body, arguments
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        int methodBodyLength = declaration.getRange().map(range -> range.end.line - range.begin.line - 1).orElse(0);
        int methodNameLength = declaration.getNameAsString().length();
        int parametersCount = declaration.getParameters().size();
        String[] methodStrings = declaration.toString().split("\n");
        int methodNotEmptyLines = methodStrings.length - 2;

        System.out.println("methodBodyLength: " + methodBodyLength);
        System.out.println("programLines.length: " + methodNotEmptyLines);
        List<String> lineLengthWarnings = new ArrayList<>();
        List<String> lineActionWarnings = new ArrayList<>();
        for (String methodLine : methodStrings) {
            int lineLength = methodLine.length();
            if (lineLength > MAX_SYMBOLS_COUNT_PER_LINE) {
                String warning = "Строка класса " + className + " в методе \"" + declaration.getName() +
                        "\" слишком длинная (" + lineLength + ")! Пороговое значение: " + MAX_SYMBOLS_COUNT_PER_LINE;
//                String warning = "Method \"" + declaration.getName() + "\" variable \"" + param.getName() + "\" is way too long!";
                collector.addWarning(className, warning);
                lineLengthWarnings.add(warning);
            }

            if (methodLine.matches(MORE_THAN_ONE_ACTION_PER_LINE_REGEX)) {
                String warning = "Указано некорректное количество действий в одной строке метода \"" + declaration.getName() + "\"! " +
                        "Необходимо указать только одно действие";
//                String warning = "Method \"" + declaration.getName() + "\" variable \"" + param.getName() + "\" is way too long!";
                collector.addWarning(className, warning);
                lineActionWarnings.add(warning);
            }
        }
        collector.addAnalyzeResult(
                "MAX_SYMBOLS_COUNT_PER_LINE",
                new AnalyzeResult<>(className, MAX_SYMBOLS_COUNT_PER_LINE, lineLengthWarnings)
        );
        collector.addAnalyzeResult(
                "ONE_ACTION_PER_LINE",
                new AnalyzeResult<>(className, MAX_NUMBER_OF_ACTIONS_PER_LINE, lineActionWarnings)
        );

        List<String> emptyLinesCountWarnings = new ArrayList<>();
        int emptyLinesCount = methodBodyLength - methodNotEmptyLines;
        if (emptyLinesCount > MAX_EMPTY_LINES_COUNT_PER_METHOD) {
            String warning = "Метод " + declaration.getNameAsString() + " класса " + className + " имеет слишком много пустых строк (" + emptyLinesCount + ")!";
//                String warning = "Method \"" + declaration.getName() + "\" variable \"" + param.getName() + "\" is way too long!";
            collector.addWarning(className, warning);
            emptyLinesCountWarnings.add(warning);
        }
        collector.addAnalyzeResult(
                "MAX_EMPTY_LINES_COUNT_PER_METHOD",
                new AnalyzeResult<>(className, MAX_EMPTY_LINES_COUNT_PER_METHOD, emptyLinesCountWarnings)
        );

        List<String> maxBodyLengthWarnings = new ArrayList<>();
        if (methodBodyLength > MAX_BODY_LENGTH) {
            String warning = "Метод \"" + declaration.getName() + "\" класса " + className + " имеет " + methodBodyLength + " строк!";
//            String warning = "Method \"" + declaration.getName() + "\" body has more than " + MAX_BODY_LENGTH + " lines";
            collector.addWarning(className, warning);
            maxBodyLengthWarnings.add(warning);
        }
        collector.addAnalyzeResult(
                "MAX_BODY_LENGTH",
                new AnalyzeResult<>(className, MAX_BODY_LENGTH, maxBodyLengthWarnings)
        );

        List<String> methodNameLengthWarnings = new ArrayList<>();
        if (methodNameLength > MAX_METHOD_NAME_LENGTH) {
            String warning = "Метод \"" + declaration.getName() + "\" класса " + className + " имеет слишком длинное название (" + methodNameLength + " )!";
//            String warning = "Method \"" + declaration.getName() + "\" name is too long, it has more than " + MAX_METHOD_NAME_LENGTH + " characters";
            collector.addWarning(className, warning);
            methodNameLengthWarnings.add(warning);
        }
        collector.addAnalyzeResult(
                "MAX_METHOD_NAME_LENGTH",
                new AnalyzeResult<>(className, MAX_METHOD_NAME_LENGTH, methodNameLengthWarnings));

        List<String> maxParamCountWarnings = new ArrayList<>();
        if (parametersCount > MAX_PARAM_COUNT) {
            String warning = "Метод \"" + declaration.getName() + "\" класса " + className + " имеет слишком много параметов (" + parametersCount + " )!";
//            String warning = "Method \"" + declaration.getName() + "\"  has more than " + MAX_METHOD_NAME_LENGTH + " parameters";
            collector.addWarning(className, warning);
            maxParamCountWarnings.add(warning);
        }
        collector.addAnalyzeResult(
                "MAX_PARAM_COUNT",
                new AnalyzeResult<>(className, MAX_PARAM_COUNT, maxParamCountWarnings)
        );

        List<String> maxVariableLengthWarnings = new ArrayList<>();
        for (Parameter param : declaration.getParameters()) {
            int paramNameLength = param.getNameAsString().length();
            if (paramNameLength > MAX_VARIABLE_LENGTH) {
                String warning = "В классе " + className + " внутри метода \"" + declaration.getName() + "\" переменная \"" + param.getName() + "\" " +
                        "имеет слишком длинное название (" + paramNameLength + ")! Необходимо его уменьшить до " + MAX_VARIABLE_LENGTH + ".";
//                String warning = "Method \"" + declaration.getName() + "\" variable \"" + param.getName() + "\" is way too long!";
                collector.addWarning(className, warning);
                maxVariableLengthWarnings.add(warning);
            }
        }
        collector.addAnalyzeResult(
                "MAX_VARIABLE_LENGTH",
                new AnalyzeResult<>(className, MAX_VARIABLE_LENGTH, maxVariableLengthWarnings)
        );
    }


    /**
     * Check for too many variables & for too long ones
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {
        int variablesCount = declaration.getVariables().size();

        List<String> variablesCountWarnings = new ArrayList<>();
        if (variablesCount > MAX_VARIABLE_COUNT) {
            String warning = "Класс " + className + " имеет болле " + MAX_VARIABLE_COUNT + " переменных";
//            String warning = "Class has more than " + MAX_VARIABLE_COUNT + " variables";
            collector.addWarning(className, warning);
            variablesCountWarnings.add(warning);
        }
        collector.addAnalyzeResult(
                "MAX_VARIABLE_COUNT",
                new AnalyzeResult<>(className, MAX_VARIABLE_COUNT, variablesCountWarnings));

        List<String> maxVariableLengthWarnings = new ArrayList<>();
        for (VariableDeclarator variable : declaration.getVariables()) {
            int variableNameLength = variable.getNameAsString().length();
            if (variableNameLength > MAX_VARIABLE_LENGTH) {
                String warning = "В классе " + className + " переменная \"" + variable.getNameAsString() + "\" имеет слишком длинное название!";
//                String warning = "Field variable \"" + variable.getNameAsString() + "\" is way too long!";
                collector.addWarning(className, warning);
                maxVariableLengthWarnings.add(warning);
            }
        }
        collector.addAnalyzeResult(
                "MAX_VARIABLE_LENGTH",
                new AnalyzeResult<>(className, MAX_VARIABLE_LENGTH, maxVariableLengthWarnings)
        );
    }
}
