package com.brax_gunn;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	@Override
	protected void reduce(Text rowNumber, Iterable<IntWritable> numbers, Context context) throws IOException, InterruptedException {
		
		int maxValue = Integer.MIN_VALUE;
		for(IntWritable number: numbers) {
			if(number.get() > maxValue)
				maxValue = number.get();
		}
		context.write(rowNumber, new IntWritable(maxValue));
		
	}
}
