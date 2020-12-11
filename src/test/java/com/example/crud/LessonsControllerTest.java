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

        for (int i = 0; i < 2; i++) {
            String title = "JPA" + i;
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
    public void canGetByID() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", is("JPA1")));
    }

    @Test
    @Transactional
    public void canDelete() throws Exception {
        MockHttpServletRequestBuilder deleteRequest = delete("/lessons/3");

        // delete has to return OK status
        this.mvc.perform(deleteRequest)
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void canPatch() throws Exception {
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String title = "Spring Security";
        String json = String.format("{\"title\":\"%s\",\"deliveredOn\":\"%s\"}", title, today);

        MockHttpServletRequestBuilder deleteRequest = patch("/lessons/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // delete has to return OK status
        this.mvc.perform(deleteRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", is(title)));
    }

    @Test
    @Transactional
    public void canGetByTitle() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/find/JPA0");

        // delete has to return OK status
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                .andExpect(jsonPath("$.title", is("JPA0")));
    }
}
