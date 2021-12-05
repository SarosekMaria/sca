package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.example.scaspringv2.analyzer.collectors.ParamType;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.*;

public class TooLongVisitor extends AbstractVoidVisitorAdapter<Collector> {
    public static final String MORE_THAN_ONE_ACTION_PER_LINE_REGEX = ".*;.+;*";

    /**
     * Check for method count
     */
    @Override
    public void visit(MethodCallExpr declaration, Collector collector) {
        int methodsCount = declaration.getArguments().size();
        List<String> warningMsgs = new ArrayList<>();

        if (methodsCount > MAX_METHODS_COUNT) {
            String warning = "В классе " + className + " " + methodsCount + " методов! Пороговое значение: " + MAX_METHODS_COUNT;
            warningMsgs.add(warning);
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_METHODS_COUNT, warningMsgs);

        super.visit(declaration, collector);
    }

    /**
     * Check for too long method names, body, arguments
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        int methodBodyLength = declaration.getRange().map(range -> range.end.line - range.begin.line - 1).orElse(0);
        int methodNameLength = declaration.getNameAsString().length();
        int parametersCount = declaration.getParameters().size();
        String[] methodStrings = declaration.toString().split("\n");
        int methodNotEmptyLines = methodStrings.length - 2;
        List<String> lineLengthWarnings = new ArrayList<>();
        List<String> lineActionWarnings = new ArrayList<>();

        for (String methodLine : methodStrings) {
            int lineLength = methodLine.length();
            if (lineLength > MAX_SYMBOLS_COUNT_PER_LINE) {
                String warning = "Строка класса " + className + " в методе \"" + declaration.getName() +
                        "\" слишком длинная (" + lineLength + ")! Пороговое значение: " + MAX_SYMBOLS_COUNT_PER_LINE;
                lineLengthWarnings.add(warning);
            }

            if (methodLine.matches(MORE_THAN_ONE_ACTION_PER_LINE_REGEX)) {
                String warning = "Указано некорректное количество действий в одной строке метода \"" +
                        declaration.getName() + "\"! " + "Необходимо указать только одно действие";
                lineActionWarnings.add(warning);
            }
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_SYMBOLS_COUNT_PER_LINE, lineLengthWarnings);
        collector.addWarningToAnalyzeResults(ParamType.MAX_NUMBER_OF_ACTIONS_PER_LINE, lineActionWarnings);

        List<String> emptyLinesCountWarnings = new ArrayList<>();
        int emptyLinesCount = methodBodyLength - methodNotEmptyLines;
        if (emptyLinesCount > MAX_EMPTY_LINES_COUNT_PER_METHOD) {
            String warning = "Метод " + declaration.getNameAsString() + " класса " + className +
                    " имеет слишком много пустых строк (" + emptyLinesCount + ")!";
            emptyLinesCountWarnings.add(warning);
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_EMPTY_LINES_COUNT_PER_METHOD, emptyLinesCountWarnings);

        List<String> maxBodyLengthWarnings = new ArrayList<>();
        if (methodBodyLength > MAX_BODY_LENGTH) {
            String warning = "Метод \"" + declaration.getName() + "\" класса " + className + " имеет " + methodBodyLength + " строк!";
            maxBodyLengthWarnings.add(warning);
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_BODY_LENGTH, maxBodyLengthWarnings);

        List<String> methodNameLengthWarnings = new ArrayList<>();
        if (methodNameLength > MAX_METHOD_NAME_LENGTH) {
            String warning = "Метод \"" + declaration.getName() + "\" класса " + className +
                    " имеет слишком длинное название (" + methodNameLength + " )!";
            methodNameLengthWarnings.add(warning);
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_METHOD_NAME_LENGTH, methodNameLengthWarnings);

        List<String> maxParamCountWarnings = new ArrayList<>();
        if (parametersCount > MAX_PARAM_COUNT) {
            String warning = "Метод \"" + declaration.getName() + "\" класса " + className +
                    " имеет слишком много параметов (" + parametersCount + " )!";
            maxParamCountWarnings.add(warning);
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_PARAM_COUNT, maxParamCountWarnings);

        List<String> maxVariableLengthWarnings = new ArrayList<>();
        for (Parameter param : declaration.getParameters()) {
            int paramNameLength = param.getNameAsString().length();
            if (paramNameLength > MAX_VARIABLE_LENGTH) {
                String warning = "В классе " + className + " внутри метода \"" + declaration.getName() + "\" переменная \"" + param.getName() + "\" " +
                        "имеет слишком длинное название (" + paramNameLength + ")! Необходимо его уменьшить до " + MAX_VARIABLE_LENGTH + ".";
                maxVariableLengthWarnings.add(warning);
            }
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_VARIABLE_LENGTH, maxVariableLengthWarnings);
    }


    /**
     * Check for too many variables & for too long ones
     */
    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {
        int variablesCount = declaration.getVariables().size();

        List<String> variablesCountWarnings = new ArrayList<>();
        if (variablesCount > MAX_VARIABLE_COUNT) {
            String warning = "Класс " + className + " имеет болле " + MAX_VARIABLE_COUNT + " переменных";
            variablesCountWarnings.add(warning);
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_VARIABLE_COUNT, variablesCountWarnings);

        List<String> maxVariableLengthWarnings = new ArrayList<>();
        for (VariableDeclarator variable : declaration.getVariables()) {
            int variableNameLength = variable.getNameAsString().length();
            if (variableNameLength > MAX_VARIABLE_LENGTH) {
                String warning = "В классе " + className + " переменная \"" + variable.getNameAsString() + "\" имеет слишком длинное название!";
                maxVariableLengthWarnings.add(warning);
            }
        }
        collector.addWarningToAnalyzeResults(ParamType.MAX_VARIABLE_LENGTH, maxVariableLengthWarnings);
    }
}
