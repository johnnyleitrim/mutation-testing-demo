Mutation Testing Demo
=====================

[![Build Status](https://travis-ci.org/johnnyleitrim/mutation-testing-demo.svg?branch=master)](https://travis-ci.org/johnnyleitrim/mutation-testing-demo)
[![Coverage Status](https://coveralls.io/repos/github/johnnyleitrim/mutation-testing-demo/badge.svg)](https://coveralls.io/github/johnnyleitrim/mutation-testing-demo)

A quick description of mutation testing and examples using [PIT](http://pitest.org/)

What is Mutation Testing?
=========================
Mutation testing can be used to evaluate the effectiveness of unit tests.

The idea is to mutate the source code by introducing faults and to check whether the existing unit tests are capable of detecting the faults (by failing).

Mutation testing frameworks generally work as follows:
* Your project is built and the unit tests are run to ensure your project is currently stable.
* It will then automatically apply a mutation operator (eg: remove a line of code, replace an addition with a subtraction, invert a boolean condition) on a single method of your code and re-run all unit tests to check if at least one of the test cases fails.
* If some tests fail, this means they were able to reveal the broken code.
* If no tests fail, this is a sign that there are gaps in your tests.
* It will repeat the mutation/testing process multiple times for different types of mutation operators.

Why do we need Mutation Testing?
================================
**Isn't code coverage/branch coverage enough? No.**

To demonstrate, do the following:
* Run `mvn clean site` on the project.
* Open the code coverage report (`target/site/cobertura/index.html`).  All branch coverage is 100%.
* Open the `SimpleNumberIsPositiveTest.java` unit test.  You will see that it only has tests for positive and negative numbers, but there is no test for 0 (that test is currently commented out).
* This means that if the condition in `SimpleNumber.isPositive()` method is accidentially changed from `>=` to `>`, there is no test that would catch that.

Traditional test coverage (i.e line, statement, branch etc) measures only which code is executed by your tests. It does not check that your tests are actually able to detect faults in the executed code. **It is therefore only able to identify code that is definitely not tested**.

Examples
========
This project contains a few examples of unit tests that have 100% branch coverage.  However, running mutation testing on them will show that there are gaps in the tests.  Each unit test has commented out sections that are the "missing" tests to make the mutation tests pass.

Demo 1
------
Run `testBoundaryMutations.sh`.  This script will run the mutation tests and automatically opens the PIT report (located at `target/pit-reports/index.html`)

See that there is a mutation that lived (i.e. no test failed after the code was mutated).  The mutation that lived is that the condition in `SimpleNumber.isPositive()` method was changed from `>=` to `>`, but no unit test failed.  This means we are missing a test case for the `0` bondary condition.

### What to do
* Uncomment the `testBoundary` test method in `SimpleNumberIsPositiveTest.java`
* Run `testBoundaryMutations.sh`
This time, you will see that all mutations were killed, as the `testBoundary` test failed when the `isPositive` method was mutated.

Demo 2
------
Run `testReturnValuesMutations.sh`.  This script will run the mutation tests and automatically opens the PIT report (located at `target/pit-reports/index.html`)

See that there is a mutation that lived (i.e. no test failed after the code was mutated).  The mutation that lived is that the `SimpleNumber.increment()` method was changed to always return `null`, but no unit test failed.  This means our unit tests are not checking the return value of the `increment` method.

### What to do
* Uncomment the `assertEquals` lines in `SimpleNumberIncrememterTest.java`
* Run `testReturnValuesMutations.sh`
This time, you will see that all mutations were killed, as `SimpleNumberIncrememterTest` now checks the return value of the `increment` method.

Should it be run as part of my CI build?
========================================
No, not yet.  While mutation testing is very useful and would be beneficial as part of a CI build, it can have some drawbacks.

Mutation testing is a computationally expensive process and can take quite some time depending on the size of your codebase and the quality and speed of your test suite. PIT (*the mutation testing framework used in this project*) is fast compared to other mutation testing systems, and also has `withHistory` and `scmMutationCoverage` goals which can run the mutation tests on new code only, improving execution times.

There is also the (rare) possibility false positives.  It is possible that certain mutations don't actually change the behaviour of the code, and the mutation testing framework incorrectly expects that a unit test should fail.  This is called an equivalent mutation.

Equivalent Mutations
--------------------
To see an example of an equivalent mutation, run `testMathMutations.sh`.  This script will run the mutation tests and automatically opens the PIT report (located at `target/pit-reports/index.html`)

See that there is a mutation that lived (i.e. no test failed after the code was mutated).  The mutation that lived is that the `SimpleNumber.multiplyIfOnes()` method was changed to do division instead of multiplication.  That is
```java
return new SimpleNumber(value * otherNumber.value);
```
was changed to:
```java
return new SimpleNumber(value / otherNumber.value);
```
No unit test failed, and this is considered an error.

The problem here is that `1 * 1` is the same value as `1 / 1`, so the mutation is equivalent to the original code.  This means there is no chance that a unit test will fail, because the mutated code works in exactly the same way as the original code.  The mutation testing framework is incorrectly expecting a unit test failure in this case.

How do I add Mutation Testing to my own project?
================================================
Add the `pitest-maven` plugin to your `pom.xml` file:
```xml
<plugin>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-maven</artifactId>
    <version>1.1.11</version>
</plugin>
```
Now run the plugin on your project:
```
mvn -DtimestampedReports=false org.pitest:pitest-maven:mutationCoverage
```
After the mutation test has completed, a report will be available at `target/pit-reports/index.html`
