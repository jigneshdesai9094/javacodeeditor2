#1.way
# fh = open("file4.txt","r")
# content = fh.read()
# content=content.replace("gujarat","gujrat")
# fh.close()
# fh = open("file4.txt","w")
# fh.write(content)
# fh.close()

#2.way
# fh = open("file4.txt","r")
# c = fh.readline()
# content=""
# while(len(c)>0):
#      content+=c.replace("gujrat","gujarat")
#      c=fh.readline()
# fh.close()
# fh = open("file4.txt","w")
# fh.write(content)
# fh.close()

fh = open("file6.txt","r+")
s = fh.read()
ind = s.find("gujarat")
while(ind!=-1):
    print(ind)
    fh.seek(ind)
    fh.write("gujrat ")
    ind = s.find("gujarat",ind+1)
fh.close()
print(s)