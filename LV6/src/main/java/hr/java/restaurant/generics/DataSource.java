package hr.java.restaurant.generics;

import java.util.List;

public interface DataSource<T, S> {
    S getData();
    void recordData(List<T> data);
    void recordData(T data);
}
