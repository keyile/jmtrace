# jmtrace

A simple memory access tracer for java programs.

## Requirements
* Orace JDK / OpenJDK 8
* Maven 3.0

Tested on Ubuntu 19.10 and Windows 10.

## Build and Run
Build with maven, and it will resolve the dependencies and generate a jar file.

    mvn package
    
Then you should be able to run the tests:

    jmtrace -jar target\traceagent-tests.jar
    jmtrace -cp target\traceagent-tests.jar test.TestType
    jmtrace -cp target\traceagent-tests.jar test.TestThread
    
To run your own programs, `jmtrace` has the same usage as `java`.

## Resources
1. https://asm.ow2.io/
2. [Java 6 Instrument](https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html)