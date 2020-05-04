package api.entity;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ADMIN,
    MODER,
    DRIVER,
    PASSENGER;

    public static List<Role> getAll() {
        return Arrays.asList(Role.values());
    }
}
