package ru.sbrf.course;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderControllerUTest {

    private OrderController orderController;
    @Before
    public void init() {
        orderController = new OrderController(new DiscountRegistryImpl());
    }

    @Test(expected = NullPointerException.class)
    public void npeItems() {
        orderController.getItemDiscountForClient(new Client(), null);
    }

}
