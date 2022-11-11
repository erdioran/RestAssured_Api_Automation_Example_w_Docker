
package com.erdioran.testCases;


import com.aventstack.extentreports.ExtentTest;

import com.erdioran.PojoJson.CreateBusinessAccount;
import com.erdioran.PojoJson.CreateIndividualAccount;
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
import static com.erdioran.utils.ExcelManager.*;
import static com.erdioran.utils.DataManager.getData;


public class CreateAccounts extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(CreateAccounts.class);

    @BeforeMethod()
    public void beforeCreateUser(ITestContext context) {
        ExtentTest test = ExtentTestManager.getNode();
        test.assignCategory("Create Account Tests");

    }

    @Test(description = "Create Business Account - 200", priority = 1)
    public void createBusinessAccount200() {



        CreateBusinessAccount account = new CreateBusinessAccount();
        account.setName(getData("businessAccount.name"));
        account.setEmail(getData("businessAccount.email"));
        account.setNationality(getData("businessAccount.nationality"));
        account.setDob(getData("businessAccount.dob"));
        account.setType(getData("businessAccount.type"));
        account.setCountryCode(getData("businessAccount.country_code"));

        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        log().body().
                        body(account).
                        when().
                        post(getData("url.arf_url") + getData("url.version") + getData("url.accounts") + getData("url.business")).
                        then().
                        log().ifValidationFails().
                        assertThat().
                        statusCode(Integer.parseInt(getData("status.ok"))).
                        body("status", Matchers.equalTo(getData("status_types.status_success"))).
                        body("data.type", Matchers.equalTo(getData("status_types.type_business"))).
                        extract().response();

        String responseBody = response.getBody().asString();
        LOGGER.info("responseBody: " + responseBody);

        setExcel("Data",response.getBody().jsonPath().getString("data.id"), 1, 0);

    }


    @Test(description = "Create Individual Account - 200", priority = 2)
    public void createIndividualAccount200() {


        CreateIndividualAccount account = new CreateIndividualAccount();
        account.setFirstName(getData("individualAccount.first_name"));
        account.setLastName(getData("individualAccount.last_name"));
        account.setEmail(getData("individualAccount.email"));
        account.setNationality(getData("individualAccount.nationality"));
        account.setDob(getData("individualAccount.dob"));
        account.setType(getData("individualAccount.type"));
        account.setIdentificationType(getData("individualAccount.identification_type"));
        account.setIdentificationValue(getData("individualAccount.identification_value"));
        account.setCountryCode(getData("individualAccount.country_code"));


        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        log().body().
                        body(account).
                        when().
                        post(getData("url.arf_url") + getData("url.version")+ getData("url.accounts")).
                        then().
                        log().ifValidationFails().
                        assertThat().
                        statusCode(Integer.parseInt(getData("status.ok"))).
                        body("status", Matchers.equalTo(getData("status_types.status_success"))).
                        body("data.type", Matchers.equalTo(getData("status_types.type_individual"))).
                        extract().response();

        String responseBody = response.getBody().asString();
        LOGGER.info("responseBody: " + responseBody);

        setExcel("Data",response.getBody().jsonPath().getString("data.id"), 1, 1);

    }


}
