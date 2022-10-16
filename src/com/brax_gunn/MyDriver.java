package com.brax_gunn;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{

            // hdfs input and output folder path
            String iPath = "/home/hduser/eclipse-workspace/MapReduce_RowWiseMaxVal/data/input_data";
            String oPath = "/home/hduser/eclipse-workspace/MapReduce_RowWiseMaxVal/data/output_data";

            Path inputPath = new Path(iPath);
            Path outputPath = new Path(oPath);
            
            // Every MapReduce program is a Job
            // Job object needs a Configuration object

            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf);

            job.setMapperClass(MyMapper.class);
            job.setReducerClass(MyReducer.class);
            job.setJarByClass(MyDriver.class);

            // Defining Job output key, val data type
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
            // job.setNumReduceTasks(2);            

            FileInputFormat.addInputPath(job, inputPath);
            FileOutputFormat.setOutputPath(job, outputPath);

            // hadoop does not allow job to run, if output folder exists
            // to counter it, delete output folder if it exists
            // true: to delete recursively
            outputPath.getFileSystem(conf).delete(outputPath, true);
            
            // Execute the job, and then exit
            System.exit(job.waitForCompletion(true) ? 0: 1);
            
    }
    
}