#!/bin/bash

SCRIPT_DIR=$(dirname $0)
source $SCRIPT_DIR/procs.sh

mvn -f $SCRIPT_DIR/pom.xml clean test-compile org.pitest:pitest-maven:mutationCoverage -Dpitest.mutator=RETURN_VALS
openUrl target/pit-reports/index.html
