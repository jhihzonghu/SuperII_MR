#! /bin/bash
export _JAVA_OPTIONS=-Xmx10000M
tar -C /data/ -zxvf /data/EHC_1st.tar.gz 
hadoop dfs -mkdir /tmp/Team06/
hadoop dfs -put /data/EHC_1st_round.log /tmp/Team06/ &
hadoop jar ./Team06.jar
sort -n -r -k2   /data/round1/part-r-00000 -o /data/round1/output.txt
cd `pwd` 
awk '{length(NR)==1?no="0"NR:no=NR; print no",pid"no" "$1}' /data/round1/output.txt > ./Team06_result.txt
head -n 20 ./Team06_result.txt

