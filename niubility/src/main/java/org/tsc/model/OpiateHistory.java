package org.tsc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.time.LocalDate;

@Getter
@Setter
@DynamoDbBean
@NoArgsConstructor
public class OpiateHistory {

    private LocalDate startDate;
    private LocalDate endDate;
    private String substance;
}
