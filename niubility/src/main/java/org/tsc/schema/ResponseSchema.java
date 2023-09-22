package org.tsc.schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseSchema {

    @Schema(name = "status", description = "indicate the processin status")
    private String status;

    @Schema(name = "message", description = "indicate the processin message")
    private String message;

}
