package com.larrex.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory factory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job job() {

        return jobBuilderFactory
                .get("step1")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(step())
                .build();

    }

    public Step step() {
        return factory
                .get("step1")
                .<String, String>chunk(1)
                .reader(reader())
                .writer(writer())
                .processor(processor())
                .listener(listener())
                .build();
    }

    @Bean
    public Reader reader() {
        return new Reader();
    }

    @Bean
    public Writer writer() {
        return new Writer();
    }

    @Bean
    public Processor processor() {
        return new Processor();
    }

    @Bean
    public JobListener listener() {
        return new JobListener();
    }
}
