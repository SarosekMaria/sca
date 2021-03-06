package com.example.scaspringv2.analyzer;

import com.example.scaspringv2.analyzer.collectors.Collector;
import com.example.scaspringv2.analyzer.visitors.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Analyzer extends SimpleFileVisitor<Path> {
    private Collector collector;

    public Analyzer(Collector collector) {
        this.collector = collector;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        if (isNotJava(file))
            return FileVisitResult.CONTINUE;
        CompilationUnit unit = JavaParser.parse(file.toFile());

        // collect all the stats
        new BooleanMethodVisitor().visit(unit, collector);
        new TooLongVisitor().visit(unit, collector);
        new TooSmallVisitor().visit(unit, collector);
        new VariableNamingConventionVisitor().visit(unit, collector);
        new ClassLineCounterVisitor().visit(unit, collector);

        return FileVisitResult.CONTINUE;
    }

    /**
     * Extract class name from the path
     */
    private String getClassName(Path file) {
        String filename = file.getFileName().toString();
        if (filename.indexOf(".") > 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        return filename;
    }

    /**
     * We only need to analyze the Java files
     */
    private boolean isNotJava(Path file) {
        return !file.toString().endsWith("java");
    }
}
