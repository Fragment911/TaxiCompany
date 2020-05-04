package api.entity;

import java.util.Arrays;
import java.util.List;

public enum StatusUser {
    ACTIVE,
    BANNED;

    public static List<StatusUser> getAll() {
        return Arrays.asList(StatusUser.values());
    }
}
