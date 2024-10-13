import re
fh = open("file7.txt")
string = fh.read()
pattern ="credit hours- (\\d+)\npoint - (\\d+.\\d+)"
data= re.findall(pattern,string)
print(data)
tp=0
tc = 0
for i in data:
    tp +=(int(i[0])*float(i[1]))
    tc += int(i[0])
print("CGPA : ",end=" ")
print(tp/tc)
fh.close()