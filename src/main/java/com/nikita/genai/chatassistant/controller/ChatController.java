package com.nikita.genai.chatassistant.controller;

import com.nikita.genai.chatassistant.dto.ChatRequest;
import com.nikita.genai.chatassistant.dto.ChatResponse;
import com.nikita.genai.chatassistant.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Tag(name = "AI Chat API", description = "Chat with Google Gemini AI")
public class ChatController {

  private final ChatService chatService;

  @Operation(summary = "Ask AI a question")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Successful Response"),
    @ApiResponse(responseCode = "400", description = "Invalid Request")
  })
  @PostMapping
  public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {

    ChatResponse response = chatService.chat(request);

    return ResponseEntity.ok(response);
  }
}
