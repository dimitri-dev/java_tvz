package hr.java.restaurant.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Entity implements Serializable {
    private static final AtomicInteger ATOMIC_IDENTITY_GENERATOR = new AtomicInteger(1000);

    private Long id;

    public Entity() {
        setId(ATOMIC_IDENTITY_GENERATOR.getAndIncrement());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
