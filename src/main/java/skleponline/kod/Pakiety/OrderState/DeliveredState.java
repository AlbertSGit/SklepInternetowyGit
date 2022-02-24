package skleponline.kod.Pakiety.OrderState;

import skleponline.kod.Pakiety.Elementy.Order;

public class DeliveredState implements OrderState {

    @Override
    public void next(Order z) {
        System.out.println("Zamowienie zostalo juz odebrane.");
    }

    @Override
    public void prev(Order z) {
        z.setState(new SentState());
    }

    @Override
    public void cancel(Order z) {
        z.setState(new CanceledState());
    }

    @Override
    public void printStatus() {
        System.out.println("Zamowienie zostalo odebrane.");
    }
}
