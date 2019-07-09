package com.loganconnor44.GameObjects;

public enum Armor {
    CLOTHES,
    LEATHER,
    CHAIN_MAIL,
    PLATE_MAIL;

    @Override
    public String toString() {
        return name();
    }
}