package com.brax_gunn;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	int rowNumber = 0;
	
	@Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
		String line = value.toString();
		
		if(line.trim().isEmpty())
			return;
		
		rowNumber++;
		
		System.out.println("key: " + key + " | value: " + value);
		
		String[] numbers = line.split(" ");
		
		for(String number: numbers) {
			context.write(new Text("row_"+rowNumber), new IntWritable(Integer.parseInt(number)));
		}
				
    }
}
