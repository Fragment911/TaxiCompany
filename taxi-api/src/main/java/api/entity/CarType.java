package api.entity;

import java.util.Arrays;
import java.util.List;

public enum CarType {
    SEDAN("Sedan (3-seaters)"),
    UNIVERSAL("Universal (3-seaters, baggage)"),
    PICKUP("Pickup (4-seaters, truck body)"),
    MINIVAN("Minivan (6-seaters)"),
    MINIBUS("Minibus (8-seaters, baggage)");

    public String description;

    CarType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static List<CarType> getAll() {
        return Arrays.asList(CarType.values());
    }
}

