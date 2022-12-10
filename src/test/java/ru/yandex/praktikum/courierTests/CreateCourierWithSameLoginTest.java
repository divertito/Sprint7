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

public class CreateCourierWithSameLoginTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierID;
    private int statusCode;
    private boolean isCreated;

    @Before
    public void setUp() {
        courier = CourierGenerator.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierID);
    }

    //нельзя создать двух одинаковых курьеров;
    //если создать пользователя с логином, который уже есть, возвращается ошибка.
    @Test
    @DisplayName("Creating several same couriers (shouldn't work, status code = 409)")
    public void sameCourierCantBeCreatedTest() {
        ValidatableResponse responseOne = courierClient.create(courier);

        statusCode = responseOne.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        isCreated = responseOne.extract().path("ok");
        assertTrue("Courier is not created", isCreated);

        ValidatableResponse responseTwo = courierClient.create(courier);

        statusCode = responseTwo.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CONFLICT, statusCode);
        String message = responseTwo.extract().path("message");
        assertEquals("Incorrect message on creating courier with the same login", "Этот логин уже используется", message);
    }
}

