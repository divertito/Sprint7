package ru.yandex.praktikum.courierTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.courier.Courier;
import ru.yandex.praktikum.client.courier.CourierClient;
import ru.yandex.praktikum.client.courier.CourierCredentials;
import ru.yandex.praktikum.generator.CourierGenerator;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class CreateCourierTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierID;

    @Before
    public void setUp() {
        courier = CourierGenerator.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierID);
    }

    //курьера можно создать;
    //запрос возвращает правильный код ответа;
    //успешный запрос возвращает ok: true;
    //курьер может авторизоваться;
    //успешный запрос возвращает id.
    @Test
    @DisplayName("Creating new courier with valid credentials")
    public void courierCanBeCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);

        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);

        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_OK, loginStatusCode);
        courierID = loginResponse.extract().path("id");
        assertNotNull("Courier was not created or id is incorrect", courierID);
    }
}
