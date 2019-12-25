# jmtrace

A simple memory access tracer for java programs.

## Requirements
* Orace JDK / OpenJDK 8
* Maven 3.0

## Build and Run
Build with maven, and it will resolve the dependencies and generate a jar file.

    mvn package
    
Then you should be able to run with:

    jmtrace -jar something.jar

Tested on Ubuntu 18.04 and Windows 10.

## Resources
1. https://asm.ow2.io/
2. [Java 6 Instrument](https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html)