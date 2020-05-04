package api.entity;

import java.util.Arrays;
import java.util.List;

public enum StatusOrder {
    AWAIT,
    RUN,
    DONE,
    CANCELLED,
    FAILED;

    public static List<StatusOrder> getAll() {
        return Arrays.asList(StatusOrder.values());
    }
}
