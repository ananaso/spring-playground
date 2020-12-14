package com.example.SpringWord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WordCounterControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getWordCount() throws Exception {
//        String expectedJSON =
//                "{\n" +
//                "  \"A\": 1,\n" +
//                "  \"brown\": 2,\n" +
//                "  \"cow\": 1,\n" +
//                "  \"jumps\": 1,\n" +
//                "  \"over\": 1,\n" +
//                "  \"a\": 1,\n" +
//                "  \"fox\": 1\n" +
//                "}\n";
//        expectedJSON = expectedJSON.replaceAll("\n", "");
        String expectedJSON = "{\"over\":1,\"A\":1,\"a\":1,\"jumps\":1,\"cow\":1,\"brown\":2,\"fox\":1}";

        String inputText = "A brown cow jumps over a brown fox";

        MockHttpServletRequestBuilder request = post("/words/count")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.TEXT_PLAIN)
                .content(inputText);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJSON));
    }
}