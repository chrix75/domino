#!/usr/bin/env python
#
# This script prepares a template file (cql script) by replacing uuid variable.
#
# The arguments of the script are tcql files and the result is cql scripts.

import sys
import uuid
import os

def replace_var(line):
    previous_line = line
    new_line = ""
    
    while True:
        new_line = previous_line.replace("$uuid", '"%s"' % str(uuid.uuid4()), 1)
        if new_line == previous_line:
            break

        previous_line = new_line
        
    return new_line

def process_tcql_script(tcql_filename):
    filename, file_extension = os.path.splitext(tcql_filename)
    new_cql_script = filename + ".cql"

    cql = open(new_cql_script, "w")

    for line in open(tcql_filename).readlines():
        updated_line = replace_var(line)
        cql.write(updated_line)

    cql.close()

args = sys.argv[1:]
if len(args) == 0:
    print >> sys.stderr, "No tcql files"
    sys.exit(1)

for f in args:
    process_tcql_script(f)

