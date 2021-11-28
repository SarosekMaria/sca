package com.example.scaspringv2.controller;

import com.example.scaspringv2.analyzer.collectors.AnalyzeResult;
import com.example.scaspringv2.service.AnalyzeResultService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        return new HandlingResult("SUCCESS");
    }
}
