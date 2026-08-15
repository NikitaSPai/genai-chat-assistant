package com.nikita.genai.chatassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Request object for chat with AI")
public class ChatRequest {

    @Schema(description = "The question to ask the AI", example = "How do I implement a REST API in Spring Boot?")
    @NotBlank(message = "Question cannot be empty")
    @Pattern(
            regexp = "^(?=.*[A-Za-z]).{3,}$",
            message = "Question must contain at least 3 characters and one letter"
    )
    private String question;
}
