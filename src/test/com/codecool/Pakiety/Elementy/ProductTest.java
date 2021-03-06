package com.codecool.Pakiety.Elementy;

import org.junit.jupiter.api.Test;
import skleponline.kod.Pakiety.Elementy.Product;

import static org.junit.jupiter.api.Assertions.*;
//czy się nie powielają
class ProductTest {

    @Test
    void productToString() {
        Product product = new Product.Builder().withId(1).withName("Water").withCategory("Soft Drink").withPrice(5).withAmount(10).build();
        assertEquals("id = 1, \nname = Water, \ncategory = Soft Drink, \nprice = 5, \namount = 10", product.toString());
    }

    @Test
    void calculateRating() {
        Product product = new Product.Builder().withId(1).withName("Water").withCategory("Soft Drink").withPrice(5).withAmount(10).withRating(15).withNumberOfRatings(3).build();
        assertEquals(5.0, product.calculateRating());
    }

}