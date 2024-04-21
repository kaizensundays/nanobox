
set JAVA_HOME=%JAVA_11_HOME%

gradle clean build -i -x allTests -x jvmTest -x test
