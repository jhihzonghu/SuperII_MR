package com.cgu.superii;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StaticMaper extends Mapper<Object, Text, Text, IntWritable> {
   // private String PID ;
    private String[] PIDList;
    private int TotalPrice,count=0;
	@Override
	protected void map(Object key, Text value,Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//super.map(key, value, context);
	    PIDList = Regexp(value.toString()).replace("plist=", "").split(",");
		if(PIDList.length%3==0)
		{
			do{
			 String PID = PIDList[count];
		     int TotalPrice = Integer.valueOf(PIDList[count+1])*Integer.valueOf(PIDList[count+2]);
		     System.out.println("PID:  \t "+PID +"\tTotalPrice:  \t "+TotalPrice);
		     context.write(new Text(PID), new IntWritable(TotalPrice));
			  count+=3;
			}while(count>PIDList.length);
			count = 0 ;
		}
	}
	private String Regexp(String string) {
		// TODO Auto-generated method stub
		String Parser = "plist=[\\d*,]*";
		Pattern SoldDataPattern =Pattern.compile(Parser); 
		Matcher GoodMatcher =SoldDataPattern.matcher(string);
		if(GoodMatcher.find())
		{
			return GoodMatcher.group();
		}else{
			return "No Matcher";
		}
		
	}
  
}
