package ru.yandex.praktikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.client.order.Order;

@RunWith(Parameterized.class)
public class OrderGenerator {
    public static Order getDefaultOrder() {
        return new Order("Ivanov", "Ivan", "Saint Petersburg", "Lesnaya", "322-233", 10, "2023-05-05", "где мой самокат?", new String[]{"Yellow"});
    }


    @Parameterized.Parameters(name = "{index}:{0}, {1}")
    public static Object[][] getRandomOrder() {
        return new Object[][]{
                {RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(20), "Lesnaya", "322-233", 10, "2023-05-05", "где мой самокат?", new Object[]{"BLACK", "GREY"}},
                {RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(20), "Lesnaya", "322-233", 10, "2023-05-05", "где мой самокат?", new Object[]{"BLACK"}},
                {RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(20), "Lesnaya", "322-233", 10, "2023-05-05", "где мой самокат?", new Object[]{"GREY"}},
                {RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(20), "Lesnaya", "322-233", 10, "2023-05-05", "где мой самокат?", new Object[]{}},
        };
    }
}
