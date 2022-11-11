package com.erdioran.listener;


import com.erdioran.utils.ExtentManager;
import com.erdioran.utils.ExtentTestManager;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.List;
import java.util.stream.Collectors;

public class ExtentReportListener implements ITestListener {


    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getNode().pass("Test Passed");

    }

    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getNode()
                .fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {

        List<String> dependingTestList = result.getSkipCausedBy().stream().map(ITestNGMethod::getMethodName)
                .collect(Collectors.toList());

        if (result.wasRetried()) {

            ExtentManager.getExtentReports().removeTest(ExtentTestManager.getNode());
        } else {
            String testMethodNameOrDescription =
                    StringUtils.isNotBlank(result.getMethod().getDescription()) ? result.getMethod().getDescription()
                            : result.getMethod().getMethodName();
            com.aventstack.extentreports.ExtentTest node = ExtentTestManager.getTest().createNode(testMethodNameOrDescription);
            node.skip(String.format("Skipped. Depending test %s failed.", dependingTestList));
        }


    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {
        ExtentTestManager.startTest(context.getName(), "");
    }

    public void onFinish(ITestContext context) {
        ExtentManager.getExtentReports().flush();

    }

}
