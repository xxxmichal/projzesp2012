#!/usr/bin/env python
#./insert_DiyDbAdapter.py hello triggers text '""' int
import sys, os


def replacements(strarg):
  return strarg.replace('{lowercase}', word.lower())\
    .replace('{uppercase}', word.upper())\
    .replace('{field}', field)\
    .replace('{diyget}', diyget)\
    .replace('{retrieve}', retrieve)

FILE_IN = 'Kreator/src/com/diyapp/kreator2/DiyEditActionsActivity.java'
FILE_OUT = 'Kreator/src/com/diyapp/kreator2/DiyEditActionsActivity.java_new'

nlines = []
word, field, diyget, retrieve = sys.argv[1:]


for l in open(FILE_IN, 'r').readlines():
  if l.find('// TEMPLATE: ') != -1:
    print l
    nl = l.replace('// TEMPLATE: ','')
    nl = replacements(nl)
    nlines.append(nl)
  nlines.append(l)

f = open(FILE_OUT, 'w').writelines(nlines)

#os.system('kompare %s %s' % ( FILE_OUT, FILE_IN ))

import shutil
shutil.copy( FILE_OUT, FILE_IN )






