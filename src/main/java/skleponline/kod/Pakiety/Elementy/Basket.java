package skleponline.kod.Pakiety.Elementy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Basket {
// Klasa odpowiada za wszystko co związane z Koszykiem np:
// - Dodawnie, usuwanie, zwiększanie produktów w koszyku ale także :
// - Sprawdzaniem czy koszyk jest pusty ale również czyszczeniem samego koszyka.
    private int id;
    private List<Product> products;
    private Iterator<Product> basketIterator;

    public Basket() {
        this.products = new ArrayList<Product>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getId() {
        return id;
    }

    public void addProduct(Product product, int amount) {
        product.setAmount(amount);
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public Iterator<Product> getIterator() {
        basketIterator = new ProductIterator(products);
        return basketIterator;
    }

    public boolean checkIfBasketEmpty() {
        return products.isEmpty();
    }

    public void clearBasket() {
        this.products = new ArrayList<Product>();
    }
}