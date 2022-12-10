package ru.yandex.praktikum.orderTests;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.order.OrderClient;
import ru.yandex.praktikum.generator.OrderGenerator;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class CheckOrderList {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    //получения листа со всеми заказами
    @Test
    public void getAllOrdersTest() {
        ValidatableResponse response = orderClient.getAllOrders();
        //System.out.println(response);
        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_OK, statusCode);

    }
}
