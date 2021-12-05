package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.MAX_CLASS_LENGTH;

public class ClassLineCounterVisitor extends AbstractVoidVisitorAdapter<Collector> {

    /**
     * Calculate number of lines in the class
     *
     */
    public void visit(CompilationUnit cu, Collector collector) {
        super.visit(cu, collector);

        String[] programLines = cu.toString().split("\n");
        int count = programLines.length;
        List<String> warnings = new ArrayList<>();
        if (count > MAX_CLASS_LENGTH) {
            String warning = "Класс \"" + className + " содержит " + count + " строк, что превышает пороговое значение в \"" + MAX_CLASS_LENGTH + " строк";
//            String warning = "Class has more than " + MAX_CLASS_LENGTH + " lines";
            collector.addWarning(className, warning);
            warnings.add(warning);
        }
        collector.addAnalyzeResult(
                "MAX_CLASS_LENGTH",
                new AnalyzeResult<>(className, MAX_CLASS_LENGTH, warnings)
        );

        collector.incrementMetric("Code Lines", count);
    }

}
