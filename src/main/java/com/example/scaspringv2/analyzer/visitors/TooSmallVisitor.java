package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.example.scaspringv2.analyzer.collectors.ParamType;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.MIN_VARIABLE_LENGTH;

public class TooSmallVisitor extends AbstractVoidVisitorAdapter<Collector> {
    /**
     * Check for too short variable names
     */
    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {
        List<String> minVariableLengthWarnings = new ArrayList<>();

        for (VariableDeclarator variable: declaration.getVariables()) {
            if (variable.getNameAsString().length() < MIN_VARIABLE_LENGTH) {
                String warning = "В классе " + className + " переменная \"" + variable.getNameAsString() +
                        "\" имеет слишком короткое название";
                minVariableLengthWarnings.add(warning);
            }
        }
        collector.addWarningToAnalyzeResults(ParamType.MIN_VARIABLE_LENGTH, minVariableLengthWarnings);
    }
}
