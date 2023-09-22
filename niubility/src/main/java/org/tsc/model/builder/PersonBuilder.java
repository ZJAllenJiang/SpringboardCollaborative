package org.tsc.model.builder;

import org.tsc.model.Gender;
import org.tsc.model.Person;
import org.tsc.model.Race;

public abstract class PersonBuilder {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int age;
    private Gender gender;
    private Race race;
    private String phoneNumber;

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public PersonBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public PersonBuilder withRace(Race race) {
        this.race = race;
        return this;
    }

    public PersonBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public abstract Person build();

    protected Person buildPerson(Person person) {
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDateOfBirth(dateOfBirth);
        person.setAge(age);
        person.setGender(gender);
        person.setRace(race);
        person.setPhoneNumber(phoneNumber);
        return person;
    }

}
