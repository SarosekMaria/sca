package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.Config;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

public class ClassLineCounterVisitor extends AbstractVoidVisitorAdapter<Collector> {

    private static final int MAX_CLASS_LENGTH = Config.MAX_CLASS_LENGTH;

    /**
     * Calculate number of lines in the class
     *
     */
    public void visit(CompilationUnit cu, Collector collector) {
        super.visit(cu, collector);

        int count = cu.toString().split("\n").length;
        List<String> warnings = new ArrayList<>();
        if (count > MAX_CLASS_LENGTH) {
            String warning = "Class has more than " + MAX_CLASS_LENGTH + " lines";
            collector.addWarning(className, warning);
            warnings.add(warning);
        }
        collector.addAnalyzeResult("MAX_CLASS_LENGTH", new AnalyzeResult<>(className, count, MAX_CLASS_LENGTH, warnings));

        collector.incrementMetric("Code Lines", count);
    }

}
