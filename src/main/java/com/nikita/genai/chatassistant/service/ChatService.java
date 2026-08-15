package com.nikita.genai.chatassistant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikita.genai.chatassistant.dto.ChatRequest;
import com.nikita.genai.chatassistant.dto.ChatResponse;
import com.nikita.genai.chatassistant.dto.gemini.Content;
import com.nikita.genai.chatassistant.dto.gemini.GeminiRequest;
import com.nikita.genai.chatassistant.dto.gemini.GeminiResponse;
import com.nikita.genai.chatassistant.dto.gemini.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String apiKey;

    public ChatResponse chat(ChatRequest request) {

        GeminiRequest geminiRequest = getGeminiRequest(request);

        String response = restClient.post()
                .uri(apiUrl)
                .header("x-goog-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(geminiRequest)
                .retrieve()
                .body(String.class);

        try {
            GeminiResponse geminiResponse =
                    objectMapper.readValue(response, GeminiResponse.class);

            String answer = geminiResponse
                    .getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

            return objectMapper.readValue(answer, ChatResponse.class);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to process Gemini response", e);
        }
    }

    private static GeminiRequest getGeminiRequest(ChatRequest request) {

        String prompt = """
                You are an expert Java Backend Developer and AI Assistant.

                Answer the user's question in simple English.

                Return ONLY valid JSON in this exact format:

                {
                  "title": "Short meaningful title",
                  "points": [
                    "First point",
                    "Second point",
                    "Third point"
                  ],
                  "code": "Java code if required, otherwise empty string"
                }

                Rules:
                - Keep the title short and meaningful.
                - Return each explanation as a separate point.
                - Do not add numbers or bullet characters to the points.
                - Keep each point short and easy to understand.
                - Include Java code when code is relevant.
                - If code is not required, return an empty string.
                - Do not use markdown outside the JSON.
                - Do not wrap the response inside ```json or ```.

                User Question:
                """ + request.getQuestion();

        return new GeminiRequest(
                List.of(
                        new Content(
                                List.of(
                                        new Part(prompt)
                                )
                        )
                )
        );
    }
}