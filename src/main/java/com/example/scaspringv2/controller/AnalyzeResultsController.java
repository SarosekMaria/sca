package com.example.scaspringv2.controller;

import com.example.scaspringv2.domain.HandlingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sca")
public class AnalyzeResultsController {


    @PostMapping
    public HandlingResult getHandlingResults(@RequestBody String filename) {
        System.out.println("------- filename: " + filename);
        return new HandlingResult("SUCCESS");
    }
}
