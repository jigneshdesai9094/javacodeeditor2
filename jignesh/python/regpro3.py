import re
string = '''
  email 1 : abc@gmail.com
  email 2 : a.bc@gmail.com
  email 3 : abcpqr@yahoo.in
  email 4 : 0.007@gujaratvidyapith.org
'''

pattern = r"[a-z.]+@\w+.\w+"
match = re.findall(pattern,string)
for m in match:
    print(m) 