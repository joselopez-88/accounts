package com.eazybites.accounts.exception.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
@Schema(
    description = "Error body containing detailed validation errors",
    name = "DetailsBody"
)
@Getter
@Builder
public class DetailsBody extends ErrorBody {
     @Schema(
        description = "Map of field names to validation error messages",
        example = """
            {
                "email": "must be a valid email address",
                "password": "must be at least 8 characters long"
            }
            """
    )
    private Map<String, String> details;
}
