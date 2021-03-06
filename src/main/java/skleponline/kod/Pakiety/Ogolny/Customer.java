package skleponline.kod.Pakiety.Ogolny;

import skleponline.kod.Pakiety.Elementy.User;
import skleponline.kod.Pakiety.SerwisPracownicy.CustomerService;
import skleponline.kod.Pakiety.Widok.View;

public class Customer {
    
    private View view = new View();
    private boolean isRunning;
    private CustomerService customerService;
    User customer;

    public Customer(User customer) {
        this.customer = customer;
        this.isRunning = true;
        this.customerService = new CustomerService(customer);
    }

    public void handleCustomerController() {
        while (isRunning) {
            view.clearScreen();
            view.displayCustomerMenu();
            handleCustomerinput();
        }
    }

    public void handleCustomerinput() {
        int userInput = view.getIntegerInput();
        view.clearScreen();
        switch (userInput) {
            case 1:
                customerService.DisplayAllProductsInBasket();
                view.getEmptyInput();
                break;
            case 2:
                customerService.displayAllProductsInShop();
                customerService.handleAddProduct();
                break;
            case 3:
                customerService.DisplayAllProductsInBasket();
                customerService.handleDeleteProduct();
                break;
            case 4:
                customerService.placeOrderIfBasketNotEmpty();
                break;
            case 5:
                customerService.displayOrdersHistory();
                view.getEmptyInput();
                customerService.displayUserOrder();
                view.getEmptyInput();
                break;
            case 6:
                customerService.displayAllProductsInShop();
                view.getEmptyInput();
                break;
            case 7:
                customerService.displayProductsByCategory();
                view.getEmptyInput();
                break;
            case 8:
                customerService.DisplayAllProductsInBasket();
                customerService.editProductQuantity();
                break;
            case 9:
                customerService.displayAllProductsInShop();
                customerService.handleRateProduct();
                break;
            case 10:
                customerService.changePassword();
                view.getEmptyInput();
                break;
            case 11:
                isRunning = false;
                break;
            default:
                break;
        }
    }
}