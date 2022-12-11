package ru.yandex.praktikum.client.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.config.Config;

public class OrderClient extends Config {

    @Step("Create new order {order}")
    public ValidatableResponse create(Object[][] order) {
        return getBaseSpec()
                .body(order)
                .when()
                .post(getOrderPath())
                .then();
    }

    @Step("Cancel new order {order}")
    public ValidatableResponse cancel(Object[][] order) {
        return getBaseSpec()
                .body(order)
                .when()
                .put(getOrderPath() + "/cancel")
                .then();
    }

    @Step("Get list of all orders")
    public ValidatableResponse getAllOrders() {
        return getBaseSpec()
                .when()
                .get(getOrderPath())
                .then();
    }
}
