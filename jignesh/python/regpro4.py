import re
string = '''
  url 1 : http://www.google.com
  url 2: http://myportfolio.in
  url 3:https://gvp.org
  url 4:https://www.hello1234.com
'''

pattern = r"(http|https)://(www.)?\w+.\w+"
match = re.finditer(pattern,string)
for m in match:
    print(m.group())