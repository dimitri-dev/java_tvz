package hr.java.restaurant.generics;

import hr.java.input.FileReader;
import hr.java.restaurant.model.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NarudzbaDatotekaDatasource implements DataSource<Order, List<Order>> {
    private final List<Order> orders;

    public NarudzbaDatotekaDatasource(Path path, String fileName) {
        orders = new ArrayList<>();
    }

    public void loadData(List<Order> orders) {
        this.orders.addAll(orders);
    }

    @Override
    public List<Order> getData() {
        return new ArrayList<>(orders); // copy
    }

    @Override
    public void recordData(List<Order> data) {
        data.forEach(this::recordData);
    }

    @Override
    public void recordData(Order data) {
        orders.add(data);
    }

    public void saveData() {
        // group orders by amount of meals and make a directory for each group and save the meals inside those directories/files.
        var groupedOrders = orders.stream().collect(Collectors.groupingBy(o -> o.getMeals().size()));

        for (var entry : groupedOrders.entrySet()) {
            var dir = Path.of("dat/orders/" + entry.getKey());
            dir.toFile().mkdirs();

            var path = dir.resolve("orders.txt");
            var ls = entry.getValue().stream().map(Order::getMeals)
                    .flatMap(List::stream)
                    .distinct()
                    .toList();

            try (var fileOut = new FileOutputStream(path.toString());
                 var out = new ObjectOutputStream(fileOut)) {
                out.writeObject(ls);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Saved " + entry.getValue().size() + " orders with " + entry.getKey() + " meals to " + path);
        }
    }
}
