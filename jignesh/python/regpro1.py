import re
#. any character
string = "hello hii"
s = re.search("h..",string)
print(s.group())

#^start witht
s = re.findall("^h..",string)
print(s)

#$ end with
s=re.findall("^h......ii$",string)
print(s)

#+ one or match many character
s=re.findall(r"h\w+",string)
print(s)

string="Hello Dello Mello xello"
s = re.findall("[A-Z]ello",string)
print(s)

s=re.finditer("[A-Z]ello",string)
for i in s:
    print(i)


