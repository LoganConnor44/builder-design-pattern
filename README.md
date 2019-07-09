# Builder Design Pattern

## What Problem Does This Pattern Solve?

The problem that a developer can encounter is when he/she is attempting to create an object but needs to define different representations of the object. Often, a developer may create a constructor for every possible combination of an object. This is known as [Telescoping Constructors](#Telescoping-Constructors) and is a defined anti-pattern.

## How Can I Identify An Appropriate Time To Use This Pattern?

This is usually identified by a class with [Telescoping Constructors](#Telescoping-Constructors).

A class with telescoping constructors is below - see the annotation for more information.

```java
public class Hero {
    public Hero(Profession profession) { ... }
    public Hero(Profession profession, String name) { ... }
    public Hero(Profession profession, String name, HairType hairType) { ... }
    public Hero(Profession profession, String name, HairType hairType, HairColor hairColor) { ... }
    ...
}
```

## What Does This Look Like In Code?

```java
public final class Hero {
    private final Profession profession;
    private final String name;
    private final HairType hairType;
    private final HairColor hairColor;
    private final Armor armor;
    private final Weapon weapon;

    private Hero(HeroBuilder heroBuilder) {
        this.profession = heroBuilder.profession;
        this.name = heroBuilder.name;
        this.hairColor = heroBuilder.hairColor;
        this.hairType = heroBuilder.hairType;
        this.weapon = heroBuilder.weapon;
        this.armor = heroBuilder.armor;
    }
}
```

```java
public static class HeroBuilder {
    private final Profession profession;
    private final String name;
    private HairType hairType;
    private HairColor hairColor;
    private Armor armor;
    private Weapon weapon;

    public HeroBuilder(Profession profession, String name) {
        if (profession == null || name == null) {
            throw new IllegalArgumentException("profession and name can not be null");
        }
        this.profession = profession;
        this.name = name;
    }

    public HeroBuilder withHairType(HairType hairType) {
        this.hairType = hairType;
        return this;
    }

    public HeroBuilder withHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
        return this;
    }

    public HeroBuilder withArmor(Armor armor) {
        this.armor = armor;
        return this;
    }

    public HeroBuilder withWeapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public Hero build() {
        return new Hero(this);
    }
}
```

```java
Hero mage = new Hero.Builder(Profession.MAGE, "Riobard")
    .withHairColor(HairColor.BLACK)
    .withWeapon(Weapon.DAGGER)
    .build();
```

## Run This Project
```bash
java -cp target/builder-design-pattern-1.0-SNAPSHOT.jar com.loganconnor44.App
```




## Annotations

### Telescoping Constructors

#### What Are Telescoping Constructors?

Telescoping Constructors is an anti-pattern where a class has multiple constructors where each constructor calls a more specific constructor in the hierarchy. Each constructor will contain more class fields than the previous constructor. This will continue until there are no more fields remaining in the class.

#### What Problem Does This Anti-Pattern Introduce?

The main issue with this anti-pattern is **maintainability**.

If there are only a few fields in the class then is anti-pattern isn't a huge problem, but the issue begins when a class has more than two or three fields. Telescoping Constructors can be difficult to read as they may contain a large number of fields. When instantiating this class developers may pass an incorrect argument.

#### What Does This Look Like In Code?

```java
public class Person {

    private final String name;
    private final String lastName;
    private final int age;
    private final String profession;
    private final List<String> hobbies;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.age = 0;
        this.profession = null;
        this.hobbies = new ArrayList<>();
    }
    public Person(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.profession = null;
        this.hobbies = new ArrayList<>();
    }
    public Person(String name, String lastName, int age, String profession) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.profession = profession;
        this.hobbies = new ArrayList<>();
    }
    public Person(String name, String lastName, int age, String profession, List<String> hobbies) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.profession = profession;
        this.hobbies = hobbies == null ? new ArrayList<>() : new ArrayList<>(hobbies);
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getProfession() {
        return profession;
    }
    public List<String> getHobbies() {
        return Collections.unmodifiableList(hobbies);
    }
}
```