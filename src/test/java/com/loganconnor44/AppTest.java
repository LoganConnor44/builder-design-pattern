package com.loganconnor44;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.loganconnor44.Builders.HeroBuilder;
import com.loganconnor44.GameObjects.HairColor;
import com.loganconnor44.GameObjects.Hero;
import com.loganconnor44.GameObjects.Profession;
import com.loganconnor44.GameObjects.Weapon;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void verifyNameTest() {
        Hero mage = new HeroBuilder(Profession.MAGE, "Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();

        assertSame(
                mage.getName(),
                "Riobard"
        );
    }

    @Test
    public void verifyHairColorTest() {
        Hero mage = new HeroBuilder(Profession.MAGE, "Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();

        assertSame(
                mage.getHairColor().toString(),
                "BLACK"
        );
    }

    @Test
    public void verifyWeaponTest() {
        Hero mage = new HeroBuilder(Profession.MAGE, "Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();

        assertSame(
                mage.getWeapon().toString(),
                "DAGGER"
        );
    }
}
