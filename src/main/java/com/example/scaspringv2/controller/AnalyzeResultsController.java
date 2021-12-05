package com.example.scaspringv2.controller;

import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.service.AnalyzeResultService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Data
@RequestMapping("/api/sca")
public class AnalyzeResultsController {
    private final AnalyzeResultService analyzeResultService;

    @PostMapping
    public Map<String, AnalyzeResult<?>> getHandlingResults(@RequestBody String filename) {
        System.out.println("------- filename: " + filename);
        return analyzeResultService.getAnalysis(filename);
    }
}
