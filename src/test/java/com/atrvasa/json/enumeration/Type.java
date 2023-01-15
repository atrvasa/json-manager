package com.atrvasa.json.enumeration;

public enum Type {
    PDF(1),

    EXCEL(2),

    WORD(3),

    PRINTER(4);

    private int value;

    private Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Type getEnumItem(int value) {
        return PDF;
    }
}
