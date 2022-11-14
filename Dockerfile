FROM erdioran/java8-3.6.3-testdep
COPY src  home/apiframework/src
COPY extent-reports/Test_Report_Arf.html  home/apiframework/extent-reports/Test_Report_Arf.html
COPY pom.xml	home/apiframework/pom.xml
COPY testng.xml	home/apiframework/testng.xml
WORKDIR home/apiframework
ENTRYPOINT mvn clean test