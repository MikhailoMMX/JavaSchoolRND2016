package ru.sbrf.course;

/**
 * Created by Student on 04.08.2016.
 */
public class DiscountRegistryImpl implements DiscountRegistry {
    @Override
    public int getDiscount(Item item) {
        return 5;
    }
}
