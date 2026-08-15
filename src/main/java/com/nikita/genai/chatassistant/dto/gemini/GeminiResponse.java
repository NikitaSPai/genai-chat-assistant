package com.nikita.genai.chatassistant.dto.gemini;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeminiResponse {

  private List<Candidate> candidates;
}
