import re
string='''
   mobile no 1 : 1234567890
   mobile no 2 : 123-456-7890
   mobile no 3 : (123)-456-7890
'''

pattern = r"\(?\d{3}\)?-?\d{3}-?\d"
match = re.findall(pattern,string)
for m in match:
    print(m) 