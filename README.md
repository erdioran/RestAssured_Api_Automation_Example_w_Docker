<b> <h1>   ErdiOran API project with Rest Assured  </h1> </b>  


## Run 

Run this suite -> [/testng.xml](https://github.com/erdioran/erdioran_api_automation/blob/master/testng.xml)

## Report 
It is created inside this file after the test is complete -> `/extent-reports/`

## Project Detail

- It takes the token from the [data.json](https://github.com/erdioran/erdioran_api_automation/blob/master/src/test/resources/data.json) file, sends it to the request header with the [BeforeTest](https://github.com/erdioran/erdioran_api_automation/blob/master/src/test/java/com/erdioran/base/BaseTest.java) method.

- It reads data such as url, test data, error messages from [data.json](https://github.com/erdioran/erdioran_api_automation/blob/master/src/test/resources/data.json) using [DataManager](https://github.com/erdioran/erdioran_api_automation/blob/master/src/main/java/com/erdioran/utils/DataManager.java). 

- The test cases are in the [testCases](https://github.com/erdioran/erdioran_api_automation/tree/master/src/test/java/com/erdioran/testCases) file.

- It creates the service body it will request using the [CreateBusinessAccount](https://github.com/erdioran/erdioran_api_automation/blob/master/src/test/java/com/erdioran/PojoJson/CreateBusinessAccount.java), [CreateIndividualAccount](https://github.com/erdioran/erdioran_api_automation/blob/master/src/test/java/com/erdioran/PojoJson/CreateIndividualAccount.java), [PostPayout](https://github.com/erdioran/erdioran_api_automation/blob/master/src/test/java/com/erdioran/PojoJson/Payout/PostPayout.java) class.

- When it creates "Business" and "Individual" accounts, it writes the returned account IDs to excel. While testing the "Payout" service, it reads the IDs from excel. Excel reader and writer methods: [ExcelManager](https://github.com/erdioran/erdioran_api_automation/blob/master/src/main/java/com/erdioran/utils/ExcelManager.java)

-  Test report settings are in [ExtentManager](https://github.com/erdioran/erdioran_api_automation/blob/master/src/main/java/com/erdioran/utils/ExtentManager.java) and [ExtentTestManager](https://github.com/erdioran/erdioran_api_automation/blob/master/src/main/java/com/erdioran/utils/ExtentTestManager.java) files under  "/src/main/java/com/erdioran/utils" file.

-  Docker configuration file -> [Dockerfile](https://github.com/erdioran/erdioran_api_automation/blob/master/Dockerfile)

## Docker Build & Container Run

```sh
$ docker pull maven:3.6.3-jdk-8
$ docker build -t <imaganame>:1 .
$ docker run <imaganame>:1

$ docker ps -a
$ docker commit <containerId>
$ docker tag <containerId> <dockerUserName>/java8-3.6.3-testdep:latestdocker 
$ docker login 
$ push <dockerUserName>/arfapiimage:latest
```

|         | VERSION |
| -------| ----- |
| Rest Assured   | 5.0.1  |
| TestNG   | 7.4.0  |
| Extentreports   | 4.0.9  |
