package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.*;

public class VariableNamingConventionVisitor extends AbstractVoidVisitorAdapter<Collector> {
    public static final String CLASS_NAME_REGEX = "([A-Z][a-z0-9]+)+";
    public static final String OK_REGEX = "/(([A-Z]+_[A-Z]*)+)/gx";
    protected String methodName;

    /**
     * Check if class is in camel case
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Collector collector) {
        List<String> classNameInCamelCaseWarning = new ArrayList<>();
        boolean isClassNameInCamelCase = CAMEL_CASE_CLASS_NAME && declaration.getNameAsString().length() > 2 &&
                !declaration.getNameAsString().matches(CLASS_NAME_REGEX);
        if (isClassNameInCamelCase) {
            String warning = "Наименование класса " + className + " должно быть в CamelCase стиле (" + declaration.getNameAsString() + ").";
//            String warning = "Class name must be in CamelCase";
            collector.addWarning(className, warning);
            classNameInCamelCaseWarning.add(warning);
        }
        collector.addAnalyzeResult(
                "CAMEL_CASE_CLASS_NAME",
                new AnalyzeResult<>(className, CAMEL_CASE_CLASS_NAME, classNameInCamelCaseWarning)
        );

        super.visit(declaration, collector);
    }


    /**
     * Check if method & method variables is in camelCase, not in underscore_case,
     * since in Java we use camelCase naming convention
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        methodName = declaration.getNameAsString();
        List<String> methodInCamelCaseWarnings = new ArrayList<>();
        boolean isMethodInCamelCase = METHOD_IN_CAMEL_CASE && (methodName.contains("_") || !methodName.matches("^[a-z][a-zA-Z0-9]*$"));
        if (isMethodInCamelCase) {
            String warning = "В классе " + className + " наименование метода \"" + methodName + "\" должно быть в 'camelCase', а не в 'underscore_case'";
//            String warning = "Method \"" + methodName + "\"  should be in 'camelCase', not in 'underscore_case'";
            collector.addWarning(className, warning);
            methodInCamelCaseWarnings.add(warning);
        }
        collector.addAnalyzeResult(
                "METHOD_IN_CAMEL_CASE",
                new AnalyzeResult<>(className, METHOD_IN_CAMEL_CASE, methodInCamelCaseWarnings)
        );

        if (PARAM_IN_CAMEL_CASE) {
            List<String> paramInCamelCaseWarnings = new ArrayList<>();
            for (Parameter param : declaration.getParameters()) {
                boolean isParamInCamelCase = param.getNameAsString().contains("_");
                if (isParamInCamelCase) {
                    String warning = "В классе " + className + "\" переменная \"" + param.getName() +
                            " метода \"" + methodName + "\" должна быть в 'camelCase', а не в 'underscore_case'";
//                    String warning = "Method \"" + methodName + "\" variable \"" + param.getName() + "\" should be in 'camelCase', not in 'underscore_case'";
                    collector.addWarning(className, warning);
                    paramInCamelCaseWarnings.add(warning);
                }
            }
            collector.addAnalyzeResult(
                    "PARAM_IN_CAMEL_CASE",
                    new AnalyzeResult<>(className, PARAM_IN_CAMEL_CASE, paramInCamelCaseWarnings)
            );
        }

        super.visit(declaration, collector);

    }

    /**
     * Check if class variables is in camelCase, not in underscore_case,
     * since in Java we use camelCase naming convention
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {
//todo: collector.addAnalyzeResult
        if (PARAM_IN_CAMEL_CASE) {
            List<String> classFieldsInCamelCaseWarnings = new ArrayList<>();
            for (VariableDeclarator variable : declaration.getVariables()) {
                String name = variable.getNameAsString();
                if (name.matches(OK_REGEX)) // e.x. SOME_VARIABLE is OKAY
                    continue;
                if (name.contains("_")) {
                    String warning = "В классе " + className + "\" поле \"" + name + "\" должно быть в 'camelCase', а не в 'underscore_case'";
                    //String warning = "Method \"" + methodName + "\" variable \"" + name + "\" should be in 'camelCase', not in 'underscore_case'";
                    collector.addWarning(className, warning);
                    classFieldsInCamelCaseWarnings.add(warning);
                }
            }
            collector.addAnalyzeResult(
                    "PARAM_IN_CAMEL_CASE",
                    new AnalyzeResult<>(className, PARAM_IN_CAMEL_CASE, classFieldsInCamelCaseWarnings)
            );
        }
    }
}
