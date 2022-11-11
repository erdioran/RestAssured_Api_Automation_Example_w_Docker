
package com.erdioran.testCases;


import com.aventstack.extentreports.ExtentTest;
import com.erdioran.base.BaseTest;
import com.erdioran.utils.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.erdioran.utils.DataManager.getData;


public class GetAccount extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(GetAccount.class);


    @BeforeMethod()
    public void beforeGetUser(ITestContext context) {
        ExtentTest test = ExtentTestManager.getNode();
        test.assignCategory("Get User Tests");

    }

    @Test(description = "Get Retrieve Account 200", priority = 1)
    public void getRetrieveAccount200() {



        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        when().
                        get(getData("url.arf_url") + getData("url.version")+ getData("url.accounts")+"/"+ getData("accounts.account_id")).
                        then().
                        log().ifValidationFails().
                        assertThat().
                        statusCode(Integer.parseInt(getData("status.ok"))).
                        body("status", Matchers.equalTo(getData("status_types.status_success"))).
                        body("data.id", Matchers.equalTo(getData("accounts.account_id"))).
                        extract().response();

        String responseBody = response.getBody().asString();
        LOGGER.info("responseBody: " + responseBody);

    }

}
