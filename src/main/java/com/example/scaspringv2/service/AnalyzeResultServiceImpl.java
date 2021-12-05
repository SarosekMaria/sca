package com.example.scaspringv2.service;

import com.example.scaspringv2.analyzer.Analyzer;
import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.analyzer.collectors.HashMapCollector;
import com.example.scaspringv2.analyzer.printers.WarningPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyzeResultServiceImpl implements AnalyzeResultService {
    @Override
    public Map<String, AnalyzeResult<?>> getAnalysis(String path) {
        try {
            return run(Paths.get(path));
        } catch (Exception e) {
            System.out.println("Looks like you specified not existing directory. Exiting...");
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * Walk through the files and collect stats and then print them out
     *
     * @param p
     */
    private static Map<String, AnalyzeResult<?>> run(Path p) {
        try {
            HashMapCollector collector = new HashMapCollector();

            Files.walkFileTree(p, new Analyzer(collector));
            var analyzeResultsByStatNames = collector.getAnalyzeResultByName();
            new WarningPrinter(collector).print();
//            new MetricPrinter(collector).print();
//            new MetricPrinter(collector).print();

            return analyzeResultsByStatNames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
