package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.github.javaparser.ast.body.MethodDeclaration;

import static com.example.scaspringv2.analyzer.Config.BOOLEAN_STARTS_WITH_IS;
import static java.util.List.of;

public class BooleanMethodVisitor extends AbstractVoidVisitorAdapter<Collector> {


    /**
     * Boolean method should start isSomething(), not getSomething()
     *
     * getSomething(someVar) is OK, though
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        if (isBooleanDeclarationCorrect(declaration)) {
            var warningMsg = "Method name \"" + declaration.getType().toString() + "\"  should start with is, e.g.\"isSomething()\"";
            collector.addWarning(className, warningMsg);
            collector.addAnalyzeResult("BOOLEAN_STARTS_WITH_IS",
                    new AnalyzeResult<>(className, false, true, of(warningMsg)));
        } else {
            collector.addAnalyzeResult("BOOLEAN_STARTS_WITH_IS",
                    new AnalyzeResult<>(className, true, true, of()));
        }
    }

    private boolean isBooleanDeclarationCorrect(MethodDeclaration declaration) {
        return BOOLEAN_STARTS_WITH_IS && declaration.getType().toString().equals("boolean") &&
                declaration.getParameters().size() == 0 &&
                !declaration.getNameAsString().startsWith("is");
    }
}