package com.bestbuyApi.bestBuyTest;

import com.bestbuyApi.bestbutstepinfo.UtilitiesSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.testbase.TestBase;

@RunWith(SerenityRunner.class)
public class UtilitiesTest extends TestBase {
    @Steps
    UtilitiesSteps utilitiesSteps;

    @Title("This will get the utilities version")
    @Test
    public void test001(){
        ValidatableResponse response=utilitiesSteps.getUtilitiesVersion().log().all().statusCode(200);


    }

    @Title("This will get the utilities healthcheck")
    @Test
    public void test002(){
        utilitiesSteps.getHealthCheck().log().all().statusCode(200);
    }
}
