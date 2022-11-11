package com.erdioran.base;

import com.aventstack.extentreports.ExtentTest;
import com.erdioran.listener.ExtentReportListener;
import com.erdioran.utils.ExtentTestManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static com.erdioran.utils.DataManager.getData;

@Listeners(ExtentReportListener.class)
public abstract class BaseTest extends ExtentReportListener{


    public static RequestSpecification requestSpec;

    @BeforeMethod(alwaysRun = true)
    public void startReport(Method method, ITestResult result, ITestContext context) {


        String nodeName =
                StringUtils.isNotBlank(result.getMethod().getDescription()) ? result.getMethod().getDescription() : method.getName();
        ExtentTest node = ExtentTestManager.getTest().createNode(nodeName);
        ExtentTestManager.setNode(node);
        ExtentTestManager.info("Test Started");


    }

    @AfterMethod(alwaysRun = true)
    public void finishTest(ITestResult result, ITestContext context) {
        if (!result.isSuccess()) {
            context.setAttribute("previousTestStatus", "failed");
        } else {
            context.setAttribute("previousTestStatus", "passed");
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

    }

    @BeforeTest(alwaysRun = true)
    public void apiHeaders() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Arf-Client-ID", getData("accessData.arfClientId"));

        requestSpec = builder.build();
    }
}
