package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.Config;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

public class TooLongVisitor extends AbstractVoidVisitorAdapter<Collector> {


    //todo: зачем? Можно убрать, сделать статические импорты из файла конфигов
    public static final int MAX_BODY_LENGTH = Config.MAX_BODY_LENGTH;

    private static final int MAX_METHOD_NAME_LENGTH = Config.MAX_METHOD_NAME_LENGTH;

    private static final int MAX_PARAM_COUNT = Config.MAX_PARAM_COUNT;

    private static final int MAX_VARIABLE_LENGTH = Config.MAX_VARIABLE_LENGTH;

    private static final int MAX_VARIABLE_COUNT = Config.MAX_VARIABLE_COUNT;

    private static final int MAX_METHODS_COUNT = Config.MAX_METHODS_COUNT;


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
            String warning = "Class  has more than " + MAX_METHODS_COUNT + " methods";
            collector.addWarning(className, warning);
            warningMsgs.add(warning);
        }
        collector.addAnalyzeResult("MAX_METHODS_COUNT",
                new AnalyzeResult<>(className, methodsCount, MAX_METHODS_COUNT, warningMsgs));
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
        int methodBodyLength = declaration.getRange().map(range -> range.begin.line - range.end.line).orElse(0);
        int methodNameLength = declaration.getNameAsString().length();
        int parametersCount = declaration.getParameters().size();

        List<String> maxBodyLengthWarnings = new ArrayList<>();
        if (methodBodyLength > MAX_BODY_LENGTH) {
            String warning = "Method \"" + declaration.getName() + "\" body has more than " + MAX_BODY_LENGTH + " lines";
            collector.addWarning(className, warning);
            maxBodyLengthWarnings.add(warning);
        }
        collector.addAnalyzeResult("MAX_BODY_LENGTH", new AnalyzeResult<>(className, methodBodyLength, MAX_BODY_LENGTH, maxBodyLengthWarnings));

        List<String> methodNameLengthWarnings = new ArrayList<>();
        if (methodNameLength > MAX_METHOD_NAME_LENGTH) {
            String warning = "Method \"" + declaration.getName() + "\" name is too long, it has more than " + MAX_METHOD_NAME_LENGTH + " characters";
            collector.addWarning(className, warning);
            methodNameLengthWarnings.add(warning);
        }
        collector.addAnalyzeResult("MAX_METHOD_NAME_LENGTH", new AnalyzeResult<>(className, methodNameLength, MAX_METHOD_NAME_LENGTH, methodNameLengthWarnings));

        List<String> maxParamCountWarnings = new ArrayList<>();
        if (parametersCount > MAX_PARAM_COUNT) {
            String warning = "Method \"" + declaration.getName() + "\"  has more than " + MAX_METHOD_NAME_LENGTH + " parameters";
            collector.addWarning(className, warning);
            maxParamCountWarnings.add(warning);
        }
        collector.addAnalyzeResult("MAX_PARAM_COUNT", new AnalyzeResult<>(className, parametersCount, MAX_PARAM_COUNT, maxParamCountWarnings));

        //todo: подумать, как коллекционировать информацию о множественных параметрах (то есть в методе может быть много переменных, не удовлетворяющих условию)
        //можно делать так - собирать названия всех переменных в мапу - <ИмяПеременной, Ошибка>, а потом на ui, если мапа переменных с ошибками не пуста,
        //то в сертификате эргономичности указывать, что пункт не пройден и пояснение по всем косячным переменным
        for (Parameter param : declaration.getParameters()) {
            List<String> maxVariableLengthWarnings = new ArrayList<>();
            int paramNameLength = param.getNameAsString().length();
            if (paramNameLength > MAX_VARIABLE_LENGTH) {
                String warning = "Method \"" + declaration.getName() + "\" variable \"" + param.getName() + "\" is way too long!";
                collector.addWarning(className, warning);
                maxVariableLengthWarnings.add(warning);
            }
            collector.addAnalyzeResult("MAX_VARIABLE_LENGTH", new AnalyzeResult<>(className, paramNameLength, MAX_VARIABLE_LENGTH, maxVariableLengthWarnings));
        }

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
            String warning = "Class has more than " + MAX_VARIABLE_COUNT + " variables";
            collector.addWarning(className, warning);
            variablesCountWarnings.add(warning);
        }
        collector.addAnalyzeResult("MAX_VARIABLE_COUNT", new AnalyzeResult<>(className, variablesCount, MAX_VARIABLE_COUNT, variablesCountWarnings));

        for (VariableDeclarator variable : declaration.getVariables()) {
            List<String> maxVariableLengthWarnings = new ArrayList<>();
            int variableNameLength = variable.getNameAsString().length();
            if (variableNameLength > MAX_VARIABLE_LENGTH) {
                String warning = "Field variable \"" + variable.getNameAsString() + "\" is way too long!";
                collector.addWarning(className, warning);
                maxVariableLengthWarnings.add(warning);
            }
            collector.addAnalyzeResult("MAX_VARIABLE_LENGTH", new AnalyzeResult<>(className, variableNameLength, MAX_VARIABLE_LENGTH, maxVariableLengthWarnings));
        }
    }
}
