package org.xsy.dorm.model.admin;

public enum AdminRole {
    SUPER_ADMIN("super_admin"),
    ADMIN("admin"),
    STAFF("staff");

    private final String value;

    AdminRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}