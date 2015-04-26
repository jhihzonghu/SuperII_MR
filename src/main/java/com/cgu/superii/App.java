package com.cgu.superii;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, Exception, Exception {
		long StartTime = System.currentTimeMillis();
		long AverageTime = 0;
		Configuration conf = new Configuration();
		Job job = new Job(conf, "xxxx");
		job.setJarByClass(App.class);
		job.setMapperClass(StaticMaper.class);
		job.setCombinerClass(StaticReduer.class);
		job.setReducerClass(StaticReduer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// TODO: specify input and output DIRECTORIES (not files)
		
		FileInputFormat.setInputPaths(job, 
				"/opt/hadoop/input2/EHC_1st_round.log");
		FileOutputFormat.setOutputPath(job, new Path("/opt/hadoop/output2/EHC_1st_round.log"));
		job.waitForCompletion(true);
		long ProcessTime = System.currentTimeMillis() - StartTime;
		AverageTime += ProcessTime;
		System.out.print(AverageTime/1000);

	}

}
