package org.tsc.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
public class Organization {

    private String orgId;
    private String orgName;
    private String type;
    private String contactNumber;
    private String address;
    private String responsibility;
    private String description;


    @DynamoDbPartitionKey
    public String getOrgId() {
        return orgId;
    }
}
