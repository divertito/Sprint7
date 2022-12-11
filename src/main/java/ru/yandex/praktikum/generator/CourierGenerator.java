package ru.yandex.praktikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.praktikum.client.courier.Courier;

public class CourierGenerator {

    public static Courier getDefaultCourier() {
        return new Courier("Ivan", "Qwerty123", "Ivan");
    }

    public static Courier getRandomCourier() {
        return new Courier(RandomStringUtils.randomAlphanumeric(10), "Qwerty123", "Petr");
    }
}
