package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.Config;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

public class TooSmallVisitor extends AbstractVoidVisitorAdapter<Collector> {



    private static final int MIN_VARIABLE_LENGTH = Config.MIN_VARIABLE_LENGTH;
    private static final int MIN_METHOD_NAME = Config.MIN_METHOD_NAME;


    /**
     * Check for too short method names
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        int methodNameLength = declaration.getName().toString().length();
        List<String> methodNameLengthWarnings = new ArrayList<>();
        if (methodNameLength < MIN_METHOD_NAME) {
            String warning = "Method with name \"" + declaration.getName().toString() + "\" length is too small";
            collector.addWarning(className, warning);
            methodNameLengthWarnings.add(warning);
        }
        collector.addAnalyzeResult("MIN_METHOD_NAME", new AnalyzeResult<>(className, methodNameLength, MIN_METHOD_NAME, methodNameLengthWarnings));

        super.visit(declaration, collector);
    }


    /**
     * Check for too short variable names
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {
        List<String> minVariableLengthWarnings = new ArrayList<>();
        for (VariableDeclarator variable: declaration.getVariables()) {
            int variableNameLength = variable.getNameAsString().length();
            if (variable.getNameAsString().length() < MIN_VARIABLE_LENGTH) {
                String warning = "Variable \"" + variable.getNameAsString() + "\" length is too small";
                collector.addWarning(className, warning);
                minVariableLengthWarnings.add(warning);
            }
            collector.addAnalyzeResult("MIN_VARIABLE_LENGTH", new AnalyzeResult<>(className, variableNameLength, MIN_VARIABLE_LENGTH, minVariableLengthWarnings));
        }
    }
}
