package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.example.scaspringv2.analyzer.collectors.ParamType;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.MIN_METHOD_NAME;
import static com.example.scaspringv2.analyzer.Config.MIN_VARIABLE_LENGTH;

public class TooSmallVisitor extends AbstractVoidVisitorAdapter<Collector> {
//    /**
//     * Check for too short method names
//     *
//     * @param declaration
//     * @param collector
//     */
//    @Override
//    public void visit(MethodDeclaration declaration, Collector collector) {
//        int methodNameLength = declaration.getName().toString().length();
//        List<String> methodNameLengthWarnings = new ArrayList<>();
//
//        if (methodNameLength < MIN_METHOD_NAME) {
//            String warning = "В классе " + className + " метод \"" + declaration.getName().toString() + "\" имеет слишком короткое название";
//            collector.addWarning(className, warning);
//            methodNameLengthWarnings.add(warning);
//        }
////        collector.addAnalyzeResult(
////                "MIN_METHOD_NAME",
////                new AnalyzeResult<>(className, MIN_METHOD_NAME, methodNameLengthWarnings)
////        );
//        collector.addWarningToAnalyzeResults(ParamType.MIN_METHOD_NAME, methodNameLengthWarnings);
//
//        super.visit(declaration, collector);
//    }


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
            if (variable.getNameAsString().length() < MIN_VARIABLE_LENGTH) {
                String warning = "В классе " + className + " переменная \"" + variable.getNameAsString() + "\" имеет слишком короткое название";
                collector.addWarning(className, warning);
                minVariableLengthWarnings.add(warning);
            }
        }
//            collector.addAnalyzeResult(
//                    "MIN_VARIABLE_LENGTH",
//                    new AnalyzeResult<>(className, MIN_VARIABLE_LENGTH, minVariableLengthWarnings)
//            );
        collector.addWarningToAnalyzeResults(ParamType.MIN_VARIABLE_LENGTH, minVariableLengthWarnings);
    }
}
