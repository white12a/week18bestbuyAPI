package com.bestbuyApi.bestBuyTest;

import com.bestbuyApi.bestbutstepinfo.StoresSteps;
import com.bestbuyApi.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.testbase.TestBase;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresTest extends TestBase {
    static String name = "Robinhood" + TestUtils.getRandomValue();
    static String type = "smallbox";
    static String address = "105 County grove B2";
    static String address2 = "";
    static String city = "Roseville";
    static String state = "NY";
    static String zip = "34523";
    static double lat = 56.87364;
    static double lng = -67.563452;
    static String hours = "Mon: 10-9; Tue 10-9;";
    static int storesId;

    @Steps
    StoresSteps storesSteps;

    @Title("This will get all the stores")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.getStores().log().all().statusCode(200);
    }

    @Title("This will create a new stores")
    @Test
    public void test002() {
        HashMap<Object, Object> services = new HashMap<>();
        ValidatableResponse response = storesSteps.createStores(name, type, address, address2, city, state, zip, (int) lat, (int) lng, hours, services);
        response.log().all().statusCode(201);
        storesId = response.log().all().extract().path("id");
        System.out.println(storesId);

    }

    @Title("Verify if the information was added to store")
    @Test
    public void test003() {
        HashMap<String, ?> storeMap = storesSteps.getStoresInformationById(storesId);
        Assert.assertThat(storeMap, hasValue(name));
    }

    @Title("Update the stores information and verify the updated information")
    @Test
    public void test004() {
        name = name + "_updated";
        HashMap<Object, Object> services = new HashMap<>();
        storesSteps.updateStores(storesId, name, type, address, address2, city, state, zip, (int) lat, (int) lng, hours, services).log().all().statusCode(200);
        HashMap<String, ?> storeMap = storesSteps.getStoresInformationById(storesId);
        Assert.assertThat(storeMap, hasValue(name));
    }
    @Title("Delete the store and verify the store is deleted")
    @Test
    public void test005(){
        storesSteps.deleteStoresById(storesId).log().all().statusCode(200);
        storesSteps.getStoresById(storesId).log().all().statusCode(404);

    }
}


