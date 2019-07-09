package com.loganconnor44.GameObjects;

public enum Weapon {
    DAGGER,
    SWORD,
    AXE,
    WARHAMMER,
    BOW;

    @Override
    public String toString() {
        return name();
    }
}
