package com.cgu.superii;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StaticReduer extends Reducer<Text,IntWritable,Text, IntWritable> {
  int sum =0 ; 
	@Override
	protected void reduce(Text PID, Iterable<IntWritable> EveryRowPrice,Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	//	super.reduce(arg0, arg1, arg2);
		for(IntWritable AccountEveryRow:EveryRowPrice)
		{
			sum += AccountEveryRow.get() ; 
		}
		//System.out.println("PID: \t"+ PID + "TotalPrice: \t"+sum);
		context.write(PID, new IntWritable(sum));
		sum = 0 ; 
	}

}
