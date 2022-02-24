package skleponline.kod.Pakiety.BazaDane;

import java.util.List;

import skleponline.kod.Pakiety.Elementy.Order;
import skleponline.kod.Pakiety.Elementy.User;

public interface OrdersBase {

    public List<Order> getOrderData();
    public void addOrder(User user);
    public void updateOrderStatus(int orderId, String status);
}