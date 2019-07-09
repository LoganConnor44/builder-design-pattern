package com.loganconnor44;

import com.loganconnor44.Builders.HeroBuilder;
import com.loganconnor44.GameObjects.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Hero mage = new HeroBuilder(Profession.MAGE, "Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .withHairType(HairType.LONG_STRAIGHT)
                .build();

        System.out.println(
                "Character " +
                        mage.getName() +
                        " is a " +
                        mage.getProfession() +
                        " with " +
                        mage.getHairType() +
                        ", " +
                        mage.getHairColor() +
                        " hair and is ready to battle with his " +
                        mage.getWeapon() +
                        "."
        );
    }
}