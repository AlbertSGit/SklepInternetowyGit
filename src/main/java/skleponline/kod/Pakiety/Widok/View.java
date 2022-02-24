package skleponline.kod.Pakiety.Widok;

import skleponline.kod.Pakiety.Elementy.Order;
import skleponline.kod.Pakiety.Elementy.Product;
import skleponline.kod.Pakiety.Elementy.User;
import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public final String ENTERPRODUCTID = "Podaj ID produktu dla edycji: ";
    public final String ID = "Podaj ID produktu do kupna: ";
    public final String ENTERPRODUCT = "Wprowadź nazwę produktu: ";
    public final String ENTERCATEGORY = "Wprowadź kategorię produktu: ";
    public final String ENTERPRICE = "Wprowadź cenę produktu: ";
    public final String ENTERAMOUNT = "Wprowadź ilość produktu: ";
    public final String DELETEPRODUCT = "Wprowadź ID produktu do usunięcia: ";
    public final String ENTERNAME = "Wprowadź nazwę produktu: ";
    public final String ENTERUSERID = "Wprowadź ID użytkownika: ";
    public final String ENTERNEWVALUE = "Wprowadź nową wartość: ";
    public final String WRONGID = "Złe ID produktu, wprowadź ponownie !";
    public final String WRONGAMOUNT = "Niewłaściwa ilość produktu, za mało produktu w sklepie, spróbuj ponownie !";
    public final String UPDATED = "Zaktualizowano pomyślnie.";
    public final String NOOPTION = "Nie ma takiej opcji.";

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String userText (){
        return scanner.nextLine();
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void productsTable(List<Product> products){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("ID","Nazwa","Kategoria","Cena","Ilość","Ocena");
        for (Product p: products){
            String.valueOf(p.getId());
            table.addRow(String.valueOf(p.getId()),
                    p.getName(),
                    p.getCategory(),
                    String.valueOf(p.getPrice()),
                    String.valueOf(p.getAmount()),
                    String.format("%.2f", p.calculateRating()));
        }
        table.print();
    }
    public void ordersTableUser(List<Order> orderList){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("ID zamówienia","Nazwa produktu","Ilość produktu","Cena");

        for (Order o: orderList){
            table.addRow(String.valueOf(o.getOrderId()),
                    String.valueOf(o.getProductName()),
                    String.valueOf(o.getProductAmount()),
                    String.valueOf(o.getProductAmountPrice()));
        }
        table.print();
    }

    public void ordersTableAdmin(List<Order> orderList){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("ID zamówienia","ID produktu", "Nazwa produktu","Ilość produktu","Cena","ID użytkownika");

        for (Order o: orderList){
            table.addRow(String.valueOf(o.getOrderId()),
                    String.valueOf(o.getProductId()),
                    String.valueOf(o.getProductName()),
                    String.valueOf(o.getProductAmount()),
                    String.valueOf(o.getProductAmountPrice()),
                    String.valueOf(o.getUserId()));
        }
        table.print();
    }

    public void ordersHistoryTable(List<Order> orderHistoryList) {
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("ID zamówienia", "data", "id użykownika","cena", "status");

        for (Order oh: orderHistoryList) {
            table.addRow(String.valueOf(oh.getOrderId()),
                    String.valueOf(oh.getDate()),
                    String.valueOf(oh.getUserId()),
                    String.valueOf(oh.getTotalPrice()));
            //String.valueOf(oh.getStatus()));
        }
        table.print();
    }

    public void ordersUserHistoryTable(List<Order> orderHistoryList) {
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("ID zamówienia", "data","cena", "status");

        for (Order oh: orderHistoryList) {
            table.addRow(String.valueOf(oh.getOrderId()),
                    String.valueOf(oh.getDate()),
                    String.valueOf(oh.getTotalPrice()));
            //String.valueOf(oh.getStatus()));
        }
        table.print();
    }

    public void usersTable(List<User> users){
        TableClass table = new TableClass();
        table.setShowVerticalLines(true);
        table.setHeaders("ID zamówienia","nazwa","hasło","który użytkownik");
        for (User u: users) {
            table.addRow(String.valueOf(u.getId()),
                    String.valueOf(u.getName()),
                    String.valueOf(u.getPassword()),
                    String.valueOf(u.getUserType()));
        }
        table.print();
    }

    public void displayMainMenu() {
        showMessage("\n Witaj w naszym Sklepie : Wybierz opcje !" +
                "\n 1. Zaloguj się" +
                "\n 2. Zarejestruj się" +
                "\n 3. Wyjdź ze sklepu.");
    }

    public void displayCustomerMenu() {
        showMessage("MENU Klienta:" +
                "\n  1. - Pokaż mój koszyk" +
                "\n  2. - Dodaj produkt do koszyka" +
                "\n  3. - Usuń produkt" +
                "\n  4. - Zamień produkt" +
                "\n  5. - Pokaż historię moich zamówień" +
                "\n  6. - Pokaż wszystkie produkty" +
                "\n  7. - Pokaż wszystkie produkty z danej kategorii"+
                "\n  8. - Edytuj ilość produktów"+
                "\n  9. - Oceń jakość produktu"+
                "\n 10. - Zmień hasło"+
                "\n 11. - Wyjdź z MENU");
    }

    public void displayAdminMenu() {
        showMessage("MENU Admina:" +
                "\n  1. - Dodaj nowy produkt" +
                "\n  2. - Usuń produkt" +
                "\n  3. - Wyświetl produkty" +
                "\n  4. - Edytuj cenę produktu" +
                "\n  5. - Edytuj nazwe produktu" +
                "\n  6. - Edytuj kwotę" +
                "\n  7. - Wyświetl wszystkie zamówienia" +
                "\n  8. - Zrób zniżkę" +
                "\n  9. - Edytuj dane użytkownika" +
                "\n 10. - Usuń użytkownika" +
                "\n 11. - Zaktualizuj status zamówienia" +
                "\n 12. - Wyjście");
    }

    public void displayUpdateStatusMenu() {
        showMessage("Aktualizacja stanu: " +
                "\n 1. - Oczekiwanie" +
                "\n 2. - Wysłano" +
                "\n 3. - Dostarczono" +
                "\n 4. - Anulowany" +
                "\n 0. - Wyjście");
    }

    public void displayUpdateUserDetailsMenu() {
        showMessage("Aktualizacja: " +
                "\n 1. - Nazwa" +
                "\n 2. - Hasło" +
                "\n 3. - Typ użytkownika" +
                "\n 0. - Wyjście");
    }

    public int getIntegerInput() {
        while (!scanner.hasNextInt()) {
            showMessage("Błędne dane, spróbuj ponownie!");
            scanner.nextLine();
        }
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    public String getStringInput(){
        return scanner.nextLine();
    }

    public void displayNotImplementedMessage() {
        showMessage("Jeszcze niezaimplementowana");
        getEmptyInput();

    }

    public void getEmptyInput() {
        showMessage("Naciśnij Enter, aby kontynuować ");
        String input = scanner.nextLine();
    }
}
