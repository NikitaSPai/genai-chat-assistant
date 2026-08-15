package com.nikita.genai.chatassistant.dto.gemini;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Content {

  private List<Part> parts;
}
