package ru.yandex.praktikum.courierTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.courier.Courier;
import ru.yandex.praktikum.client.courier.CourierClient;
import ru.yandex.praktikum.generator.CourierGenerator;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class CreateCourierWithEmptyCredentialsTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierID;

    @Before
    public void setUp() {
        courier = CourierGenerator.getRandomCourier();
        ;
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierID);
    }

    //чтобы создать курьера, нужно передать в ручку все обязательные поля;
    //если одного из полей нет, запрос возвращает ошибку;
    @Test
    @DisplayName("Creating courier with empty password (shouldn't be done, status code = 400)")
    public void courierWithEmptyPasswordCantBeCreatedTest() {
        courier.setPassword(null);
        ValidatableResponse responseOne = courierClient.create(courier);

        int statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        String message = responseOne.extract().path("message");
        assertEquals("Incorrect validation message on creating courier with empty required fields", "Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @DisplayName("Creating courier with empty login (shouldn't be done, status code = 400)")
    public void courierWithEmptyLoginCantBeCreatedTest() {
        courier.setLogin(null);
        ValidatableResponse responseOne = courierClient.create(courier);

        int statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        String message = responseOne.extract().path("message");
        assertEquals("Incorrect validation message on creating courier with empty required fields", "Недостаточно данных для создания учетной записи", message);
    }

    @Test
    @DisplayName("Creating courier with empty name (should be done correctly, status code = 201)")
    public void courierWithEmptyNameCanCreatedTest() {
        courier.setFirstName(null);
        ValidatableResponse responseOne = courierClient.create(courier);

        int statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        boolean isCreated = responseOne.extract().path("ok");
        assertTrue("Courier is not created", isCreated);
    }
}

