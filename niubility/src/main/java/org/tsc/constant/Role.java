package org.tsc.constant;

public enum Role {
    Admin(StringConstant.ADMIN),
    Navigator(StringConstant.NAVIGATOR),
    Organization(StringConstant.ORGANIZATION),
    Recipient(StringConstant.RECIPIENT);

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
