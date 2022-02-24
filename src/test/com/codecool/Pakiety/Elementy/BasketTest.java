package com.codecool.Pakiety.Elementy;

import org.junit.jupiter.api.Test;
import skleponline.kod.Pakiety.Elementy.Basket;
import skleponline.kod.Pakiety.Elementy.Product;

import static org.junit.jupiter.api.Assertions.*;

public class BasketTest {

    Basket basket = new Basket();
    Product product = new Product.Builder().withId(1).withName("Water").withCategory("Soft Drink").withPrice(5).withAmount(10).build();

    @Test
    void addProductTest() {
        basket.addProduct(product, 10);
        assertEquals(1, basket.getProducts().size());
    }

    @Test
    void deleteProductTest() {
        basket.deleteProduct(product);
        assertEquals(0, basket.getProducts().size());
    }


}