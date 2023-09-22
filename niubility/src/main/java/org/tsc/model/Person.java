package org.tsc.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int age;
    private Gender gender;
    private Race race;
    private String phoneNumber;
}
