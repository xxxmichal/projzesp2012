#!/usr/bin/env python
#./insert_DiyDbAdapter.py hello triggers text '""' int
import sys, os


def replacements(strarg):
  return strarg.replace('{lowercase}', word.lower())\
    .replace('{uppercase}', word.upper())\
    .replace('{dbtype}', dbtype)\
    .replace('{default_value}', default_value.upper())\
    .replace('{vartype}', vartype)\
    .replace('{cmp}', cmpa)

FILE_IN = '/home/lab/projzesp2012/diyapplib/src/com/diyapp/lib/DiyDbAdapter.java'
FILE_OUT = '/home/lab/projzesp2012/diyapplib/src/com/diyapp/lib/DiyDbAdapter.java_new'

nlines = []
word, section, dbtype, default_value, vartype, cmpa = sys.argv[1:]



for l in open(FILE_IN, 'r').readlines():
  if l.find('// TEMPLATE: ') != -1:
    print l
    nl = l.replace('// TEMPLATE: ','')
    nl = replacements(nl)
    nlines.append(nl)
  elif l.find('// TEMPLATE_'+section+': ') != -1:
    print l
    nl = l.replace('// TEMPLATE_'+section+': ','')
    nl = replacements(nl)
    nlines.append(nl)
  nlines.append(l)

f = open(FILE_OUT, 'w').writelines(nlines)

os.system('kompare %s %s' % ( FILE_OUT, FILE_IN ))





