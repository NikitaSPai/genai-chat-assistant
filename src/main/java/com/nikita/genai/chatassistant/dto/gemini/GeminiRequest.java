package com.nikita.genai.chatassistant.dto.gemini;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GeminiRequest {

    private List<Content> contents;

}