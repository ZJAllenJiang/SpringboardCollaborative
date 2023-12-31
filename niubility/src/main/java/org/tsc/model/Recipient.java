package org.tsc.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tsc.schema.RecipientExcelFlattenSchema;
import org.tsc.schema.RecipientTrainingDataSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.ArrayList;
import java.util.List;

@Data
@DynamoDbBean
@NoArgsConstructor
public class Recipient extends Person {

    private String recipientId;
    private String cabinNum;
    private List<RecipientNeed> assistanceNeeded;
    private String description;
    private RecipientStatus status;

    /**
     * Opiate fields
     */
    private List<OpiateHistory> opiateHistoryList;
    private String addictedSubstance;
    private String substanceUseDisorderProvider;
    private boolean activeUse;
    private boolean inOpiateTreatment;
    private int urgent;

    /**
     * Mental Abnormal fields
     */
    private boolean inMentalTreatment;
    private String mentalHealthProvider;

    @DynamoDbPartitionKey
    public String getRecipientId() {
        return recipientId;
    }

    public RecipientExcelFlattenSchema getFlattenSchema() {
        RecipientExcelFlattenSchema flattenSchema = new RecipientExcelFlattenSchema();
        flattenSchema.setRecipientId(recipientId);
        flattenSchema.setFirstName(getFirstName());
        flattenSchema.setLastName(getLastName());
        flattenSchema.setDateOfBirth(getDateOfBirth());
        flattenSchema.setAge(getAge());
        flattenSchema.setGender(getGender());
        flattenSchema.setRace(getRace());
        flattenSchema.setPhoneNumber(getPhoneNumber());
        flattenSchema.setCabinNum(cabinNum);
        if(assistanceNeeded != null && !assistanceNeeded.isEmpty() ) {
            flattenSchema.setEMPLOYMENT(assistanceNeeded.contains(RecipientNeed.EMPLOYMENT));
            flattenSchema.setMENTAL_HEALTH_SUPPORT(assistanceNeeded.contains(RecipientNeed.MENTAL_HEALTH_SUPPORT));
            flattenSchema.setMEDICAL_CARE(assistanceNeeded.contains(RecipientNeed.MEDICAL_CARE));
            flattenSchema.setLIFE_SKILLS(assistanceNeeded.contains(RecipientNeed.LIFE_SKILLS));
            flattenSchema.setELDERLY(assistanceNeeded.contains(RecipientNeed.ELDERLY));
            flattenSchema.setSERVICE_CONNECTIONS(assistanceNeeded.contains(RecipientNeed.SERVICE_CONNECTIONS));
            flattenSchema.setREENTRY(assistanceNeeded.contains(RecipientNeed.REENTRY));
            flattenSchema.setDISABILITY(assistanceNeeded.contains(RecipientNeed.DISABILITY));
            flattenSchema.setSOCIAL_SECURITY(assistanceNeeded.contains(RecipientNeed.SOCIAL_SECURITY));
            flattenSchema.setHOUSING_PROGRAM(assistanceNeeded.contains(RecipientNeed.HOUSING_PROGRAM));
        }
        flattenSchema.setDescription(description);
        flattenSchema.setStatus(status);
        flattenSchema.setAddictedSubstance(addictedSubstance);
        flattenSchema.setSubstanceUseDisorderProvider(substanceUseDisorderProvider);
        flattenSchema.setActiveUse(activeUse);
        flattenSchema.setInOpiateTreatment(inOpiateTreatment);
        flattenSchema.setUrgent(urgent);
        flattenSchema.setInMentalTreatment(inMentalTreatment);
        flattenSchema.setMentalHealthProvider(mentalHealthProvider);
        return flattenSchema;
    }

    public RecipientTrainingDataSchema getTrainingSchema() {
        RecipientTrainingDataSchema recipientTrainingDataSchema =
                RecipientTrainingDataSchema.builder()
                        .name(getFirstName() == null ? "" : getFirstName()
                                + ", " + getLastName() == null ? "" : getLastName())
                        .sex(getGender())
                        .urgent(getUrgent()).build();
        List<RecipientNeed> needs = getAssistanceNeeded();
        if (needs != null && !needs.isEmpty()) {
            recipientTrainingDataSchema.setEmployment(needs.contains(RecipientNeed.EMPLOYMENT));
            recipientTrainingDataSchema.setMentalHealthSupport(needs.contains(RecipientNeed.MENTAL_HEALTH_SUPPORT));
            recipientTrainingDataSchema.setMedicalCare(needs.contains(RecipientNeed.MEDICAL_CARE));
            recipientTrainingDataSchema.setLifeSkills(needs.contains(RecipientNeed.LIFE_SKILLS));
            recipientTrainingDataSchema.setElderly(needs.contains(RecipientNeed.ELDERLY));
            recipientTrainingDataSchema.setServiceConnections(needs.contains(RecipientNeed.SERVICE_CONNECTIONS));
            recipientTrainingDataSchema.setReentry(needs.contains(RecipientNeed.REENTRY));
            recipientTrainingDataSchema.setDisability(needs.contains(RecipientNeed.DISABILITY));
            recipientTrainingDataSchema.setSocialSecurity(needs.contains(RecipientNeed.SOCIAL_SECURITY));
            recipientTrainingDataSchema.setHousingProgram(needs.contains(RecipientNeed.HOUSING_PROGRAM));
        }
        return recipientTrainingDataSchema;
    }

}
