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
		Configuration conf = new Configuration();
		conf.set("mapreduce.jobtracker.address", "local");
		//conf.set("mapred.child.java.opts", "-Xmx200m");
		conf.set("mapred.job.tracker", "local");
		//conf.set("mapreduce.task.io.sort.mb", "100");
		conf.set("fs.defaultFS","file:///");
		conf.set("fs.default.name","file:///");
		conf.set("dfs.replication", "1");
		Job job = new Job(conf, "xxxx");
		job.setJarByClass(App.class);
		job.setMapperClass(StaticMaper.class);
		job.setCombinerClass(StaticReduer.class);
		job.setReducerClass(StaticReduer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, 
				"/data/EHC_1st_round.log");
		FileOutputFormat.setOutputPath(job, new Path("/data/round1"));
		job.waitForCompletion(true);

	}

}
