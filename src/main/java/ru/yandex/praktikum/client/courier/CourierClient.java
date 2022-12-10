package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.config.Config;

public class CourierClient extends Config {

    @Step("Create new courier {courier}")
    public ValidatableResponse create(Courier courier) {
        return getBaseSpec()
                .body(courier)
                .when()
                .post()
                .then();
    }

    @Step("Login with courier {courier}")
    public ValidatableResponse login(CourierCredentials credentials) {
        return getBaseSpec()
                .body(credentials)
                .when()
                .post("/login")
                .then();
    }

    @Step("Delete courier {courier}")
    public ValidatableResponse delete(int courierID) {
        return getBaseSpec()
                .body(courierID)
                .when()
                .delete("/" + courierID)
                .then();
    }

}
