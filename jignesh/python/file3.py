import sys
myfile=open(sys.argv[1],"r")

data=myfile.read()
print(data)
splitdata=data.split(" ")
myfile.close()
myfile=open(sys.argv[2],"w+")

for i in splitdata:
    myfile.write(i+' ')

myfile.seek(0)

newdata=myfile.read()

print("new data")

print(newdata)
