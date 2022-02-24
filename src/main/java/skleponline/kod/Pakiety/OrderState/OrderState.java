package skleponline.kod.Pakiety.OrderState;
import skleponline.kod.Pakiety.Elementy.Order;

public interface OrderState {

    void next(Order z);
    void prev(Order z);
    void cancel(Order z);
    void printStatus();
}