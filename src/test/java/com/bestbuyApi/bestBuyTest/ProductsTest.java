package com.bestbuyApi.bestBuyTest;

import com.bestbuyApi.bestbutstepinfo.ProductsSteps;
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
public class ProductsTest extends TestBase {
    static String name = "laptopi"+ TestUtils.getRandomValue();
    static String type = "remote" + TestUtils.getRandomValue();
    static int price = 20;
    static int shipping = 10;
    static String upc = "65656";
    static String description = "dell 3456";
    static String manufacturer = "dell";
    static String model = "extra";
    static String url = "dell.com";
    static String image = "img";
    static int productId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will get all products list")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.getProduct().log().all().statusCode(200);
    }

    @Title("This will create a new product")
    @Test
    public void test002() {
        ValidatableResponse response = productsSteps.createProductList(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId=response.log().all().extract().path("id");
        System.out.println(productId);
    }

    @Title("verify if Product is created")
    @Test
    public void test003(){
        HashMap<String,Object>productMap=productsSteps.getProductInformationById(productId);
        Assert.assertThat(productMap, hasValue(name));
       /* productId=(int)productMap.get("id");
        System.out.println(productId);
*/
    }
    @Title("Update the product information and verify the updated information")
    @Test
    public void test004(){
        name = name + "_updated";
        productsSteps.updateProductList(productId,name,type,price,shipping,upc,description,manufacturer,model,url,image).log().all().statusCode(200);
        HashMap<String,Object> productMap = productsSteps.getProductInformationById(productId);
        Assert.assertThat(productMap,hasValue(model));

    }
    @Title("Delete the product and verify if the product is deleted")
    @Test
    public void test005(){
        productsSteps.deleteProductById(productId).log().all().statusCode(200);
        productsSteps.getProductById(productId).log().all().statusCode(404);

    }


}
