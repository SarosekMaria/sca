package com.example.scaspringv2.analyzer.visitors;

import com.example.scaspringv2.analyzer.AbstractVoidVisitorAdapter;
import com.example.scaspringv2.analyzer.collectors.Collector;
import com.example.scaspringv2.analyzer.collectors.ParamType;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

import static com.example.scaspringv2.analyzer.Config.BOOLEAN_STARTS_WITH_IS_HAS;

public class BooleanMethodVisitor extends AbstractVoidVisitorAdapter<Collector> {


    /**
     * Boolean method should start isSomething(), not getSomething()
     * <p>
     * getSomething(someVar) is OK, though
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {
        List<String> warnings = new ArrayList<>();
        if (isBooleanDeclarationCorrect(declaration)) {
            var warningMsg = "Наименование метода \"" + declaration.getType().toString() + "\" должно начинаться с префикса is или has";
            warnings.add(warningMsg);
        }
        collector.addWarningToAnalyzeResults(ParamType.BOOLEAN_STARTS_WITH_IS_HAS, warnings);
    }

    private boolean isBooleanDeclarationCorrect(MethodDeclaration declaration) {
        String methodName = declaration.getNameAsString();
        return BOOLEAN_STARTS_WITH_IS_HAS && declaration.getType().toString().equals("boolean") &&
                declaration.getParameters().size() == 0 &&
                !(methodName.startsWith("is") || methodName.startsWith("has"));
    }
}