package skleponline.kod.Pakiety.Elementy;

import java.util.ArrayList;
import java.util.List;

public class Category {
// Klasa posiłkowa opisująca kategorie danego produktu jeśli jest utworzony
// Odnosimy się do niej np. w Widoku gdzie konstruktor Category odpowiada za
// Opis oraz ID danego przedmiotu oraz jego podziału na bloki/kategorie.
    private int id;
    private String name;
    private List<Product> products;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<Product>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void populateProducts() {
        
    }
}