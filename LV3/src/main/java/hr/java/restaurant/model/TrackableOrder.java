package hr.java.restaurant.model;

import java.time.LocalDateTime;

public class TrackableOrder extends Order implements DeliveryTrack {
    public TrackableOrder(Restaurant restaurant, Meal[] meals, Deliverer deliverer, LocalDateTime deliveryDateAndTime) {
        super(restaurant, meals, deliverer, deliveryDateAndTime);
    }
}
