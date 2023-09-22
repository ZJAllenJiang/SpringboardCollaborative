package org.tsc.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
public class TscAdministrator extends Person {

    private String adminId;

    @DynamoDbPartitionKey
    public String getAdminId() {
        return adminId;
    }
}
