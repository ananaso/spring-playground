package com.example.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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

    private HashMap<Integer, String> masterIDList = new HashMap<>();

    @BeforeEach
    @Transactional
    public void putWithLoop() throws Exception {
        masterIDList = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            String date = LocalDateTime.now().plusMonths(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String title = "JPA" + i;
            String json = String.format("{\"title\":\"%s\",\"deliveredOn\":\"%s\"}", title, date);

            MockHttpServletRequestBuilder request = post("/lessons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json);

            ResultActions resultActions = this.mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", instanceOf(Number.class)))
                    .andExpect(jsonPath("$.title", is(title)));

            // generate ID list to make tests less brittle
            MvcResult result = resultActions.andReturn();
            String response = result.getResponse().getContentAsString();
            int subStrStart = response.indexOf("id") + 4;
            String numberStr = response.substring(subStrStart, subStrStart + 3).replaceAll("[^0-9]", "");
            Integer id = Integer.parseInt(numberStr);
            masterIDList.put(id, title);
        }
    }

    @Test
    @Transactional
    public void canGetByID() throws Exception {
        Integer id = (Integer) this.masterIDList.keySet().toArray()[0];
        String title = this.masterIDList.get(id);
        String url = "/lessons/" + id;
        MockHttpServletRequestBuilder request = get(url);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.title", is(title)));
    }

    @Test
    @Transactional
    public void canDelete() throws Exception {
        Integer id = (Integer) this.masterIDList.keySet().toArray()[0];
        String title = this.masterIDList.get(id);
        String url = "/lessons/" + id;
        MockHttpServletRequestBuilder deleteRequest = delete(url);

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

        Integer id = (Integer) this.masterIDList.keySet().toArray()[0];
        String url = "/lessons/" + id;
        MockHttpServletRequestBuilder deleteRequest = patch(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // delete has to return OK status
        this.mvc.perform(deleteRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.title", is(title)));
    }

    @Test
    @Transactional
    public void canGetByTitle() throws Exception {
        Integer id = (Integer) this.masterIDList.keySet().toArray()[0];
        String title = this.masterIDList.get(id);
        String url = "/lessons/find/" + title;
        MockHttpServletRequestBuilder request = get(url);

        // delete has to return OK status
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.title", is(title)));
    }

    @Test
    @Transactional
    public void canGetBetweenDates() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/between")
                .queryParam("date1", "2020-11-14")
                .queryParam("date2", "2021-01-14");

        // delete has to return OK status
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", instanceOf(Number.class)))
                .andExpect(jsonPath("$[0].title", is("JPA0")));
    }
}
