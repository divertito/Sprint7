package ru.yandex.praktikum.config;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Config {

    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    protected static final String COURIER_PATH = "/api/v1/courier";
    protected static final String ORDER_PATH = "/api/v1/orders";

    public RequestSpecification getBaseSpec() {
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .baseUri(getBaseUrl());
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getCourierPath() {
        return COURIER_PATH;
    }

    public static String getOrderPath() {
        return ORDER_PATH;
    }
}
