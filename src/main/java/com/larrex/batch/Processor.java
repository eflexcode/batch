package com.larrex.batch;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String ,String > {
    @Override
    public String process(String s) throws Exception {

        System.out.println("inside process");

        return "PROCESSED "+s.toUpperCase();
    }
}
