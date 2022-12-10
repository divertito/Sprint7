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

public class LoginCourierWithIncorrectCredentialsTest {
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

    //для авторизации нужно передать все обязательные поля;
    //если какого-то поля нет, запрос возвращает ошибку;
    @Test
    @DisplayName("Login with empty login")
    public void courierWithEmptyLoginCantBeLoggedInTest() {
        courier.setLogin(null);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals("Incorrect validation message on try to log in with incorrect login", "Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Login with empty password")
    public void courierWithEmptyPasswordCantBeLoggedInTest() {
        courier.setPassword(null);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals("Incorrect validation message on try to log in with incorrect login", "Недостаточно данных для входа", message);
    }

    //если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    //система вернёт ошибку, если неправильно указать логин или пароль;
    @Test
    @DisplayName("Login with invalid login")
    public void courierWithInvalidLoginCantBeLoggedInTest() {
        courier.setLogin("MarinaMarinaMarina");
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_NOT_FOUND, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals("Incorrect validation message on try to log in with incorrect login", "Учетная запись не найдена", message);
    }

    @Test
    @DisplayName("Login with invalid password")
    public void courierWithInvalidPasswordCantBeLoggedInTest() {
        courier.setPassword("MarinaMarinaMarina");
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));

        int loginStatusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_NOT_FOUND, loginStatusCode);
        String message = loginResponse.extract().path("message");
        assertEquals("Incorrect validation message on try to log in with incorrect login", "Учетная запись не найдена", message);
    }
}
