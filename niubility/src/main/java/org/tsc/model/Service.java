package org.tsc.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;

@Data
@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
public class Service {

    private String serviceId;
    private String recipientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProgressEnum progress;
    private ServiceTypeEnum type;

    @DynamoDbPartitionKey
    public String getServiceId() {
        return serviceId;
    }
}
