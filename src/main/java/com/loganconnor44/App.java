package com.loganconnor44;

import com.loganconnor44.Builders.HeroBuilder;
import com.loganconnor44.GameObjects.HairColor;
import com.loganconnor44.GameObjects.Hero;
import com.loganconnor44.GameObjects.Profession;
import com.loganconnor44.GameObjects.Weapon;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Hero mage = new HeroBuilder(Profession.MAGE, "Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();

        System.out.println("Character " + mage.getName() + " is a " + mage.getProfession());
        System.out.println("Hello World!");
    }
}