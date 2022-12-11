package ru.yandex.praktikum.orderTests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.order.OrderClient;
import ru.yandex.praktikum.generator.OrderGenerator;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class CreateOrderTest {
    private Object[][] order;
    private OrderClient orderClient;

    @Before
    public void setUp() {
        order = OrderGenerator.getRandomOrder();
        orderClient = new OrderClient();
    }

    @After
    public void tearDown() {
        orderClient.cancel(order);
    }

    //Создание заказа;
    //параметризация для проверки условий: можно указать один из цветов — BLACK или GREY; можно совсем не указывать цвет; можно совсем не указывать цвет;
    //тело ответа содержит track.
    @Test
    @DisplayName("Creating of the new order")
    public void orderCanBeCreatedTest() {
        ValidatableResponse response = orderClient.create(order);

        int statusCode = response.extract().statusCode();
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        ValidatableResponse trackNumber = response.assertThat().body("track", notNullValue());
    }
}
