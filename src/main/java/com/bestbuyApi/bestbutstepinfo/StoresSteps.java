package com.bestbuyApi.bestbutstepinfo;

import com.bestbuyApi.constants.EndPoints;
import com.bestbuyApi.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class StoresSteps {
    @Step
    public ValidatableResponse getStores() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then()
                .statusCode(200);
    }

    @Step("Creating stores with name : {0},type : {1},address : {2},address2 : {3},city : {4},state : {5},zip : {6},lat : {7},lng : {8},hours : {9},services : {10},")
    public ValidatableResponse createStores(String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, HashMap<Object, Object> services) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post(EndPoints.GET_ALL_STORES)
                .then();
    }

    @Step("Getting product information with id : {0}")
    public HashMap<String, ?> getStoresInformationById(int storesId) {
        return SerenityRest.given().log().all()
                .pathParam("storesId", storesId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("Updating stores with name : {0},type : {1},address : {2},address2 : {3},city : {4},state : {5},zip : {6},lat : {7},lng : {8},hours : {9},services : {10},")
    public ValidatableResponse updateStores(int storesId,String name, String type, String address, String address2, String city, String state, String zip, int lat, int lng, String hours, HashMap<Object, Object> services) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("storesId",storesId)
                .body(storesPojo)
                .when()
                .patch(EndPoints.UPDATE_STORES_BY_ID)
                .then();
    }
    @Step("Deleting stores information with product id : {0}")
    public ValidatableResponse deleteStoresById(int storesId){
        return  SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("storesId",storesId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();
    }
    @Step("Getting stores information with stores id : {0}")
    public ValidatableResponse getStoresById(int storesId){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("storesId",storesId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then();
    }
}
