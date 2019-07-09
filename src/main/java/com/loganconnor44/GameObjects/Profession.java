package com.loganconnor44.GameObjects;

public enum Profession {
    WARRIOR,
    THIEF,
    MAGE,
    PRIEST;

    @Override
    public String toString() {
        return name();
    }
}