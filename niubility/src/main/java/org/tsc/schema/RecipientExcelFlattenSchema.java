package org.tsc.schema;

import anno.Excel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tsc.model.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipientExcelFlattenSchema {

    @Excel(name = "recipientId", index = 0)
    private String recipientId;

    @Excel(name = "firstName", index = 1)
    private String firstName;

    @Excel(name = "lastName", index = 2)
    private String lastName;

    @Excel(name = "dateOfBirth", index = 3)
    private String dateOfBirth;

    @Excel(name = "age", index = 4)
    private int age;

    @Excel(name = "gender", index = 5)
    private Gender gender;

    @Excel(name = "race", index = 6)
    private Race race;

    @Excel(name = "phoneNumber", index = 7)
    private String phoneNumber;

    @Excel(name = "cabinNum", index = 8)
    private String cabinNum;

    @Excel(name = "EMPLOYMENT", index = 9)
    private boolean  EMPLOYMENT;

    @Excel(name = "MENTAL_HEALTH_SUPPORT", index = 10)
    private boolean MENTAL_HEALTH_SUPPORT;

    @Excel(name = "MEDICAL_CARE", index = 11)
    private boolean MEDICAL_CARE;

    @Excel(name = "LIFE_SKILLS", index = 12)
    private boolean LIFE_SKILLS;

    @Excel(name = "ELDERLY", index = 13)
    private boolean ELDERLY;

    @Excel(name = "SERVICE_CONNECTIONS", index = 14)
    private boolean SERVICE_CONNECTIONS;

    @Excel(name = "REENTRY", index = 15)
    private boolean REENTRY;

    @Excel(name = "DISABILITY", index = 16)
    private boolean DISABILITY;

    @Excel(name = "SOCIAL_SECURITY", index = 17)
    private boolean SOCIAL_SECURITY;

    @Excel(name = "HOUSING_PROGRAM", index = 18)
    private boolean HOUSING_PROGRAM;

    @Excel(name = "description", index = 19)
    private String description;

    @Excel(name = "status", index = 20)
    private RecipientStatus status;

    @Excel(name = "addictedSubstance", index = 21)
    private String addictedSubstance;

    @Excel(name = "substanceUseDisorderProvider", index = 22)
    private String substanceUseDisorderProvider;

    @Excel(name = "activeUse", index = 23)
    private boolean activeUse;

    @Excel(name = "inOpiateTreatment", index = 24)
    private boolean inOpiateTreatment;

    @Excel(name = "urgent", index = 25)
    private int urgent;

    @Excel(name = "inMentalTreatment", index = 26)
    private boolean inMentalTreatment;

    @Excel(name = "mentalHealthProvider", index = 27)
    private String mentalHealthProvider;


    public Recipient getRecipient() {
        Recipient recipient = new Recipient();
        recipient.setRecipientId(recipientId);
        recipient.setFirstName(firstName);
        recipient.setLastName(lastName);
        recipient.setDateOfBirth(dateOfBirth);
        recipient.setAge(age);
        recipient.setGender(gender);
        recipient.setRace(race);
        recipient.setPhoneNumber(phoneNumber);
        recipient.setCabinNum(cabinNum);

        List<RecipientNeed> needList = new ArrayList<>();
        if(EMPLOYMENT) {
            needList.add(RecipientNeed.EMPLOYMENT);
        }
        if(MENTAL_HEALTH_SUPPORT) {
            needList.add(RecipientNeed.MENTAL_HEALTH_SUPPORT);
        }
        if(MEDICAL_CARE) {
            needList.add(RecipientNeed.MEDICAL_CARE);
        }
        if(LIFE_SKILLS) {
            needList.add(RecipientNeed.LIFE_SKILLS);
        }
        if(ELDERLY) {
            needList.add(RecipientNeed.ELDERLY);
        }
        if(SERVICE_CONNECTIONS) {
            needList.add(RecipientNeed.SERVICE_CONNECTIONS);
        }
        if(REENTRY) {
            needList.add(RecipientNeed.REENTRY);
        }
        if(DISABILITY) {
            needList.add(RecipientNeed.DISABILITY);
        }
        if(SOCIAL_SECURITY) {
            needList.add(RecipientNeed.SOCIAL_SECURITY);
        }
        if(HOUSING_PROGRAM) {
            needList.add(RecipientNeed.HOUSING_PROGRAM);
        }
        recipient.setAssistanceNeeded(needList);
        recipient.setDescription(description);
        recipient.setStatus(status);
        recipient.setAddictedSubstance(addictedSubstance);
        recipient.setSubstanceUseDisorderProvider(substanceUseDisorderProvider);
        recipient.setActiveUse(activeUse);
        recipient.setInOpiateTreatment(inOpiateTreatment);
        recipient.setUrgent(urgent);
        recipient.setInMentalTreatment(inMentalTreatment);
        recipient.setMentalHealthProvider(mentalHealthProvider);
        return recipient;
    }

}
