package api.entity;

import java.util.Arrays;
import java.util.List;

public enum Role {
    ROLE_ADMIN,
    ROLE_MODER,
    ROLE_DRIVER,
    ROLE_PASSENGER;

    public static List<Role> getAll() {
        return Arrays.asList(Role.values());
    }
}
