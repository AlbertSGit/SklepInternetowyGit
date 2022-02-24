package skleponline.kod.Pakiety.SerwisPracownicy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import skleponline.kod.Pakiety.BazaDane.OrdersDaoImpl;
import skleponline.kod.Pakiety.BazaDane.OrdersHistoryDaoImpl;
import skleponline.kod.Pakiety.BazaDane.ProductsDaoImpl;
import skleponline.kod.Pakiety.BazaDane.UsersDaoImpl;

import skleponline.kod.Pakiety.Elementy.Order;
import skleponline.kod.Pakiety.Elementy.Product;
import skleponline.kod.Pakiety.Elementy.ProductIterator;
import skleponline.kod.Pakiety.Elementy.User;

import skleponline.kod.Pakiety.Widok.View;

public class CustomerService {

    private User user;
    private Iterator<Product> basketIterator;
    private Iterator<Product> shopIterator;
    private ProductsDaoImpl productDao;
    private OrdersDaoImpl orderDao;
    private OrdersHistoryDaoImpl orderHistoryDao;
    private View view;

    public CustomerService(User user) {
        this.user = user;
        this.basketIterator = user.getBasket().getIterator();
        this.productDao = new ProductsDaoImpl();
        this.shopIterator = new ProductIterator(productDao.getProducts());
        this.view = new View();
        this.orderDao = new OrdersDaoImpl();
        this.orderHistoryDao = new OrdersHistoryDaoImpl();
    }

    public void addProductToBasket(int id, int amount) {
        while (shopIterator.hasNext()) {
            Product current = shopIterator.next();
            if (current.getId() == id) {
                user.getBasket().addProduct(current, amount);
            }
        }
    }

    public void removeProductFromBasket(int id) {
        while (basketIterator.hasNext()) {
            Product productToRemove = basketIterator.next();
            if (productToRemove.getId() == id) {
                user.getBasket().deleteProduct(productToRemove);
            }
        }
    }

    public void DisplayAllProductsInBasket() {
        view.productsTable(user.getBasket().getProducts());
    }

    public void displayAllProductsInShop() {
        productDao = new ProductsDaoImpl();
        view.productsTable(productDao.getProducts());
    }

    public User getUser() {
        return this.user;
    }

    public void editProductQuantity (){
        view.showMessage("wprowadź nazwę produktu, którego ilość chcesz zmienić");
        String name = view.getStringInput();
        for (Product p: user.getBasket().getProducts()){
            if(p.getName().contains(name)){
                view.showMessage("wprowadź nową ilość");
                p.setAmount(view.getIntegerInput());
            }
        }
    }

    public void displayProductsByCategory() {
        ProductsDaoImpl productsDao = new ProductsDaoImpl();
        view.showMessage("wprowadź nazwę kategorii ");
        String name = view.getStringInput();
        ArrayList<Product> productsByCategory = new ArrayList<>();
        for(Product p: productsDao.getProducts()){
            Product product = p;
            if (product.getCategory().contains(name)){
                productsByCategory.add(product);
            }
        }
        view.productsTable(productsByCategory);
    }

    public void displayUserOrder() {
        orderDao = new OrdersDaoImpl();
        List<Order> orders = orderDao.getOrderData();
        List<Order> userOrders = getOrderDataForUser(orders);
        view.showMessage("Szczegóły zamówienia : ");
        view.ordersTableUser(userOrders);
    }

    public void displayOrdersHistory() {
        orderHistoryDao = new OrdersHistoryDaoImpl();
        List<Order> ordersHistory = orderHistoryDao.getOrderHistoryDetails();
        List<Order> userOrders = getOrderDataForUser(ordersHistory);
        view.showMessage("Historia zamówień : ");
        view.ordersUserHistoryTable(userOrders);
    }


    private Integer getProductId() {
        view.showMessage(view.ID);
        int id = view.getIntegerInput();
        return id;
    }

    private Integer getProductAmount() {
        view.showMessage(view.ENTERAMOUNT);
        int amount = view.getIntegerInput();
        return amount;
    }

    private boolean validAmount(int id, int amount) {
        if (productDao.productAmountIsValid(id, amount)) {
            addProductToBasket(id, amount);
            productDao.deleteProductsByUser(id, amount);
            return true;
        } else {
            view.showMessage(view.WRONGAMOUNT);
            return false;
        }
    }

    public void handleAddProduct() {
        boolean editProduct = true;
        while (editProduct) {
            int id = getProductId();
            if (productDao.validID(id)) {
                int amount = getProductAmount();
                if (validAmount(id, amount)) {
                    editProduct = false;
                }
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    private void addProductToShop(int id) {
        while (shopIterator.hasNext()) {
            Product current = shopIterator.next();
            if (current.getId() == id) {
                productDao.addProductByUser(id, current.getAmount());
            }
        }
    }

    public void handleDeleteProduct() {
        view.showMessage("Wpisz ID produktu, który chcesz usunąć : ");
        int id = view.getIntegerInput();
        while (!productDao.validID(id)) {
            id = view.getIntegerInput();
        }
        addProductToShop(id);
        removeProductFromBasket(id);
    }

    public void placeOrderIfBasketNotEmpty() {
        OrdersDaoImpl orders = new OrdersDaoImpl();
        if (user.getBasket().checkIfBasketEmpty()) {
            view.showMessage("Dodaj produkty do koszyka !");
            view.getEmptyInput();
        } else {
            orders.addOrder(user);
            user.getBasket().clearBasket();
        }
    }

    public void handleRateProduct() {
        String columnName = "Ilość ocen";
        String columnName1 = "Ocena";
        view.showMessage("Wpisz ID produktu, który chcesz ocenić");
        int id = view.getIntegerInput();
        while (!productDao.validID(id)) {
            id = view.getIntegerInput();
        }
        view.showMessage("Wpisz ocenę pomiędzy (1 - 5)");
        int userRating = validateRating();
        while (shopIterator.hasNext()) {
            Product current = shopIterator.next();
            if (current.getId() == id) {
                current.setRating(current.getRating() + userRating);
                current.setNumberOfRatings(current.getNumberOfRatings() + 1);
                productDao.editProductRating(id, current.getRating(), columnName1);
                productDao.editProductNumberOfRatings(id, current.getNumberOfRatings(), columnName);
            }
        }
    }

    public void changePassword() {
        UsersDaoImpl usersDao = new UsersDaoImpl();
        view.showMessage(view.ENTERNEWVALUE);
        String newPassword = view.getStringInput();
        usersDao.updateUser(user.getId(), "Hasło", newPassword);
        view.showMessage("Zaktualizowano pomyślnie");
    }

    private List<Order> getOrderDataForUser(List<Order> allOrders) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getUserId() == user.getId()) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public int validateRating() {
        int userRating = view.getIntegerInput();
        while (!String.valueOf(userRating).matches("[1-5]")) {
            view.showMessage("Wpisz liczbę pomiędzy 1 a 5 ");
            userRating = view.getIntegerInput();
        }
        return userRating;
    }
}