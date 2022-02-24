package skleponline.kod.Pakiety.Elementy;

import skleponline.kod.Pakiety.OrderState.OrderState;

public class Order {
    // Jest to jedna z bardziej rozbudowanych klas, natomiast musieliśmy poświęcić troszkę czasu
// na zamówienia oraz ich poprawną implementację do "tabel". Ponieżej możemy znaleźć wszystkie
// atrybuty zamówień (ID,Nazwe,date,status). Sam konstruktor Order użyty jest w Widoku
// Podobnie jak sam Builder na dole ma za zadanie już tworzyć za pomocą wyobrażenia wzorców
// konstrukcyjnych nasze zamówienia.
    private Integer orderId;
    private int userId;
    private int productId;
    private String productName;
    private int productAmount;
    private int productAmountPrice;
    private int totalPrice;
    private String date;
    private OrderState status;

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.userId = builder.userId;
        this.productAmount = builder.productAmount;
        this.productId = builder.productId;
        this.productAmountPrice = builder.productAmountPrice;
        this.productName = builder.productName;
        this.totalPrice = builder.totalPrice;
        this.date = builder.date;
        this.status = builder.status;
    }

    public int getProductAmountPrice() {
        return productAmountPrice;
    }

    public int getProductAmount(){
        return productAmount;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductId(){
        return productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public int compareTo(Order other) {
        return this.orderId.compareTo(other.orderId);
    }

    public void setState(OrderState state) {
        this.status = state;
    }

    public OrderState getState() {
        return status;
    }

    public static class Builder {
        private Integer orderId;
        private String date;
        private int userId;
        private int totalPrice;
        private int productId;
        private String productName;
        private int productAmount;
        private int productAmountPrice;
        //private String status;
        private OrderState status;

        public Builder withOrderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder withUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder withProductId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withProductAmount(int productAmount) {
            this.productAmount = productAmount;
            return this;
        }

        public Builder withProductAmountPrice(int productAmountPrice) {
            this.productAmountPrice = productAmountPrice;
            return this;
        }

        public Builder withTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        //public Builder withStatus(String status) {
       //     this.status = status;
       //    return this;
       // }

        public Order build() {
            return new Order(this);
        }
    }
}