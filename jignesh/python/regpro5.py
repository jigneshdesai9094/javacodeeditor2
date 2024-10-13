import re
string ='''
  abc pqr.
  ABC. PQR akash
  12.37 456.
'''
pattern = r"\d+\.\d+"
print(pattern)
match = re.findall(pattern,string)
print(match)
