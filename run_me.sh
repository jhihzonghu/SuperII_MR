#presudo code
#multi job 
# 1. run put file to hdfs and staticMR in the same time.
# 2. sort file and wirte new file 
#  sort -n -r -k2 -t $'\t' part-r-00000 -o output.txt
# 3.cat Top20
