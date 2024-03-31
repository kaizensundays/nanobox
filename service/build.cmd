
set JAVA_HOME=%JAVA_8_HOME%

gradle clean build -i -x allTests -x jvmTest -x test
