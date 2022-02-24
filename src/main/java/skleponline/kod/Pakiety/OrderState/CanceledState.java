package skleponline.kod.Pakiety.OrderState;

import skleponline.kod.Pakiety.Elementy.Order;

public class CanceledState implements OrderState {

    @Override
    public void next(Order z) {
        //System.out.println("Zamowienie zostalo juz odebrane.");
    }

    @Override
    public void prev(Order z) {
        //z.setState(new ReadyState());
    }

    @Override
    public void cancel(Order z) {
        //System.out.println("Zamowienie zostalo anulowane.");
    }

    @Override
    public void printStatus() {
        System.out.println("Zamowienie zostalo anulowane.");
    }
}