package com.eazybites.accounts.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    description = "Base error body structure that can be either a simple message or detailed validation errors",
    subTypes = {MessageBody.class, DetailsBody.class},
    discriminatorProperty = "type"
)
public abstract class ErrorBody {

}
