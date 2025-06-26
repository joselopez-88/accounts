package com.eazybites.accounts.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
@Schema(
    description = "Error body containing a simple message",
    name = "MessageBody"
)
@Getter
@Builder
public class MessageBody extends ErrorBody {
    @Schema(
        description = "Human-readable error message",
        example = "Customer not found with the given ID"
    )
    private String message;
}
