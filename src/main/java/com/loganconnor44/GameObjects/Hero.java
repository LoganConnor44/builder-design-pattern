package com.loganconnor44.GameObjects;

import com.loganconnor44.Builders.HeroBuilder;

public class Hero {

    private final Profession profession;
    private final String name;
    private final HairType hairType;
    private final HairColor hairColor;
    private final Armor armor;
    private final Weapon weapon;

    public Hero(HeroBuilder heroBuilder) {
        this.profession = heroBuilder.profession;
        this.name = heroBuilder.name;
        this.hairType = heroBuilder.hairType;
        this.hairColor = heroBuilder.hairColor;
        this.armor = heroBuilder.armor;
        this.weapon = heroBuilder.weapon;
    }

    public Profession getProfession() {
        return profession;
    }

    public String getName() {
        return name;
    }

    public HairType getHairType() {
        return hairType;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
