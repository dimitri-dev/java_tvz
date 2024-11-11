package hr.java.restaurant.model;

public interface DeliveryTrack {
    default void Track() {
        System.out.println("Order is being tracked.");
    }
}
