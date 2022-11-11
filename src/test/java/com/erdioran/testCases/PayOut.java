
package com.erdioran.testCases;


import com.aventstack.extentreports.ExtentTest;
import com.erdioran.PojoJson.Payout.Amount;
import com.erdioran.PojoJson.Payout.Meta;
import com.erdioran.PojoJson.Payout.PayoutMethod;
import com.erdioran.PojoJson.Payout.PostPayout;
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
import static com.erdioran.utils.ExcelManager.*;


public class PayOut extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(PayOut.class);

    @BeforeMethod()
    public void beforeCreateUser(ITestContext context) {
        ExtentTest test = ExtentTestManager.getNode();
        test.assignCategory("PayOut Tests");

    }

    @Test(description = "Post PayOut - 200", priority = 1)
    public void postPayOut200() {

        PayoutMethod payoutMethod = new PayoutMethod();
        payoutMethod.setAccountNumber(getData("payout.account_number"));
        payoutMethod.setType(getData("payout.type"));
        payoutMethod.setBankCode(getData("payout.bank_code"));

        Amount amount = new Amount();
        amount.setCurrency(getData("payout.currency"));
        amount.setValue(Integer.valueOf(getData("payout.value")));

        Meta meta = new Meta();
        meta.setRelationship(getData("payout.relationship"));
        meta.setSourceOfFund(getData("payout.source_of_fund"));
        meta.setPurpose(getData("payout.purpose"));


        PostPayout payout = new PostPayout();
        payout.setExternalId(getData("payout.external_id"));
        payout.setSenderId(getCellData(1,0));
        payout.setBeneficiaryId(getCellData(1,1));
        payout.setPayoutMethod(payoutMethod);
        payout.setAmount(amount);
        payout.setMeta(meta);

        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        log().body().
                        body(payout).
                        when().
                        post(getData("url.arf_url") + getData("url.version")+ getData("url.payouts")).
                        then().
                        log().ifValidationFails().
                        assertThat().
                        statusCode(Integer.parseInt(getData("status.ok"))).
                        body("status", Matchers.equalTo(getData("status_types.status_success"))).
                        body("data.sender.id", Matchers.equalTo(getCellData(1,0))).
                        body("data.beneficiary.id", Matchers.equalTo(getCellData(1,1))).
                        extract().response();

        String responseBody = response.getBody().asString();
        LOGGER.info("responseBody: " + responseBody);

    }



}
