package com.nikita.genai.chatassistant.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object from AI chat")
public class ChatResponse {

  @Schema(
      description = "The answer from the AI",
      example = "Here is the answer to your question...")
  private String title;

  @Schema(
      description = "The detailed points from the AI",
      example = "- Point 1\n- Point 2\n- Point 3")
  private List<String> points;

  @Schema(
      description = "The code snippet from the AI, if any",
      example = "public void example() { ... }")
  private String code;
}
