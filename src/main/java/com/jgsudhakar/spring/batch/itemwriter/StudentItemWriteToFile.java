/**
 * 
 */
package com.jgsudhakar.spring.batch.itemwriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.jgsudhakar.spring.batch.dto.StudentResponseDto;

import lombok.extern.log4j.Log4j2;

/**
 * @Author : Sudhakar Tangellapalli
 * @File   : com.jgsudhakar.spring.batch.itemwriter.StudentItemWriteToFile.java
 * @Date   : 2021-10-05
 */
@Component
@Log4j2
public class StudentItemWriteToFile implements ItemWriter<StudentResponseDto>{

	@Override
	public void write(List<? extends StudentResponseDto> items) throws Exception {
		log.info(":: Student Item Write to File::");
		
	}

}
