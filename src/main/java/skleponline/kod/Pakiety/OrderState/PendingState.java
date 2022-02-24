package skleponline.kod.Pakiety.OrderState;

import skleponline.kod.Pakiety.Elementy.Order;

public class PendingState implements OrderState {

    @Override
    public void next(Order z) {
        z.setState(new SentState());
    }

    @Override
    public void prev(Order z) {
        System.out.println("Zamowienie jest w poczatkowym stanie.");
    }

    @Override
    public void cancel(Order z) {
        z.setState(new CanceledState());
    }

    @Override
    public void printStatus() {
        System.out.println("Zamowienie zostalo przyjete.");
    }
}