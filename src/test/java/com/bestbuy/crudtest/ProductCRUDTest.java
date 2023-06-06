package com.bestbuy.crudtest;

import com.bestbuy.steps.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductCRUDTest extends TestBase {

    static int id;
    String name = "Bowe Dry Cleaning Machine";
    String type = "Dry Cleaning Machine";
    int price = 25000;
    int shipping = 150;
    String upc = "XYZ";
    String description = "Dry Cleaning Machine for Garments.";
    String manufacturer = "Bowe";
    String model = "P525";
    String url = "www.bowe.com";
    String image = "bowep525.jpg";

    @Steps
    ProductsSteps productsSteps;

    @Title("Creating a new product.")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Getting single product.")
    @Test
    public void test002() {
        ValidatableResponse response = productsSteps.getProduct(id);
        response.log().all().statusCode(200);
    }

    @Title("Updating the product.")
    @Test
    public void test003() {
        name = "Bowe Dry Cleaning Machine-Updated";
        ValidatableResponse response = productsSteps.updateProduct(id, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the product and verifying the deletion.")
    @Test
    public void test004() {
        productsSteps.deleteProduct(id).statusCode(200);
        productsSteps.getProduct(id).statusCode(404);
    }

}
