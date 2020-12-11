package com.example.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private LessonRepository repository;

    @BeforeEach
    @Transactional
    public void putWithLoop() throws Exception {
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        for (int i = 0; i < 6; i++) {
            String title = "JPA";
            String json = String.format("{\"title\":\"%s\",\"deliveredOn\":\"%s\"}", title, today);

            MockHttpServletRequestBuilder request = post("/lessons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json);

            this.mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                    .andExpect(jsonPath("$.title", is(title)));
        }
    }

    @Test
    @Transactional
    @Rollback
    public void canGetByID() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/4");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.title", is("JPA")));
    }

    @Test
    @Transactional
    @Rollback
    public void canDelete() throws Exception {
        MockHttpServletRequestBuilder deleteRequest = delete("/lessons/8");

        // delete has to return OK status
        this.mvc.perform(deleteRequest)
                .andExpect(status().isOk());
    }
}
