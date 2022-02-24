package skleponline.kod.Pakiety.OrderState;

import skleponline.kod.Pakiety.Elementy.Order;

public class SentState implements OrderState {

    @Override
    public void next(Order z) {
        z.setState(new DeliveredState());
    }

    @Override
    public void prev(Order z) {
        z.setState(new PendingState());
    }

    @Override
    public void cancel(Order z) {
        z.setState(new CanceledState());
    }

    @Override
    public void printStatus() {
        System.out.println("Zamowienie jest gotowe do odbioru.");
    }
}
