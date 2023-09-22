package org.tsc.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

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
}
