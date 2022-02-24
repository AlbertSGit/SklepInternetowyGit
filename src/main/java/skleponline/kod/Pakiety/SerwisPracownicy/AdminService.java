package skleponline.kod.Pakiety.SerwisPracownicy;


import skleponline.kod.Pakiety.Elementy.Order;
import skleponline.kod.Pakiety.Elementy.OrderStatus;
import skleponline.kod.Pakiety.Elementy.User;
import skleponline.kod.Pakiety.Elementy.UserType;
import skleponline.kod.Pakiety.Widok.View;
import skleponline.kod.Pakiety.BazaDane.*;

import java.util.List;

public class AdminService {

    private View view;
    private ProductsDaoImpl productDao;
    private OrdersDaoImpl orderDao;
    private OrdersHistoryDaoImpl orderHistoryDao;
    private UsersDaoImpl usersDao;
    private boolean wrongInput;
    private final String COLUMNAMOUNT = "Ilość";
    private final String COLUMNPRICE = "Cena";
    

    public AdminService() {
        view = new View();
        productDao = new ProductsDaoImpl();
        orderDao = new OrdersDaoImpl();
        orderHistoryDao = new OrdersHistoryDaoImpl();
        usersDao = new UsersDaoImpl();
    }
    
    public void createNewProduct() {
        view.showMessage(view.ENTERPRODUCT);
        String name = view.getStringInput();
        view.showMessage(view.ENTERCATEGORY);
        String category = view.getStringInput();
        view.showMessage(view.ENTERPRICE);
        int price = view.getIntegerInput();
        view.showMessage(view.ENTERAMOUNT);
        int amount = view.getIntegerInput();
        productDao.addNewProduct(name, category, price, amount);
        productDao = new ProductsDaoImpl();
    }

    public void deleteProductAdmin() {
        boolean editProduct = true;
        while (editProduct) {
            int productID = getID(view.DELETEPRODUCT);
            if (productDao.validID(productID)) {
                productDao.deleteProductAdmin(productID);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    public Integer getID(String message) {
        view.showMessage(message);
        int productID = view.getIntegerInput();
        return productID;
    }

    public Integer editValueOfProduct(String message) {
        view.showMessage(message);
        int producValue = view.getIntegerInput();
        return producValue;
    }

    public String editNameOfProduct(String message) {
        view.showMessage(message);
        String producValue = view.getStringInput();
        return producValue;
    }

    public void editProductName() {
        boolean editProduct = true;
        while(editProduct) {
            int productId = getID(view.ENTERPRODUCTID);
            if (productDao.validID(productId)) {
                String productName = editNameOfProduct(view.ENTERNAME);
                productDao.editProductName(productId, productName);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    private void editProductValue(String column, String sentence) {
        boolean editProduct = true;
        while(editProduct) {
            int productId = getID(view.ENTERPRODUCTID);
            if (productDao.validID(productId)) {
                int productValue = editValueOfProduct(sentence);
                productDao.editProduct(productId, productValue, column);
                editProduct = false;
            } else {
                view.showMessage(view.WRONGID);
            }
        }
    }

    public void editProductPrice() {
        editProductValue(COLUMNPRICE, view.ENTERPRICE);
    }

    public void  editProductAmount() {
        editProductValue(COLUMNAMOUNT, view.ENTERAMOUNT);
    }

    public void displayAllProductsInShop() {
        ProductBase productDao = new ProductsDaoImpl();
        view.productsTable(productDao.getProducts());
    }

    public void displayAllOrders() {
        orderDao = new OrdersDaoImpl();
        List<Order> orders = orderDao.getOrderData();
        view.showMessage("Szczegóły zamówienia :");
        view.ordersTableAdmin(orders);
    }

    public void displayOrdersHistory() {
        orderHistoryDao = new OrdersHistoryDaoImpl();
        List<Order> ordersHistory = orderHistoryDao.getOrderHistoryDetails();
        view.showMessage("Historia zamówień :");
        view.ordersHistoryTable(ordersHistory);
    }

    public void displayAllCustomers(){
        usersDao = new UsersDaoImpl();
        List<User> allUsers = usersDao.getUserData();
        view.showMessage("Tabela wszystkich klientów");
        view.usersTable(allUsers);
    }

    public void deleteUser() {
        usersDao = new UsersDaoImpl();
        view.showMessage(view.ENTERUSERID);
        int userID = view.getIntegerInput();
        if (usersDao.isValid(userID)) {
            usersDao.deleteUser(userID);
            view.showMessage("Pomyślnie usunięto");
        } else {
            view.showMessage(view.NOOPTION);
        }
    }

    public void updateUserDetails() {
        usersDao = new UsersDaoImpl();
        wrongInput = true;
        view.showMessage(view.ENTERUSERID);
        int userID = view.getIntegerInput();
        if (usersDao.isValid(userID)) {
            view.displayUpdateUserDetailsMenu();
            while (wrongInput) {
                handleUpdateUserDetails(userID);
            }
            view.showMessage(view.UPDATED);
        } else {
            view.showMessage(view.NOOPTION);
        }
    }

    public void updateOrderStatus() {
        orderDao = new OrdersDaoImpl();
        wrongInput = true;
        view.showMessage("Wprowadź ID zamówienia : ");
        int orderId = view.getIntegerInput();
        if (orderDao.isValid(orderId)) {
            view.displayUpdateStatusMenu();
            while (wrongInput) {
                handleOrderStatusUpdate(orderId);
            }
            view.showMessage(view.UPDATED);
        } else {
            view.showMessage(view.NOOPTION);
        }
    }

    private void handleOrderStatusUpdate(int orderId) {
        int chosenOption = view.getIntegerInput();
        switch (chosenOption) {
            case 1:
                orderDao.updateOrderStatus(orderId, OrderStatus.PENDING.name());
                wrongInput = false;
                break;
            case 2:
                orderDao.updateOrderStatus(orderId, OrderStatus.SENT.name());
                wrongInput = false;
                break;
            case 3:
                orderDao.updateOrderStatus(orderId, OrderStatus.DELIVERED.name());
                wrongInput = false;
                break;
            case 4:
                orderDao.updateOrderStatus(orderId, OrderStatus.CANCELED.name());
                wrongInput = false;
                break;
            case 0:
                wrongInput = false;
                break;
            default:
                view.showMessage(view.NOOPTION);
                break;
        }
    }

    private void handleUpdateUserDetails(int userID) {
        String newValue;
        int chosenOption = view.getIntegerInput();
        switch (chosenOption) {
            case 1:
                newValue = getNewValueFromUser();
                usersDao.updateUser(userID, "Nazwa", newValue);
                wrongInput = false;
                break;
            case 2:
                newValue = getNewValueFromUser();
                usersDao.updateUser(userID, "Hasło", newValue);
                wrongInput = false;
                break;
            case 3:
                newValue = handleUserTypeUpdate();
                usersDao.updateUser(userID, "Typ użytkownika", newValue);
                wrongInput = false;
                break;
            case 0:
                wrongInput = false;
                break;
            default:
                view.showMessage(view.NOOPTION);
                break;
        }
    }

    private String handleUserTypeUpdate() {
        wrongInput = true;
        while (wrongInput) {
            view.showMessage("\n1. Klient\n2. Admin");
            int chosenOption = view.getIntegerInput();
            switch (chosenOption) {
                case 1:
                    return UserType.CUSTOMER.name();
                case 2:
                    return UserType.ADMIN.name();
                default:
                    view.showMessage(view.NOOPTION);
                    break;
            }
        }
        return null;
    }

    private String getNewValueFromUser() {
        view.showMessage(view.ENTERNEWVALUE);
        return view.getStringInput();
    }
}