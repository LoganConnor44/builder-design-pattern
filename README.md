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

## How Is This Better Than...

### Using The Object's Setter Methods

```java
Hero mage = new Hero.Builder(Profession.MAGE, "Riobard")
    .withHairColor(HairColor.BLACK)
    .withWeapon(Weapon.DAGGER)
    .build();
```

Sure does look a whole lot like:

```java
Hero mage = new Hero();
mage.setProfession(Profession.MAGE);
mage.setProfession("Riobard");
mage.setHairColor(HairColor.BLACK);
mage.setWeapon(Weapon.DAGGER);
```

1. The class we are instantiating can not be instantiated **without** these values.
2. Forces the developer to know all the details of the object they are creating before instantiation.
3. Developers can easily follow this logic because the Builder design pattern is standard among software development.

### Creating A Factory Method Design Pattern

## What Are Some Disadvantages To This Design Pattern?

The lines of code that a developer needs to write is essentially doubled. The target class is written and an almost double is written for the target class's builder.

## Run This Project
```bash
java -jar target/builder-design-pattern-1.0-SNAPSHOT.jar
```




## Annotations

### Telescoping Constructors

#### What Are Telescoping Constructors?

Telescoping Constructors is an anti-pattern where a class has multiple constructors where each constructor calls a more specific constructor in the hierarchy. Each constructor will contain more class fields than the previous constructor. This will continue until there are no more fields remaining in the class.

#### What Problem Does This Anti-Pattern Introduce?

The main issue with this anti-pattern is **maintainability**.

If there are only a few fields in the class then is anti-pattern isn't a huge problem, but the issue begins when a class has more than two or three fields. Although keep in mind, a class with eight properties will result in 254 different constructors for every different possible combination of fields that may be instantiated at one time.

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