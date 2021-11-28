package com.example.scaspringv2.analyzer.printers;

import com.example.scaspringv2.analyzer.collectors.HashMapCollector;

public abstract class AbstractPrinter {

    protected HashMapCollector collector;

    public AbstractPrinter(HashMapCollector collector) {

        this.collector = collector;
    }


}
