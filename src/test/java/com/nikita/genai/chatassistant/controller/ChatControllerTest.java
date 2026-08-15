package com.nikita.genai.chatassistant.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nikita.genai.chatassistant.dto.ChatResponse;
import com.nikita.genai.chatassistant.service.ChatService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ChatService chatService;

  @Test
  void shouldReturnChatResponse() throws Exception {

    ChatResponse response =
        new ChatResponse(
            "N+1 Problem",
            List.of(
                "One query fetches the main records.", "Additional queries fetch related records."),
            "SELECT * FROM clubs;");

    when(chatService.chat(any())).thenReturn(response);

    mockMvc
        .perform(
            post("/api/v1/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                                {
                                    "question": "What is N+1 problem?"
                                }
                                """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("N+1 Problem"))
        .andExpect(jsonPath("$.points[0]").value("One query fetches the main records."))
        .andExpect(jsonPath("$.points[1]").value("Additional queries fetch related records."))
        .andExpect(jsonPath("$.code").value("SELECT * FROM clubs;"));
  }

  @Test
  void shouldReturnBadRequestForEmptyQuestion() throws Exception {

    mockMvc
        .perform(
            post("/api/v1/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                            {
                                "question": ""
                            }
                            """))
        .andExpect(status().isBadRequest());
  }
}
