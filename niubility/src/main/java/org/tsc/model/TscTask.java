package org.tsc.model;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;

@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
public class TscTask {

    private String taskId;

    private String tscAdministratorId;

    private String navigatorId;

    private TaskProgressEnum taskProgressEnum;

    private LocalDate createDate;

    private LocalDate completeDate;

    @DynamoDbPartitionKey
    public String getTaskId() {
        return taskId;
    }
}
