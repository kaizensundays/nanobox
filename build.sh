#!/bin/bash

export JAVA_HOME=$JAVA_17_HOME

gradle clean build -i -x allTests -x jvmTest -x test

echo Done

read
