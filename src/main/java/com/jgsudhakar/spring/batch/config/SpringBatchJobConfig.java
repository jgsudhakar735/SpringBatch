/**
 * 
 */
package com.jgsudhakar.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jgsudhakar.spring.batch.dto.StudentResponseDto;
import com.jgsudhakar.spring.batch.itemprocessor.StudentItemProcessor;
import com.jgsudhakar.spring.batch.itemreader.StudentItemReader;
import com.jgsudhakar.spring.batch.itemwriter.StudentItemWriter;
import com.jgsudhakar.spring.batch.listener.JobStatusListerner;
import com.jgsudhakar.spring.batch.service.StudentService;

/**
 * @Author : Sudhakar Tangellapalli
 * @File : com.jgsudhakar.spring.batch.config.SpringBatchJobConfig.java
 * @Date : 2021-10-05
 */
@Configuration
public class SpringBatchJobConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private StudentService service;

	/**
	 * Job To configure the flow of execution with listerner configuration
	 * @return
	 */
	@Bean
	public Job processJob() {
		return jobBuilderFactory
				.get("processJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(orderStep1())
				.end()
				.build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1") // Step name
				.<StudentResponseDto, StudentResponseDto>chunk(1) // Iterm Processor Input and output Classes
				.reader(new StudentItemReader()) // Item Reader class
				.processor(new StudentItemProcessor()) // Item Processor class
				.writer(new StudentItemWriter(this.service)) // Item Writer class
				.build();
	}

	/**
	 * This is to Listen to the job status
	 * 
	 * @return
	 */
	@Bean
	public JobExecutionListener listener() {
		return new JobStatusListerner();
	}

}
