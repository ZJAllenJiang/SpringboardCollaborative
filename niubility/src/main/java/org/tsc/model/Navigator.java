package org.tsc.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
public class Navigator extends Person {

    private String navigatorId;

    @DynamoDbPartitionKey
    public String getNavigatorId() {
        return navigatorId;
    }

}
